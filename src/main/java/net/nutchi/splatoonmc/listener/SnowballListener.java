package net.nutchi.splatoonmc.listener;

import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import net.nutchi.splatoonmc.SplatoonMC;
import net.nutchi.splatoonmc.model.SplatoonTeam;
import net.nutchi.splatoonmc.util.ScoreboardUtil;

public class SnowballListener implements Listener {
    private final SplatoonMC plugin;

    public SnowballListener(SplatoonMC plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        if (event.getHitEntity() instanceof Player && event.getEntity() instanceof Snowball
                && event.getEntity().getShooter() instanceof Player) {
            Player attacked = (Player) event.getHitEntity();
            Player attacker = (Player) event.getEntity().getShooter();

            ScoreboardUtil.getTeam(plugin, attacked.getName()).ifPresent(attackedTeam -> {
                ScoreboardUtil.getTeam(plugin, attacker.getName()).ifPresent(attackerTeam -> {
                    if ((attackedTeam == SplatoonTeam.PINK && attackerTeam == SplatoonTeam.GREEN)
                            || (attackedTeam == SplatoonTeam.GREEN
                                    && attackerTeam == SplatoonTeam.PINK)) {
                        ScoreboardUtil.increaseScore(plugin, "snowball_hit", attacked.getName());
                    }
                });
            });
        }
    }
}
