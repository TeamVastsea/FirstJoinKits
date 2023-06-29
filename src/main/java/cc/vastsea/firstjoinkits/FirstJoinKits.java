package cc.vastsea.firstjoinkits;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.logging.Level;

public final class FirstJoinKits extends JavaPlugin {
    public static JavaPlugin instance;

    public static FileConfiguration config;

    @Override
    public void onEnable() {

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") == null) {
            getLogger().log(Level.SEVERE, "PlaceholderAPI not installed, disabling.");
            Bukkit.getPluginManager().disablePlugin(this);
        }

        saveDefaultConfig();
        instance = this;
        config = getConfig();

        Bukkit.getPluginManager().registerEvents(new PlayerJoinEventHandler(), this);

        if (Bukkit.getPluginCommand("give-kits") != null) {
            Objects.requireNonNull(Bukkit.getPluginCommand("give-kits")).setExecutor(new GiveKitsCommand());
        }

        getLogger().info("First Join Kits Enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info("First Join Kits disabled.");
    }
}
