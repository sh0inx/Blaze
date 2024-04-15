package io.github.sh0inx.blaze.managers.dependencyManager;

import io.wispforest.owo.Owo;

public class DependencyManager {

    private OwoManager owoManager;

    public void initializeDependencies() {
        this.owoManager = new OwoManager();
        owoManager.initializeOwo();
    }
}
