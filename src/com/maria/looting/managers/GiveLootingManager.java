package com.maria.looting.managers;

import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.maria.looting.Main;
import com.maria.looting.models.LootingSettings;
import com.maria.looting.utils.Format;
import com.maria.looting.utils.ItemBuilder;
import com.maria.looting.utils.SkullAPI;

import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagDouble;

public class GiveLootingManager {

	protected Main main;

	private LootingSettings lootingSettings;

	public GiveLootingManager(Main main) {
		this.main = main;
	}

	public void giveLootingPlayer(Player target, double amount) {
		lootingSettings = main.getLootingSettings();

		List<String> lore = lootingSettings.lore;
		lore = lore.stream().map(l -> l.replace("&", "§").replace("{level}", Format.format(amount)))
				.collect(Collectors.toList());

		if (lootingSettings.customSkull) {
			ItemStack item = new ItemStack(SkullAPI.getSkull(lootingSettings.skull));
			net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);

			NBTTagCompound itemCompound = nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound();
			itemCompound.set("Looting", new NBTTagDouble(amount));

			nmsItem.setTag(itemCompound);

			ItemStack craftItem = CraftItemStack.asBukkitCopy(nmsItem);

			ItemStack itemBuilded = new ItemBuilder(craftItem).setName(lootingSettings.name).setLore(lore).build();

			target.getInventory().addItem(itemBuilded);

		} else {
			ItemStack item = new ItemStack(lootingSettings.material, 1, (short) lootingSettings.data);
			net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);

			NBTTagCompound itemCompound = nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound();
			itemCompound.set("Looting", new NBTTagDouble(amount));

			nmsItem.setTag(itemCompound);

			ItemStack craftItem = CraftItemStack.asBukkitCopy(nmsItem);

			ItemStack itemBuilded = new ItemBuilder(craftItem).setName(lootingSettings.name).setLore(lore)
					.addGlow(lootingSettings.glow).build();

			target.getInventory().addItem(itemBuilded);
		}

	}

}
