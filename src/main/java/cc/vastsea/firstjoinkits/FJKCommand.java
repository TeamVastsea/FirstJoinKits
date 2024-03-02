package cc.vastsea.firstjoinkits;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.logging.Level;

public class FJKCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull org.bukkit.command.Command command, @NotNull String label, String[] args) {
        if (args.length >= 1) {
            String subCommand = args[0];
            return switch (subCommand) {
                case "give" -> giveKits(sender, args);
                case "reload" -> reloadKits(Objects.requireNonNull(Bukkit.getPlayer(sender.getName())));
                default -> false;
            };
        } else {
            return false;
        }
    }

    boolean giveKits(CommandSender sender, String[] args) {
        Player sendPlayer = Bukkit.getPlayer(sender.getName());
        assert sendPlayer != null;
        if (!sendPlayer.hasPermission("firstjoinkits.give")) {
            FirstJoinKits.instance.getLogger().info("Player " + sendPlayer.getName() + " tried to use '/fjk give' with out permission");
            return true;
        }

        Player player;
        if (args.length == 1) {
            player = sendPlayer;
        } else {
            player = Bukkit.getPlayer(args[1]);
        }

        if (player == null) {
            return true;
        }

        PlayerJoinEventHandler.giveKits(player);

        return true;
    }
    boolean reloadKits(Player sender) {
        Player sendPlayer = Bukkit.getPlayer(sender.getName());
        assert sendPlayer != null;
        if (!sendPlayer.hasPermission("firstjoinkits.reload")) {
            FirstJoinKits.instance.getLogger().info("Player " + sendPlayer.getName() + " tried to use '/fjk reload' with out permission");
            return true;
        }

        FirstJoinKits.instance.reloadConfig();
        FirstJoinKits.config = FirstJoinKits.instance.getConfig();
        sender.sendMessage("配置文件已重新加载");
        return true;
    }
}
