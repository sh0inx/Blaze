package io.github.sh0inx.blaze;

import io.github.sh0inx.blaze.managers.PlatformManager;
import io.github.sh0inx.blaze.managers.dependencyManager.DependencyManager;
import io.github.sh0inx.blaze.utils.Utils;
import net.fabricmc.api.ModInitializer;

import lombok.Getter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Blaze implements ModInitializer {

	@Getter
	private static Blaze instance;
	@Getter
    public static final Logger logger = LoggerFactory.getLogger("Blaze");

	// Utilities
	public Utils utils;

	// Managers
	private DependencyManager dependencyManager;
	private PlatformManager platformManager;

	@Override
	public void onInitialize() {
		getLogger().info("Initializing Blaze...");
		instance = this;

		this.utils = new Utils();

		this.platformManager = new PlatformManager();
		if(!platformManager.isSupportedPlatform()) {
			getLogger().warn("UNSUPPORTED PLATFORM DETECTED - " + platformManager.getPlatform().toUpperCase());
			getLogger().warn("Blaze does not support this platform. If you run into issues using this platform, do not report them.");
		}
	}

	// WORLD MAP
	// world map with M, shows all loaded chunks of a given world (world detection through world name and server (port?), and if first time player)
	// if player has seed AND server says they have permission, can show everything on the map (slime chunks, structures, etc.)
	// scroll between Y layers with a scroll bar / keybind + scroll, custom set slice size
	// biome and structure outlining
	// optionally reflects block and sky lighting
	// player placed pins, waypoints, notes, saved outlines
	// resource pack support
	// entity display toggles (items, mobs, players, block entities, misc. entities like boats, etc.) and display types (dots, icons, etc.)
	// online player list in map
	// weather icons
	// game & computer time
	// lock direction
	// atlas mode
	// theming and different views
	// UI toggle-ability and customization / location
	// player(s) location
	// pins to hold data for a marker (hook into screenshot viewer to display location screenshot?)
	// 3D mode
	// keybind to disable the map rendering (ctrl + m)

	// WAR MAP
	// block / multiblock structure - round table that displays a section of the map (perimeter markers)

	// MINI MAP
	// toggleable minimap (keybind?)
	// cool transition from minimap to full map

	// SAVE DATA
	// a zip file with the .blazemap extension
	// saves most data via SQL - saving their name, type, source, and coordinates
	// saves
}
