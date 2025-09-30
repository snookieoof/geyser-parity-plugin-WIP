package com.snookie.paritycauldrons;

import org.bukkit.plugin.java.JavaPlugin;

public class ParityCauldrons extends JavaPlugin {

    private static ParityCauldrons instance;

    @Override
    public void onEnable() {
        instance = this;
        getLogger().info("ParityCauldrons has been enabled.");
        // We'll register event listeners here later
    }

    @Override
    public void onDisable() {
        getLogger().info("ParityCauldrons has been disabled.");
    }

    public static ParityCauldrons getInstance() {
        return instance;
    }
}
