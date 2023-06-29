package cc.vastsea.firstjoinkits;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class PlayerJoinEventHandler implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        if (PlayerUtil.isPlayerFirstPlay(player)) {
            giveKits(player);
        }
    }

    public static void giveKits(Player player) {
        String message = getPlayerMessage(player);
        if (!message.isEmpty()) {
            player.sendMessage(message);
        }

        List<String> command = getExecutableCommand(player);
        for (String commandString: command) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), commandString);
        }

        PlayerInventory inventory = player.getInventory();
        List<ItemStack> stack = getItemStack(player);
        for (ItemStack item: stack) {
            inventory.addItem(item);
        }
        player.updateInventory();
    }

    static String getPlayerMessage(Player player) {
        Object message = FirstJoinKits.config.get("message");
        if (message != null) {
            String msg = message.toString().replace("&", "§");
            msg = PlaceholderAPI.setPlaceholders(player, msg);
            return msg;
        } else {
            return "";
        }
    }

    static List<String> getExecutableCommand(Player player) {
        List<String> list = new ArrayList<>();
        Object rawCommand = FirstJoinKits.config.get("commands");
        if (rawCommand == null) {
            return list;
        }

        List<String> commandList = (List<String>) rawCommand;
        for (String command: commandList) {
            command = PlaceholderAPI.setPlaceholders(player, command);
            command = command.replace("&", "§");
            list.add(command);
        }

        return list;
    }

    static List<ItemStack> getItemStack(Player player) {
        List<ItemStack> list = new ArrayList<>();

        List<Object> rawList = (List<Object>) FirstJoinKits.config.getList("items");

        if (rawList != null) {
            for (Object rawItem: rawList) {
                    Map item = (Map) rawItem;
                    Material material = Material.getMaterial(item.get("material").toString().toUpperCase());
                    if (material == null) {
                        throw new RuntimeException("Material " + item.get("material").toString() + "is not an item");
                    }
                    List<String> rawLore = (List<String>) item.get("lore");
                    List<String> lore = new ArrayList<>();
                    List<Object> enchantment = (List<Object>) item.get("enchantment");
                    if (rawLore != null) {
                        for (String loreElement: rawLore) {
                            loreElement = PlaceholderAPI.setPlaceholders(player, loreElement);
                            lore.add(loreElement.replace("&", "§"));
                        }
                    }

                    ItemStack stack = new ItemStack(material);
                    if (item.get("amount") != null) {
                        int amount = Integer.parseInt(item.get("amount").toString());
                        stack.setAmount(amount);
                    }

                    ItemMeta meta = stack.getItemMeta();
                    if (rawLore != null) {
                        if (meta != null) {
                            meta.setLore(lore);
                        }
                    }
                    if (item.get("display") != null) {
                        String displayName = item.get("display").toString();
                        displayName = PlaceholderAPI.setPlaceholders(player, displayName);
                        if (meta != null) {
                            meta.setDisplayName(displayName.replace("&", "§"));
                        }
                    }

                    if (enchantment != null) {
                        for (Object rawEnchantmentElement: enchantment) {
                            Map enchantmentElement = (Map) rawEnchantmentElement;
                            Enchantment id = Enchantment.getByName(enchantmentElement.get("id").toString().toUpperCase());
                            int level = Integer.parseInt(enchantmentElement.get("level").toString());
                            if (meta != null && id != null) {
                                meta.addEnchant(id, level, true);
                            }
                        }
                    }

                    stack.setItemMeta(meta);

                    list.add(stack);
            }
        }

        return list;
    }
}
