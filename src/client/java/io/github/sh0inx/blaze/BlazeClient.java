package io.github.sh0inx.blaze;

import io.github.sh0inx.blaze.managers.displayManager.DisplayManager;
import lombok.Getter;
import net.fabricmc.api.ClientModInitializer;

public class BlazeClient implements ClientModInitializer {

	@Getter
	private DisplayManager displayManager;

	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		this.displayManager = new DisplayManager();
	}
}
