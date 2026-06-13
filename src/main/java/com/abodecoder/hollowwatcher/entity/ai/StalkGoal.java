package com.abodecoder.hollowwatcher.entity.ai;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;

import com.abodecoder.hollowwatcher.entity.HollowWatcherEntity;

public class StalkGoal extends Goal {
	private HollowWatcherEntity entity;
	private int updateCounter = 0;

	public StalkGoal(HollowWatcherEntity entity) {
		this.entity = entity;
	}

	@Override
	public boolean canStart() {
		PlayerEntity player = this.entity.getWorld().getClosestPlayer(
			this.entity.getX(), this.entity.getY(), this.entity.getZ(), 128, false
		);
		return player != null && !player.isSpectator();
	}

	@Override
	public void start() {
		this.updateCounter = 0;
	}

	@Override
	public void tick() {
		PlayerEntity player = this.entity.getWorld().getClosestPlayer(
			this.entity.getX(), this.entity.getY(), this.entity.getZ(), 128, false
		);

		if (player == null) {
			return;
		}

		this.updateCounter++;

		// Randomly change pose
		if (this.updateCounter % 40 == 0) {
			this.entity.setPoseVariation(this.entity.random.nextInt(6));
		}

		// Stalk from a distance (30-80 blocks away)
		double distance = this.entity.distanceTo(player);
		if (distance < 30) {
			// Move away
			this.moveAway(player);
		} else if (distance > 80) {
			// Move closer
			this.moveToward(player);
		} else {
			// Circle around the player unpredictably
			this.circlePlayer(player);
		}
	}

	private void moveToward(PlayerEntity player) {
		Vec3d direction = player.getPos().subtract(this.entity.getPos()).normalize();
		this.entity.setVelocity(direction.multiply(0.2));
	}

	private void moveAway(PlayerEntity player) {
		Vec3d direction = this.entity.getPos().subtract(player.getPos()).normalize();
		this.entity.setVelocity(direction.multiply(0.15));
	}

	private void circlePlayer(PlayerEntity player) {
		if (this.updateCounter % 60 == 0) {
			double angle = this.entity.random.nextDouble() * Math.PI * 2;
			double radius = 40 + this.entity.random.nextDouble() * 20;
			Vec3d circlePos = new Vec3d(
				Math.cos(angle) * radius,
				(this.entity.random.nextDouble() - 0.5) * 10,
				Math.sin(angle) * radius
			).add(player.getPos());

			Vec3d direction = circlePos.subtract(this.entity.getPos()).normalize().multiply(0.1);
			this.entity.setVelocity(direction);
		}
	}

	@Override
	public boolean shouldContinue() {
		PlayerEntity player = this.entity.getWorld().getClosestPlayer(
			this.entity.getX(), this.entity.getY(), this.entity.getZ(), 128, false
		);
		return player != null && !player.isSpectator();
	}
}
