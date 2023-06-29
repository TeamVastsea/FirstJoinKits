package cc.vastsea.firstjoinkits;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GiveKitsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        Player player;
        if (args.length == 0) {
            player = Bukkit.getPlayer(sender.getName());
        } else {
            player = Bukkit.getPlayer(args[0]);
        }

        if (player == null) {
            return true;
        }

        PlayerJoinEventHandler.giveKits(player);

        return true;
    }
}
