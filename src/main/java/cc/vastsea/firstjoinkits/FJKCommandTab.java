package cc.vastsea.firstjoinkits;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class FJKCommandTab implements TabCompleter {
    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!command.getName().equals("firstjoinkits") && !command.getName().equals("fjk")) {
            return null;
        }
        if (args.length > 1) {
            return null;
        }
        List<String> list = new ArrayList<>(3);
        list.add("give");
        list.add("reload");

        return list;
    }
}
