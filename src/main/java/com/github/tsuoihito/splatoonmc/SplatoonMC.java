package com.github.tsuoihito.splatoonmc;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.tsuoihito.splatoonmc.command.BlockCountCommand;
import com.github.tsuoihito.splatoonmc.command.KekkaCommand;
import com.github.tsuoihito.splatoonmc.command.NokorijikanCommand;
import com.github.tsuoihito.splatoonmc.listener.PlayerListener;
import com.github.tsuoihito.splatoonmc.listener.SnowballListener;

public final class SplatoonMC extends JavaPlugin {
    @Override
    public void onEnable() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new PlayerListener(this), this);
        pm.registerEvents(new SnowballListener(this), this);
        getCommand("blockcount").setExecutor(new BlockCountCommand(this));
        getCommand("nokorijikan").setExecutor(new NokorijikanCommand(this));
        getCommand("kekka").setExecutor(new KekkaCommand(this));
    }

    @Override
    public void onDisable() {}
}
