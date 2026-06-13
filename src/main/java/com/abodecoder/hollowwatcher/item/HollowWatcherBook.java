package com.abodecoder.hollowwatcher.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.nbt.NbtCompound;

public class HollowWatcherBook extends Item {
	public HollowWatcherBook(Item.Settings settings) {
		super(settings);
	}

	@Override
	public ItemStack getDefaultStack() {
		ItemStack stack = super.getDefaultStack();
		NbtCompound tag = new NbtCompound();
		tag.putString("title", "The Hollow Watcher's Tome");
		tag.putInt("progress", 0);
		stack.setNbt(tag);
		return stack;
	}

	@Override
	public Text getName(ItemStack stack) {
		return Text.literal("The Hollow Watcher's Tome");
	}

	@Override
	public boolean hasGlint(ItemStack stack) {
		return true;
	}
}
