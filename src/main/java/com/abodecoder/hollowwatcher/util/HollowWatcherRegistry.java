package com.abodecoder.hollowwatcher.util;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import com.abodecoder.hollowwatcher.HollowWatcherMod;
import com.abodecoder.hollowwatcher.entity.HollowWatcherEntity;
import com.abodecoder.hollowwatcher.item.HolyItem;
import com.abodecoder.hollowwatcher.item.RitualCandle;
import com.abodecoder.hollowwatcher.item.AncientPages;
import com.abodecoder.hollowwatcher.item.BlessedIronChain;
import com.abodecoder.hollowwatcher.item.HollowRealmFragment;
import com.abodecoder.hollowwatcher.item.HollowWatcherBook;

public class HollowWatcherRegistry {
	
	public static void registerEntities() {
		Registry.register(Registries.ENTITY_TYPE, 
			new Identifier(HollowWatcherMod.MOD_ID, "hollow_watcher"),
			FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, HollowWatcherEntity::new)
				.dimensions(EntityDimensions.fixed(0.6f, 3.5f))
				.trackRangeBlocks(128)
				.trackedUpdateRate(3)
				.build()
		);
		
		HollowWatcherEntity.HOLLOW_WATCHER_TYPE = Registries.ENTITY_TYPE.get(
			new Identifier(HollowWatcherMod.MOD_ID, "hollow_watcher")
		);
	}

	public static void registerItems() {
		Registry.register(Registries.ITEM, 
			new Identifier(HollowWatcherMod.MOD_ID, "holy_cross"),
			new HolyItem(new Item.Settings().maxCount(1))
		);
		
		Registry.register(Registries.ITEM,
			new Identifier(HollowWatcherMod.MOD_ID, "ritual_candle"),
			new RitualCandle(new Item.Settings().maxCount(64))
		);
		
		Registry.register(Registries.ITEM,
			new Identifier(HollowWatcherMod.MOD_ID, "ancient_pages"),
			new AncientPages(new Item.Settings().maxCount(64))
		);
		
		Registry.register(Registries.ITEM,
			new Identifier(HollowWatcherMod.MOD_ID, "blessed_iron_chain"),
			new BlessedIronChain(new Item.Settings().maxCount(64))
		);
		
		Registry.register(Registries.ITEM,
			new Identifier(HollowWatcherMod.MOD_ID, "hollow_realm_fragment"),
			new HollowRealmFragment(new Item.Settings().maxCount(64))
		);
		
		Registry.register(Registries.ITEM,
			new Identifier(HollowWatcherMod.MOD_ID, "hollow_watcher_book"),
			new HollowWatcherBook(new Item.Settings().maxCount(1))
		);
	}

	public static void registerSounds() {
		Registry.register(Registries.SOUND_EVENT,
			new Identifier(HollowWatcherMod.MOD_ID, "bones_cracking"),
			SoundEvent.of(new Identifier(HollowWatcherMod.MOD_ID, "bones_cracking"))
		);
		
		Registry.register(Registries.SOUND_EVENT,
			new Identifier(HollowWatcherMod.MOD_ID, "distant_scream"),
			SoundEvent.of(new Identifier(HollowWatcherMod.MOD_ID, "distant_scream"))
		);
		
		Registry.register(Registries.SOUND_EVENT,
			new Identifier(HollowWatcherMod.MOD_ID, "footsteps"),
			SoundEvent.of(new Identifier(HollowWatcherMod.MOD_ID, "footsteps"))
		);
		
		Registry.register(Registries.SOUND_EVENT,
			new Identifier(HollowWatcherMod.MOD_ID, "whisper"),
			SoundEvent.of(new Identifier(HollowWatcherMod.MOD_ID, "whisper"))
		);
	}

	public static void registerCriteria() {
		// Custom advancement criteria can be registered here
	}
}
