package com.abodecoder.hollowwatcher.client.render;

import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import net.minecraft.client.render.entity.EntityModelLayer;

import com.abodecoder.hollowwatcher.HollowWatcherMod;
import com.abodecoder.hollowwatcher.entity.HollowWatcherEntity;

public class HollowWatcherModel extends EntityModel<HollowWatcherEntity> {

	public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(
		new Identifier(HollowWatcherMod.MOD_ID, "hollow_watcher"),
		"main"
	);

	private final ModelPart root;
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart leftArm;
	private final ModelPart rightArm;
	private final ModelPart leftLeg;
	private final ModelPart rightLeg;

	public HollowWatcherModel(ModelPart root) {
		super(RenderLayer::getEntityCutout);
		this.root = root;
		this.body = root.getChild("body");
		this.head = root.getChild("head");
		this.leftArm = root.getChild("left_arm");
		this.rightArm = root.getChild("right_arm");
		this.leftLeg = root.getChild("left_leg");
		this.rightLeg = root.getChild("right_leg");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPart.Cuboid cuboid = new ModelPart.Cuboid(0, 0, 0, 0, 0, 0, 0, 0, 0, false, 0, 0);

		// Body - extremely thin
		modelData.getRoot().addChild(
			"body",
			ModelPart.Builder.create()
				.uv(0, 16)
				.cuboid(-2.0F, 0.0F, -1.0F, 4.0F, 12.0F, 2.0F, new Dilation(0.0F))
				.build()
		);

		// Head - dark, shadowy
		modelData.getRoot().addChild(
			"head",
			ModelPart.Builder.create()
				.uv(0, 0)
				.cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F))
				.build()
		);

		// Left Arm - unnaturally long
		modelData.getRoot().addChild(
			"left_arm",
			ModelPart.Builder.create()
				.uv(16, 16)
				.cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 16.0F, 2.0F, new Dilation(0.0F))
				.build()
		);

		// Right Arm - unnaturally long
		modelData.getRoot().addChild(
			"right_arm",
			ModelPart.Builder.create()
				.uv(24, 16)
				.cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 16.0F, 2.0F, new Dilation(0.0F))
				.build()
		);

		// Left Leg - extremely thin
		modelData.getRoot().addChild(
			"left_leg",
			ModelPart.Builder.create()
				.uv(8, 16)
				.cuboid(-0.5F, 0.0F, -0.5F, 1.0F, 12.0F, 1.0F, new Dilation(0.0F))
				.build()
		);

		// Right Leg - extremely thin
		modelData.getRoot().addChild(
			"right_leg",
			ModelPart.Builder.create()
				.uv(12, 16)
				.cuboid(-0.5F, 0.0F, -0.5F, 1.0F, 12.0F, 1.0F, new Dilation(0.0F))
				.build()
		);

		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	public void setAngles(
		HollowWatcherEntity entity,
		float limbSwing,
		float limbSwingAmount,
		float ageInTicks,
		float netHeadYaw,
		float headPitch
	) {
		// Custom animation logic based on pose variation
		int pose = entity.getPoseVariation();

		switch (pose) {
			case 0: // Head tilted sideways
				this.head.yaw = (float) Math.toRadians(45);
				break;
			case 1: // Arms hanging unnaturally low
				this.leftArm.pitch = (float) Math.toRadians(180);
				this.rightArm.pitch = (float) Math.toRadians(180);
				break;
			case 2: // One arm bent backward
				this.leftArm.pitch = (float) Math.toRadians(-90);
				break;
			case 3: // Body twisted
				this.body.yaw = (float) Math.toRadians(30);
				this.head.yaw = (float) Math.toRadians(-30);
				break;
			case 4: // Standing completely still
				this.leftArm.pitch = 0;
				this.rightArm.pitch = 0;
				this.head.yaw = 0;
				break;
			case 5: // Crawling pose
				this.body.pitch = (float) Math.toRadians(45);
				this.leftArm.pitch = (float) Math.toRadians(90);
				this.rightArm.pitch = (float) Math.toRadians(90);
				break;
		}
	}

	@Override
	public ModelPart getPart() {
		return this.root;
	}
}
