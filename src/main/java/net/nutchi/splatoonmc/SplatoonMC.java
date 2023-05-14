package net.nutchi.splatoonmc;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import net.nutchi.splatoonmc.command.BlockCountCommand;
import net.nutchi.splatoonmc.command.CustomSidebarCommand;
import net.nutchi.splatoonmc.command.KekkaCommand;
import net.nutchi.splatoonmc.command.NokorijikanCommand;
import net.nutchi.splatoonmc.listener.PlayerListener;
import net.nutchi.splatoonmc.listener.SnowballListener;

public final class SplatoonMC extends JavaPlugin {
    @Override
    public void onEnable() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new PlayerListener(this), this);
        pm.registerEvents(new SnowballListener(this), this);
        getCommand("blockcount").setExecutor(new BlockCountCommand(this));
        getCommand("nokorijikan").setExecutor(new NokorijikanCommand(this));
        getCommand("kekka").setExecutor(new KekkaCommand(this));
        getCommand("sidebar").setExecutor(new CustomSidebarCommand(this));
    }

    @Override
    public void onDisable() {}
}
