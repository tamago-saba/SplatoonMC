package com.github.tsuoihito.splatoonmc.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import com.github.tsuoihito.splatoonmc.SplatoonMC;
import com.github.tsuoihito.splatoonmc.util.BlockCount;

public class BlockCountCommand implements TabExecutor {
    private final SplatoonMC plugin;

    public BlockCountCommand(SplatoonMC plugin) {
        this.plugin = plugin;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label,
            String[] args) {
        switch (args.length) {
            case 1:
                return Arrays.asList("OBJECT_NAME");
            case 2:
                return Arrays.asList("ENTRY");
            case 3:
                return Stream.of(Material.values()).map(Material::toString)
                        .filter(s -> s.startsWith(args[2])).collect(Collectors.toList());
            case 4:
                return Arrays.asList("FROM_X");
            case 5:
                return Arrays.asList("FROM_Y");
            case 6:
                return Arrays.asList("FROM_Z");
            case 7:
                return Arrays.asList("TO_X");
            case 8:
                return Arrays.asList("TO_Y");
            case 9:
                return Arrays.asList("TO_Z");
        }
        return new ArrayList<>();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 9) {
            World world;
            if (sender instanceof BlockCommandSender) {
                BlockCommandSender blockCommandSender = (BlockCommandSender) sender;
                world = blockCommandSender.getBlock().getWorld();
            } else if (sender instanceof Player) {
                Player player = (Player) sender;
                world = player.getLocation().getWorld();
            } else {
                world = null;
            }

            if (world != null) {
                String objectName = args[0];
                String entry = args[1];
                String material = args[2];
                String fromX = args[3];
                String fromY = args[4];
                String fromZ = args[5];
                String toX = args[6];
                String toY = args[7];
                String toZ = args[8];

                BlockCount.saveCountToScoreboard(plugin, objectName, entry, material, world, fromX,
                        fromY, fromZ, toX, toY, toZ);

                sender.sendMessage("Saved block count to the score");

                return true;
            }
        }
        return false;
    }
}
