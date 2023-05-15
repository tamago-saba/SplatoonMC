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
        String coolTimeObject = "rollercool";

        if (ScoreboardUtil.getScore(plugin, coolTimeObject, player.getName()) <= 0) {
            Location loc = player.getEyeLocation();
            Vector dir = loc.getDirection();

            for (int i = 2; i <= 6; i++) {
                Location base = loc.clone().add(dir.clone().multiply(i));
                Block baseBlock = base.getBlock();

                int fromX = baseBlock.getX() - 1;
                int fromY = baseBlock.getY() - 3;
                int fromZ = baseBlock.getZ() - 1;
                int toX = baseBlock.getX() + 1;
                int toY = baseBlock.getY() + 1;
                int toZ = baseBlock.getZ() + 1;

                int smallX = fromX <= toX ? fromX : toX;
                int smallY = fromY <= toY ? fromY : toY;
                int smallZ = fromZ <= toZ ? fromZ : toZ;
                int bigX = fromX >= toX ? fromX : toX;
                int bigY = fromY >= toY ? fromY : toY;
                int bigZ = fromZ >= toZ ? fromZ : toZ;

                for (int x = smallX; x <= bigX; x++) {
                    for (int y = smallY; y <= bigY; y++) {
                        for (int z = smallZ; z <= bigZ; z++) {
                            Block block = base.getWorld().getBlockAt(x, y, z);
                            getYourBlockMaterial(plugin, player.getName())
                                    .ifPresent(yourMaterial -> {
                                        getMyBlockMaterial(plugin, player.getName())
                                                .ifPresent(myMaterial -> {
                                                    if (block.getType().equals(yourMaterial)
                                                            || block.getType().equals(
                                                                    Material.POLISHED_DEEPSLATE)) {
                                                        block.setType(myMaterial);
                                                    }
                                                });
                                    });
                        }
                    }
                }

            }

            ScoreboardUtil.setScore(plugin, coolTimeObject, player.getName(), 15);
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
