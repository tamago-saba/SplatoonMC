package com.github.tsuoihito.splatoonmc.util;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.Nullable;

import com.github.tsuoihito.splatoonmc.SplatoonMC;
import com.github.tsuoihito.splatoonmc.model.SplatoonTeam;

public class FudeUtil {
    public static void fillMyColor(SplatoonMC plugin, Player player) {
        ScoreboardManager manager = plugin.getServer().getScoreboardManager();
        if (manager != null) {
            Scoreboard scoreboard = manager.getMainScoreboard();
            fillMyColor(scoreboard, player);
        }
    }

    private static void fillMyColor(Scoreboard scoreboard, Player player) {
        Location loc = player.getEyeLocation();
        Vector dir = loc.getDirection();
        Location base = loc.add(dir.multiply(2));

        int distance = 1;

        int fromX = base.getBlockX() - distance;
        int fromY = base.getBlockY() - distance;
        int fromZ = base.getBlockZ() - distance;
        int toX = base.getBlockX() + distance;
        int toY = base.getBlockY() + distance;
        int toZ = base.getBlockZ() + distance;

        for (int x = fromX; x <= toX; x++) {
            for (int y = fromY; y <= toY; y++) {
                for (int z = fromZ; z <= toZ; z++) {
                    Block block = base.getWorld().getBlockAt(x, y, z);
                    Material yourMaterial = getYourBlockMaterial(scoreboard, player.getName());
                    Material myMaterial = getMyBlockMaterial(scoreboard, player.getName());
                    if (yourMaterial != null && myMaterial != null) {
                        if (block.getType().equals(yourMaterial) || block.getType().equals(Material.POLISHED_DEEPSLATE)) {
                            block.setType(myMaterial);
                        }
                    }
                }
            }
        }
    }

    @Nullable
    private static Material getYourBlockMaterial(Scoreboard scoreboard, String playerName) {
        switch (getMyTeam(scoreboard, playerName)) {
            case PINK:
                return Material.LIME_CONCRETE;
            case GREEN:
                return Material.MAGENTA_CONCRETE;
            default:
                return null;
        }
    }

    @Nullable
    private static Material getMyBlockMaterial(Scoreboard scoreboard, String playerName) {
        switch (getMyTeam(scoreboard, playerName)) {
            case PINK:
                return Material.MAGENTA_CONCRETE;
            case GREEN:
                return Material.LIME_CONCRETE;
            default:
                return null;
        }
    }

    @Nullable
    private static SplatoonTeam getMyTeam(Scoreboard scoreboard, String playerName) {
        Team pinkTeam = scoreboard.getTeam("pink");
        Team greenTeam = scoreboard.getTeam("green");
        if (pinkTeam != null && greenTeam != null) {
            if (pinkTeam.getEntries().contains(playerName)) {
                return SplatoonTeam.PINK;
            } else if (greenTeam.getEntries().contains(playerName)) {
                return SplatoonTeam.GREEN;
            }
        }
        return null;
    }
    
}
