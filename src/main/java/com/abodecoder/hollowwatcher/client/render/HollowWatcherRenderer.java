package com.abodecoder.hollowwatcher.client.render;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

import com.abodecoder.hollowwatcher.entity.HollowWatcherEntity;
import com.abodecoder.hollowwatcher.HollowWatcherMod;

public class HollowWatcherRenderer extends MobEntityRenderer<HollowWatcherEntity, HollowWatcherModel> {

	private static final Identifier TEXTURE = new Identifier(
		HollowWatcherMod.MOD_ID,
		"textures/entity/hollow_watcher.png"
	);

	public HollowWatcherRenderer(EntityRendererFactory.Context ctx) {
		super(ctx, new HollowWatcherModel(ctx.getPart(HollowWatcherModel.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public Identifier getTexture(HollowWatcherEntity entity) {
		return TEXTURE;
	}

	@Override
	protected void scale(HollowWatcherEntity entity, MatrixStack matrices, float tickDelta) {
		matrices.scale(1.0F, 1.0F, 1.0F);
	}
}
