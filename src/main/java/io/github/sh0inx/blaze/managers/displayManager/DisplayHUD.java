package io.github.sh0inx.blaze.managers.displayManager;

import io.wispforest.owo.ui.base.BaseUIModelScreen;
import io.wispforest.owo.ui.container.DraggableContainer;
import io.wispforest.owo.ui.container.FlowLayout;
import net.minecraft.util.Identifier;

public class DisplayHUD extends BaseUIModelScreen<FlowLayout> {

    public DisplayHUD() {
        super(FlowLayout.class, DataSource.asset(new Identifier("blaze", "HUDStyling")));
    }
    @Override
    protected void build(FlowLayout rootComponent) {
        rootComponent.childById(DraggableContainer.class, "minimap").mouseDrag();
    }
}
