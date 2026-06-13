package com.abodecoder.hollowwatcher.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.server.network.ServerPlayerEntity;

@Mixin(ServerPlayerEntity.class)
public class PlayerTickMixin {

	@Inject(method = "tick", at = @At("TAIL"))
	private void onPlayerTick(CallbackInfo ci) {
		// Handle server-side player tick events
	}
}
