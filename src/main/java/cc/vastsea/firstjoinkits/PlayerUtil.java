package cc.vastsea.firstjoinkits;

import org.bukkit.entity.Player;

public class PlayerUtil {
    public static boolean isPlayerFirstPlay(Player player) {

        return player.getLastPlayed() == 0;
    }
}
