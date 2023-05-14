package net.nutchi.splatoonmc.util;

import java.util.Optional;
import java.util.UUID;
import org.bukkit.ChatColor;
import org.bukkit.Nameable;
import org.bukkit.scoreboard.Criteria;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import net.nutchi.splatoonmc.SplatoonMC;

public class Sidebar {
    public static void showNokorijikanScoreboard(SplatoonMC plugin, String objectName,
            String entry) {
        int nokorijikan = ScoreboardUtil.getScore(plugin, objectName, entry);
        plugin.getServer().getOnlinePlayers().forEach(p -> {
            createNokorijikanScoreboard(plugin, nokorijikan).ifPresent(sb -> p.setScoreboard(sb));
        });
    }

    private static Optional<Scoreboard> createNokorijikanScoreboard(SplatoonMC plugin, int tick) {
        return ScoreboardUtil.getScoreboard(plugin).map(scoreboard -> {
            String nokorijikanScore = "SplatoonPluginNokorijikan";

            scoreboard.getObjectives().forEach(o -> {
                if (o.getName().equals(nokorijikanScore)) {
                    o.unregister();
                }
            });
            Objective objective =
                    scoreboard.registerNewObjective(nokorijikanScore, Criteria.DUMMY, "");
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);

            int minites = tick / 20 / 60;
            int seconds = tick / 20 % 60;

            objective.getScore(ChatColor.GRAY + "===============").setScore(15);
            objective.getScore(ChatColor.YELLOW + "残り時間 " + ChatColor.GREEN + minites + ":"
                    + String.format("%02d", seconds)).setScore(14);
            objective.getScore(ChatColor.GRAY + "=============== ").setScore(13);
            objective.getScore("").setScore(12);

            return scoreboard;

        });
    }

    public static void showKekkaScoreboard(SplatoonMC plugin, String midoriObjectName,
            String midoriEntry, String pinkObjectName, String pinkEntry) {
        plugin.getServer().getOnlinePlayers().forEach(p -> {
            createKekkaScoreboard(plugin, midoriObjectName, midoriEntry, pinkObjectName, pinkEntry)
                    .ifPresent(sb -> p.setScoreboard(sb));
        });
    }

    private static Optional<Scoreboard> createKekkaScoreboard(SplatoonMC plugin,
            String midoriObjectName, String midoriEntry, String pinkObjectName, String pinkEntry) {
        return ScoreboardUtil.getScoreboard(plugin).map(scoreboard -> {
            String kekkaScore = "SplatoonPluginKekka";

            scoreboard.getObjectives().forEach(o -> {
                if (o.getName().equals(kekkaScore)) {
                    o.unregister();
                }
            });
            Objective objective = scoreboard.registerNewObjective(kekkaScore, Criteria.DUMMY, "");
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);

            double greenPercent =
                    (double) ScoreboardUtil.getScore(plugin, midoriObjectName, midoriEntry) / 10;
            double pinkPercent =
                    (double) ScoreboardUtil.getScore(plugin, pinkObjectName, pinkEntry) / 10;
            objective.getScore(ChatColor.GREEN + "ミドリ  " + ChatColor.GOLD + greenPercent + "%")
                    .setScore(15);
            objective
                    .getScore(ChatColor.LIGHT_PURPLE + "ピンク  " + ChatColor.GOLD + pinkPercent + "%")
                    .setScore(14);
            objective.getScore("").setScore(13);

            return scoreboard;
        });
    }

    public static void showCustomSidebar(SplatoonMC plugin, String objectName, String tag) {
        plugin.getServer().getOnlinePlayers()
                .forEach(p -> createCustomSidebarScoreboard(plugin, objectName, tag)
                        .ifPresent(sb -> p.setScoreboard(sb)));
    }

    private static Optional<Scoreboard> createCustomSidebarScoreboard(SplatoonMC plugin,
            String objectName, String tag) {
        return ScoreboardUtil.getScoreboard(plugin).map(scoreboard -> {
            String sideBarObjectName = "splatoonplugin_entity_name_sidebar";
            scoreboard.getObjectives().forEach(o -> {
                if (o.getName().equals(sideBarObjectName)) {
                    o.unregister();
                }
            });

            Objective sidebarObjective =
                    scoreboard.registerNewObjective(sideBarObjectName, Criteria.DUMMY, "");
            sidebarObjective.setDisplaySlot(DisplaySlot.SIDEBAR);
            ScoreboardUtil.getObjective(plugin, objectName)
                    .ifPresent(o -> sidebarObjective.setDisplayName(o.getDisplayName()));

            ScoreboardUtil.getScoreboard(plugin).ifPresent(sb -> sb.getEntries()
                    .forEach(e -> getNameOfEntityForSidebar(plugin, e, tag).ifPresent(n -> Optional
                            .ofNullable(sb.getObjective(objectName)).ifPresent(o -> sidebarObjective
                                    .getScore(n).setScore(o.getScore(e).getScore())))));

            return scoreboard;
        });
    }

    private static Optional<String> getNameOfEntityForSidebar(SplatoonMC plugin, String name,
            String tag) {
        return getUuidFromString(name).flatMap(uuid -> Optional
                .ofNullable(plugin.getServer().getEntity(uuid))
                .filter(e -> e.getScoreboardTags().contains(tag)).map(Nameable::getCustomName));
    }

    private static Optional<UUID> getUuidFromString(String name) {
        try {
            return Optional.of(UUID.fromString(name));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}
