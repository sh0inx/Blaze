package io.github.sh0inx.blaze.managers;

import io.github.sh0inx.blaze.Blaze;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlatformManager {

	@Getter
	@Setter
	String platform;

	public boolean isSupportedPlatform() {

		List<String> unsupportedPlatforms = new ArrayList<>(
			Arrays.asList(
				"magma",
				"mohist",
				"arclight",
				"cardboard",
				"sponge",
				"neoforge",
				"forge",
				"bukkit"
			)
		);

		List<String> unsupportedPlatformClasses = new ArrayList<>(
			Arrays.asList("org.magmafoundation.magma.Magma",
				"com.mohistmc.MohistMC",
				"io.izzel.arclight.boot.AbstractBootstrap",
				"org.cardboardpowered.CardboardConfig",
				"org.spongepowered.api.Sponge",
				// insert neoforge
				"net.minecraftforge.fml.server.ServerMain",
				"org.bukkit.Bukkit")
		);

		for (String className : unsupportedPlatformClasses) {
			for(String platform : unsupportedPlatforms) {
				if(className.contains(platform))
					setPlatform(platform);
			}

			if (Blaze.getInstance().utils.classExists(className)) return false;
		}

		//"NeoForge".equals(ModList.get().getMods().filter(it -> it.getModId() == "forge").getDisplayName())

		if (!Blaze.getInstance().utils.classExists("org.quiltmc.loader.impl.QuiltLoaderConfig") && Blaze.getInstance().utils.classExists("net.fabricmc.fabric.mixin.event.entity.EntityMixin"))
			return false;

		return true;
	}
}
