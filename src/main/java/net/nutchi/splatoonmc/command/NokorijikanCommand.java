package net.nutchi.splatoonmc.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import net.nutchi.splatoonmc.SplatoonMC;
import net.nutchi.splatoonmc.util.ScoreboardUtil;
import net.nutchi.splatoonmc.util.Sidebar;

public class NokorijikanCommand implements TabExecutor {
    private final SplatoonMC plugin;

    public NokorijikanCommand(SplatoonMC plugin) {
        this.plugin = plugin;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label,
            String[] args) {
        switch (args.length) {
            case 1:
                return ScoreboardUtil.getObjectNames(plugin).stream()
                        .filter(s -> s.startsWith(args[0])).collect(Collectors.toList());
            case 2:
                return Arrays.asList("ENTRY");
        }
        return new ArrayList<>();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 2) {
            String objectName = args[0];
            String entry = args[1];
            Sidebar.showNokorijikanScoreboard(plugin, objectName, entry);
            return true;
        }
        return false;
    }
}
