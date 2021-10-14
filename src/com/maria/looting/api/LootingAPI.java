package com.maria.looting.api;

import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import com.maria.looting.Main;
import com.maria.looting.utils.Format;
import com.maria.looting.utils.ItemBuilder;

import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagDouble;

public class LootingAPI {

	protected Main main;

	public LootingAPI(Main main) {
		this.main = main;
	}

	public ItemStack newItem(ItemStack item, double level) {
		net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);

		NBTTagCompound itemCompound = nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound();
		double levelUnited = level + getLooting(item);

		itemCompound.set("Looting", new NBTTagDouble(levelUnited));

		nmsItem.setTag(itemCompound);

		ItemStack craftItem = CraftItemStack.asBukkitCopy(nmsItem);

		String levelFormatted = Format.format(levelUnited);

		ItemStack itemBuilded = new ItemBuilder(craftItem).setLore("§7Looting " + levelFormatted).build();

		return itemBuilded;
	}

	public double getLooting(ItemStack item) {
		net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);

		NBTTagCompound itemCompound = nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound();

		double level = itemCompound.getDouble("Looting");

		return level;
	}

	public double getLevel(ItemStack item) {
		return getLooting(item);
	}

	public double getTotalLevel(ItemStack cursor, ItemStack item) {
		double levelCursor = getLevel(cursor);
		levelCursor *= cursor.getAmount();

		return levelCursor + getLevel(item);
	}

}
