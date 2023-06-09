package net.nutchi.splatoonmc.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;
import net.nutchi.splatoonmc.SplatoonMC;
import net.nutchi.splatoonmc.model.SplatoonTeam;

public class ScoreboardUtil {
    public static int getScore(SplatoonMC plugin, String objectName, String entry) {
        return getScoreboardScore(plugin, objectName, entry).map(Score::getScore).orElse(0);
    }

    public static void setScore(SplatoonMC plugin, String objectName, String entry, int score) {
        getScoreboardScore(plugin, objectName, entry).ifPresent(s -> s.setScore(score));
    }

    public static void increaseScore(SplatoonMC plugin, String objectName, String entry) {
        getScoreboardScore(plugin, objectName, entry).ifPresent(s -> s.setScore(s.getScore() + 1));
    }

    private static Optional<Score> getScoreboardScore(SplatoonMC plugin, String objectName,
            String entry) {
        return getObjective(plugin, objectName).map(o -> o.getScore(entry));
    }

    public static List<String> getObjectNames(SplatoonMC plugin) {
        return getScoreboard(plugin).map(sb -> sb.getObjectives().stream().map(Objective::getName)
                .collect(Collectors.toList())).orElse(new ArrayList<>());
    }

    public static Optional<SplatoonTeam> getTeam(SplatoonMC plugin, String entry) {
        return getScoreboard(plugin).flatMap(sb -> getTeam(sb, entry));
    }

    private static Optional<SplatoonTeam> getTeam(Scoreboard scoreboard, String entry) {
        Team pinkTeam = scoreboard.getTeam("pink");
        Team greenTeam = scoreboard.getTeam("green");
        if (pinkTeam != null && greenTeam != null) {
            if (pinkTeam.getEntries().contains(entry)) {
                return Optional.of(SplatoonTeam.PINK);
            } else if (greenTeam.getEntries().contains(entry)) {
                return Optional.of(SplatoonTeam.GREEN);
            }
        }
        return Optional.empty();
    }

    public static Optional<Objective> getObjective(SplatoonMC plugin, String objectName) {
        return getScoreboard(plugin)
                .flatMap(sb -> Optional.ofNullable(sb.getObjective(objectName)));
    }

    public static Optional<Scoreboard> getScoreboard(SplatoonMC plugin) {
        return Optional.ofNullable(plugin.getServer().getScoreboardManager())
                .map(ScoreboardManager::getMainScoreboard);
    }
}
