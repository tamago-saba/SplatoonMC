package net.nutchi.splatoonmc.util;

import java.util.Optional;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import net.nutchi.splatoonmc.SplatoonMC;

public class Fude {
    public static void fillMyColor(SplatoonMC plugin, Player player) {
        Location loc = player.getEyeLocation();
        Vector dir = loc.getDirection();
        Location base = loc.add(dir.multiply(2));

        int distance = 2;

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
                    getYourBlockMaterial(plugin, player.getName()).ifPresent(yourMaterial -> {
                        getMyBlockMaterial(plugin, player.getName()).ifPresent(myMaterial -> {
                            if (block.getType().equals(yourMaterial)
                                    || block.getType().equals(Material.POLISHED_DEEPSLATE)) {
                                block.setType(myMaterial);
                            }
                        });
                    });
                }
            }
        }
    }

    private static Optional<Material> getYourBlockMaterial(SplatoonMC plugin, String player) {
        return ScoreboardUtil.getTeam(plugin, player).flatMap(team -> {
            switch (team) {
                case PINK:
                    return Optional.of(Material.LIME_CONCRETE);
                case GREEN:
                    return Optional.of(Material.MAGENTA_CONCRETE);
                default:
                    return Optional.empty();
            }
        });
    }

    private static Optional<Material> getMyBlockMaterial(SplatoonMC plugin, String player) {
        return ScoreboardUtil.getTeam(plugin, player).flatMap(team -> {
            switch (team) {
                case PINK:
                    return Optional.of(Material.MAGENTA_CONCRETE);
                case GREEN:
                    return Optional.of(Material.LIME_CONCRETE);
                default:
                    return Optional.empty();
            }
        });
    }
}
