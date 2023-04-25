package com.github.tsuoihito.splatoonmc;

import org.bukkit.plugin.java.JavaPlugin;

import com.github.tsuoihito.splatoonmc.command.BlockCountCommand;
import com.github.tsuoihito.splatoonmc.listener.PlayerListener;

public final class SplatoonMC extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
        getCommand("blockcount").setExecutor(new BlockCountCommand(this));
    }

    @Override
    public void onDisable() {
    }
}
