package com.abodecoder.hollowwatcher.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.render.WorldRenderer;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {

	@Inject(method = "render", at = @At("HEAD"))
	private void onWorldRender(CallbackInfo ci) {
		// Handle world rendering - can add fog effects here
	}
}
