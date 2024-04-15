package io.github.sh0inx.blaze.managers.dependencyManager;

import io.github.sh0inx.blaze.Blaze;
import io.wispforest.owo.Owo;

public class OwoManager {

    protected Owo owoLib;

    private boolean classExists() {
        try {
            Class.forName("io.wispforest.owo.Owo");
        } catch (ClassNotFoundException e) {
            return false;
        }
        return true;
    }

    public void initializeOwo() {
        if(!classExists()) {
            Blaze.getLogger().info("Could not find owo-lib, initializing built-in dependency...");
        }
    }
}
