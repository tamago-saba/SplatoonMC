package com.github.tsuoihito.splatoonmc.listener;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.github.tsuoihito.splatoonmc.SplatoonMC;
import com.github.tsuoihito.splatoonmc.util.FudeUtil;

public class PlayerListener implements Listener {
    private final SplatoonMC plugin;

    public PlayerListener(SplatoonMC plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction().equals(Action.LEFT_CLICK_AIR) || event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            if (event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.DIAMOND_SHOVEL)) {
                FudeUtil.fillMyColor(plugin, event.getPlayer());
            }
        }
    }
}
