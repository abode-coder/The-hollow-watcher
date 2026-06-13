package com.abodecoder.hollowwatcher.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.network.ClientPlayerEntity;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerTickMixin {

	@Inject(method = "tick", at = @At("TAIL"))
	private void onClientPlayerTick(CallbackInfo ci) {
		// Handle client-side player tick events
	}
}
