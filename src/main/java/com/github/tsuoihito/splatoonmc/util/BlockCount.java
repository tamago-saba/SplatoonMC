package com.github.tsuoihito.splatoonmc.util;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import com.github.tsuoihito.splatoonmc.SplatoonMC;

public class BlockCount {
    public static void saveCountToScoreboard(SplatoonMC plugin, String objectName, String entry,
            String material, World world, String fromX, String fromY, String fromZ, String toX,
            String toY, String toZ) {
        try {
            saveCountToScoreboard(plugin, objectName, entry, material, world,
                    Integer.parseInt(fromX), Integer.parseInt(fromY), Integer.parseInt(fromZ),
                    Integer.parseInt(toX), Integer.parseInt(toY), Integer.parseInt(toZ));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private static void saveCountToScoreboard(SplatoonMC plugin, String objectName, String entry,
            String material, World world, int fromX, int fromY, int fromZ, int toX, int toY,
            int toZ) {
        int score = getBlockCount(material, world, fromX, fromY, fromZ, toX, toY, toZ);
        ScoreboardUtil.setScore(plugin, objectName, entry, score);
    }

    private static int getBlockCount(String materialString, World world, int fromX, int fromY,
            int fromZ, int toX, int toY, int toZ) {
        Material material = Material.valueOf(materialString.toUpperCase());
        return getBlockCount(material, world, fromX, fromY, fromZ, toX, toY, toZ);
    }

    private static int getBlockCount(Material material, World world, int fromX, int fromY,
            int fromZ, int toX, int toY, int toZ) {
        int smallX = fromX <= toX ? fromX : toX;
        int bigX = fromX >= toX ? fromX : toX;
        int smallY = fromY <= toY ? fromY : toY;
        int bigY = fromY >= toY ? fromY : toY;
        int smallZ = fromZ <= toZ ? fromZ : toZ;
        int bigZ = fromZ >= toZ ? fromZ : toZ;

        int count = 0;

        for (int x = smallX; x <= bigX; x++) {
            for (int y = smallY; y <= bigY; y++) {
                for (int z = smallZ; z <= bigZ; z++) {
                    Block block = world.getBlockAt(x, y, z);
                    if (block.getType().equals(material)) {
                        count++;
                    }
                }
            }
        }

        return count;
    }
}
