package net.nutchi.splatoonmc.listener;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import net.nutchi.splatoonmc.SplatoonMC;
import net.nutchi.splatoonmc.util.Fude;
import net.nutchi.splatoonmc.util.ScoreboardUtil;

public class PlayerListener implements Listener {
    private final SplatoonMC plugin;

    public PlayerListener(SplatoonMC plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getHand() == EquipmentSlot.HAND
                && (event.getAction().equals(Action.LEFT_CLICK_AIR)
                        || event.getAction().equals(Action.LEFT_CLICK_BLOCK))) {
            ScoreboardUtil.increaseScore(plugin, "left_click", event.getPlayer().getName());

            Material itemType = event.getPlayer().getInventory().getItemInMainHand().getType();
            if (itemType.equals(Material.DIAMOND_SHOVEL)
                    || itemType.equals(Material.DIAMOND_PICKAXE)) {
                Fude.fillMyColor(plugin, event.getPlayer());
            }
        }
    }
}
