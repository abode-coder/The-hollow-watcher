package com.abodecoder.hollowwatcher.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.world.World;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;

import com.abodecoder.hollowwatcher.HollowWatcherMod;
import com.abodecoder.hollowwatcher.entity.ai.StalkGoal;
import com.abodecoder.hollowwatcher.entity.ai.TeleportGoal;
import com.abodecoder.hollowwatcher.entity.ai.RandomEventGoal;

public class HollowWatcherEntity extends HostileEntity {
	
	public static EntityType<HollowWatcherEntity> HOLLOW_WATCHER_TYPE;

	private int aggressionLevel = 0;
	private int poseVariation = 0;
	private int ticksSinceLastSound = 0;
	private boolean hasAttackedOnce = false;
	private int bonesCrackingIntensity = 0;

	public HollowWatcherEntity(EntityType<? extends HostileEntity> entityType, World world) {
		super(entityType, world);
		this.experiencePoints = 0;
		this.setNoGravity(true);
	}

	public static DefaultAttributeContainer.Builder createHollowWatcherAttributes() {
		return HostileEntity.createHostileAttributes()
			.add(EntityAttributes.GENERIC_MAX_HEALTH, 50.0)
			.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.35)
			.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 12.0)
			.add(EntityAttributes.GENERIC_FOLLOW_RANGE, 128.0)
			.add(EntityAttributes.GENERIC_ARMOR, 2.0);
	}

	@Override
	protected void initGoals() {
		this.goalSelector.add(0, new StalkGoal(this));
		this.goalSelector.add(1, new RandomEventGoal(this));
		this.goalSelector.add(2, new TeleportGoal(this));
	}

	@Override
	public void tick() {
		super.tick();
		
		if (!this.getWorld().isClient) {
			this.ticksSinceLastSound++;
			
			// Gradually increase aggression based on how many times player has seen this entity
			if (this.getTarget() != null) {
				this.aggressionLevel = Math.min(this.aggressionLevel + 1, 1000);
				
				// Play bone cracking sounds increasingly frequently as aggression grows
				int soundInterval = Math.max(100 - (this.aggressionLevel / 20), 15);
				if (this.ticksSinceLastSound > soundInterval) {
					this.playBoneCrackingSound();
					this.ticksSinceLastSound = 0;
				}
			}
		}
	}

	private void playBoneCrackingSound() {
		if (this.getTarget() != null && this.getWorld() instanceof ServerWorld) {
			double distance = this.distanceTo(this.getTarget());
			if (distance < 64) {
				float volume = (float) Math.max(0.3, 2.0 - (distance / 32.0));
				this.playSound(
					Registries.SOUND_EVENT.get(new Identifier(HollowWatcherMod.MOD_ID, "bones_cracking")),
					volume * (this.aggressionLevel / 1000.0f + 0.3f),
					0.7f + this.random.nextFloat() * 0.6f
				);
			}
		}
	}

	@Override
	public boolean damage(DamageSource source, float amount) {
		// Cannot be damaged by normal means
		return false;
	}

	@Override
	public void writeCustomDataToNbt(NbtCompound nbt) {
		super.writeCustomDataToNbt(nbt);
		nbt.putInt("aggressionLevel", this.aggressionLevel);
		nbt.putInt("poseVariation", this.poseVariation);
		nbt.putBoolean("hasAttackedOnce", this.hasAttackedOnce);
	}

	@Override
	public void readCustomDataFromNbt(NbtCompound nbt) {
		super.readCustomDataFromNbt(nbt);
		this.aggressionLevel = nbt.getInt("aggressionLevel");
		this.poseVariation = nbt.getInt("poseVariation");
		this.hasAttackedOnce = nbt.getBoolean("hasAttackedOnce");
	}

	public int getAggressionLevel() {
		return this.aggressionLevel;
	}

	public void setAggressionLevel(int level) {
		this.aggressionLevel = Math.min(level, 1000);
	}

	public int getPoseVariation() {
		return this.poseVariation;
	}

	public void setPoseVariation(int pose) {
		this.poseVariation = pose % 6; // 6 different poses
	}

	public boolean hasAttackedOnce() {
		return this.hasAttackedOnce;
	}

	public void setHasAttackedOnce(boolean value) {
		this.hasAttackedOnce = value;
	}
}
