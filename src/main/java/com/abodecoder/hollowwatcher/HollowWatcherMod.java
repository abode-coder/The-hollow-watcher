package com.abodecoder.hollowwatcher;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.abodecoder.hollowwatcher.util.HollowWatcherRegistry;
import com.abodecoder.hollowwatcher.world.HollowRealmManager;
import com.abodecoder.hollowwatcher.world.PlayerStalkerData;

public class HollowWatcherMod implements ModInitializer {
	public static final String MOD_ID = "hollow-watcher";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("The Hollow Watcher awakens...");
		
		HollowWatcherRegistry.registerEntities();
		HollowWatcherRegistry.registerItems();
		HollowWatcherRegistry.registerSounds();
		HollowWatcherRegistry.registerCriteria();
		
		ServerLifecycleEvents.SERVER_STARTED.register(server -> {
			HollowRealmManager.initializeServer(server);
		});
		
		ServerWorldEvents.LOAD.register((server, world) -> {
			PlayerStalkerData.loadWorldData(world);
		});
	}
}
