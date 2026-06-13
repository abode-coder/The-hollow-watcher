package com.abodecoder.hollowwatcher;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientEntityEvents;

import com.abodecoder.hollowwatcher.client.render.HollowWatcherRenderer;
import com.abodecoder.hollowwatcher.client.render.HollowWatcherModel;
import com.abodecoder.hollowwatcher.entity.HollowWatcherEntity;
import com.abodecoder.hollowwatcher.client.HollowWatcherClientEvents;

public class HollowWatcherClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		HollowWatcherMod.LOGGER.info("Hollow Watcher client initialized...");
		
		EntityRendererRegistry.register(
			HollowWatcherEntity.HOLLOW_WATCHER_TYPE,
			(context) -> new HollowWatcherRenderer(context)
		);

		EntityModelLayerRegistry.registerModelLayer(
			HollowWatcherModel.LAYER_LOCATION,
			HollowWatcherModel::getTexturedModelData
		);

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			HollowWatcherClientEvents.onClientTick(client);
		});

		ClientEntityEvents.ENTITY_LOAD.register((entity, world) -> {
			HollowWatcherClientEvents.onEntityLoad(entity, world);
		});
	}
}
