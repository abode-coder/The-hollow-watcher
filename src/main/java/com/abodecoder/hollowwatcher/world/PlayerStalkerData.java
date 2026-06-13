package com.abodecoder.hollowwatcher.world;

import net.minecraft.world.World;
import net.minecraft.nbt.NbtCompound;

public class PlayerStalkerData {

	public static void loadWorldData(World world) {
		// Load stalker data for the world
	}

	public static void saveWorldData(World world) {
		// Save stalker data for the world
	}

	public static int getStalkerLevel(String playerUUID) {
		// Returns current stalking level (0-100)
		return 0;
	}

	public static void incrementStalkerLevel(String playerUUID) {
		// Increase stalker aggression for this player
	}
}
