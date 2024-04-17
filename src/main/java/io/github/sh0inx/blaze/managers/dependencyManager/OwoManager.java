package io.github.sh0inx.blaze.managers.dependencyManager;

import io.github.sh0inx.blaze.Blaze;
import io.wispforest.owo.Owo;

public class OwoManager {

    protected Owo owoLib;

    public void initializeOwo() {
        if(!Blaze.getInstance().utils.classExists("io.wispforest.owo.Owo")) {
            Blaze.getLogger().info("Could not find owo-lib, initializing built-in dependency...");
        }
    }
}
