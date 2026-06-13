package com.abodecoder.hollowwatcher.entity.ai;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registries;

import com.abodecoder.hollowwatcher.entity.HollowWatcherEntity;
import com.abodecoder.hollowwatcher.HollowWatcherMod;

public class RandomEventGoal extends Goal {
	private HollowWatcherEntity entity;
	private int tickCounter = 0;

	public RandomEventGoal(HollowWatcherEntity entity) {
		this.entity = entity;
	}

	@Override
	public boolean canStart() {
		return this.entity.getTarget() != null;
	}

	@Override
	public void tick() {
		this.tickCounter++;
		PlayerEntity player = (PlayerEntity) this.entity.getTarget();

		if (player == null) {
			return;
		}

		// Random events occur based on aggression level
		int eventChance = Math.max(3000 - (this.entity.getAggressionLevel() * 2), 500);

		if (this.tickCounter > eventChance) {
			this.triggerRandomEvent(player);
			this.tickCounter = 0;
		}
	}

	private void triggerRandomEvent(PlayerEntity player) {
		int eventType = this.entity.random.nextInt(5);

		switch (eventType) {
			case 0:
				this.playFootsteps(player);
				break;
			case 1:
				this.playDistantScream(player);
				break;
			case 2:
				this.appearBehindPlayer(player);
				break;
			case 3:
				this.playWhisper(player);
				break;
			case 4:
				// Door open event (handled by mixin)
				break;
		}
	}

	private void playFootsteps(PlayerEntity player) {
		double distance = this.entity.distanceTo(player);
		if (distance < 64) {
			float volume = (float) (1.0 - (distance / 64.0)) * 0.5f;
			this.entity.playSound(
				Registries.SOUND_EVENT.get(new Identifier(HollowWatcherMod.MOD_ID, "footsteps")),
				volume,
				0.9f + this.entity.random.nextFloat() * 0.2f
			);
		}
	}

	private void playDistantScream(PlayerEntity player) {
		double distance = this.entity.distanceTo(player);
		if (distance < 80) {
			float volume = (float) (1.5 - (distance / 80.0)) * 0.6f;
			this.entity.playSound(
				Registries.SOUND_EVENT.get(new Identifier(HollowWatcherMod.MOD_ID, "distant_scream")),
				volume,
				0.7f + this.entity.random.nextFloat() * 0.3f
			);
		}
	}

	private void appearBehindPlayer(PlayerEntity player) {
		// Brief jumpscare - teleport behind for a split second
		if (this.entity.random.nextDouble() < 0.3) {
			Vec3d behind = player.getPos().add(0, 0, -2).add(
				(this.entity.random.nextDouble() - 0.5) * 4,
				0,
				0
			);
			this.entity.setPos(behind.getX(), behind.getY(), behind.getZ());
		}
	}

	private void playWhisper(PlayerEntity player) {
		double distance = this.entity.distanceTo(player);
		if (distance < 32) {
			float volume = (float) (1.0 - (distance / 32.0)) * 0.4f;
			this.entity.playSound(
				Registries.SOUND_EVENT.get(new Identifier(HollowWatcherMod.MOD_ID, "whisper")),
				volume,
				0.6f + this.entity.random.nextFloat() * 0.3f
			);
		}
	}

	@Override
	public boolean shouldContinue() {
		return this.entity.getTarget() != null;
	}
}
