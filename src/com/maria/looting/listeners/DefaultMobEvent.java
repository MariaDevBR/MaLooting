package com.maria.looting.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import com.maria.looting.Main;
import com.maria.looting.api.LootingAPI;

import net.minecraft.server.v1_8_R3.NBTTagCompound;

public class DefaultMobEvent implements Listener {

	protected Main main;

	private LootingAPI lootingAPI;

	public DefaultMobEvent(Main main) {
		this.main = main;

		Bukkit.getPluginManager().registerEvents(this, main);

		lootingAPI = main.getLootingAPI();
	}

	@EventHandler
	void mobDeath(EntityDeathEvent e) {
		if (e.getEntity() instanceof Player)
			return;

		if (!(e.getEntity().getKiller() instanceof Player))
			return;

		Player p = e.getEntity().getKiller();
		ItemStack item = p.getItemInHand();

		if (item == null || item.getType() == Material.AIR)
			return;

		net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
		NBTTagCompound itemCompound = nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound();

		if (!itemCompound.hasKey("Looting"))
			return;

		for (ItemStack items : e.getDrops())
			items.setAmount((int) (items.getAmount() * lootingAPI.getLevel(item)));
	}

}
