package com.abodecoder.hollowwatcher.entity.ai;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;

import com.abodecoder.hollowwatcher.entity.HollowWatcherEntity;

public class TeleportGoal extends Goal {
	private HollowWatcherEntity entity;
	private int tickCounter = 0;

	public TeleportGoal(HollowWatcherEntity entity) {
		this.entity = entity;
	}

	@Override
	public boolean canStart() {
		PlayerEntity player = (PlayerEntity) this.entity.getTarget();
		return player != null && this.entity.getAggressionLevel() > 600;
	}

	@Override
	public void tick() {
		this.tickCounter++;
		PlayerEntity player = (PlayerEntity) this.entity.getTarget();

		if (player == null) {
			return;
		}

		// As aggression increases, creature teleports closer and appears more frequently
		int teleportChance = Math.max(1500 - (this.entity.getAggressionLevel() * 1), 300);

		if (this.tickCounter > teleportChance && this.entity.random.nextDouble() < 0.4) {
			this.teleportNearPlayer(player);
			this.tickCounter = 0;
		}
	}

	private void teleportNearPlayer(PlayerEntity player) {
		// Teleport to a position the player isn't directly looking at
		double angle = this.entity.random.nextDouble() * Math.PI * 2;
		double distance = 15 + this.entity.random.nextDouble() * 20;

		Vec3d newPos = player.getPos().add(
			Math.cos(angle) * distance,
			-2 + this.entity.random.nextDouble() * 3,
			Math.sin(angle) * distance
		);

		this.entity.setPos(newPos.getX(), newPos.getY(), newPos.getZ());
	}

	@Override
	public boolean shouldContinue() {
		PlayerEntity player = (PlayerEntity) this.entity.getTarget();
		return player != null && this.entity.getAggressionLevel() > 600;
	}
}
