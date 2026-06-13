package com.abodecoder.hollowwatcher.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;

public class HolyItem extends Item {
	
	public HolyItem(Item.Settings settings) {
		super(settings);
	}

	@Override
	public ItemStack getDefaultStack() {
		ItemStack stack = super.getDefaultStack();
		NbtCompound tag = new NbtCompound();
		tag.putBoolean("isHoly", true);
		stack.setNbt(tag);
		return stack;
	}

	@Override
	public Text getName(ItemStack stack) {
		return Text.literal("Holy Cross");
	}

	@Override
	public boolean hasGlint(ItemStack stack) {
		return true;
	}
}
