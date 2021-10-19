package com.maria.looting.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import com.dont.spawnersv2.models.AtlasMobDeathEvent;
import com.maria.looting.Main;
import com.maria.looting.api.LootingAPI;

import net.minecraft.server.v1_8_R3.NBTTagCompound;

public class AtlasMobEvent implements Listener {

	protected Main main;

	private LootingAPI lootingAPI;

	public AtlasMobEvent(Main main) {
		this.main = main;

		Bukkit.getPluginManager().registerEvents(this, main);

		lootingAPI = main.getLootingAPI();
	}

	@EventHandler
	void mobDeath(AtlasMobDeathEvent e) {
		Player p = e.getKiller();
		ItemStack item = p.getItemInHand();

		if (item == null || item.getType() == Material.AIR)
			return;

		net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
		NBTTagCompound itemCompound = nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound();

		if (!itemCompound.hasKey("Looting"))
			return;

		double drops = e.getDrops();
		double lootingLevel = lootingAPI.getLevel(item);

		e.setDrops(drops * lootingLevel);
	}

}
