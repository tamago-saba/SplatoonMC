package net.nutchi.splatoonmc.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import net.nutchi.splatoonmc.SplatoonMC;
import net.nutchi.splatoonmc.util.Sidebar;

public class KekkaCommand implements TabExecutor {
    private final SplatoonMC plugin;

    public KekkaCommand(SplatoonMC plugin) {
        this.plugin = plugin;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label,
            String[] args) {
        switch (args.length) {
            case 1:
                return Arrays.asList("MIDORI_OBJECT_NAME");
            case 2:
                return Arrays.asList("MIDORI_ENTRY");
            case 3:
                return Arrays.asList("PINK_OBJECT_NAME");
            case 4:
                return Arrays.asList("PINK_ENTRY");
        }
        return new ArrayList<>();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 4) {
            String midoriObjectName = args[0];
            String midoriEntry = args[1];
            String pinkObjectName = args[2];
            String pinkEntry = args[3];

            Sidebar.showKekkaScoreboard(plugin, midoriObjectName, midoriEntry, pinkObjectName,
                    pinkEntry);
            return true;
        }
        return false;
    }
}
