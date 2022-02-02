package net.snavesutit.configurable_boats;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.snavesutit.configurable_boats.config.ConfigManager;

public class ConfigurableBoats implements ModInitializer {
	@Override
	public void onInitialize() {

		ServerLifecycleEvents.SERVER_STARTING.register((s) -> ConfigManager.loadConfig());
		ServerLifecycleEvents.START_DATA_PACK_RELOAD.register((a, b) -> ConfigManager.loadConfig());
		ConfigManager.loadConfig();

		System.out.println("Boat moment");
	}
}
