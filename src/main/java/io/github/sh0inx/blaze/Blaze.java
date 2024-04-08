package io.github.sh0inx.blaze;

import net.fabricmc.api.ModInitializer;

import lombok.Getter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Blaze implements ModInitializer {

	@Getter
	private static Blaze instance;
    public static final Logger LOGGER = LoggerFactory.getLogger("Blaze");

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing Blaze...");
		instance = this;
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