package com.maria.looting.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.maria.looting.Main;
import com.maria.looting.managers.StackLootingManager;

import net.minecraft.server.v1_8_R3.NBTTagCompound;

public class StackLootingEvent implements Listener {

	protected Main main;

	private StackLootingManager stackLootingManager;

	public StackLootingEvent(Main main) {
		this.main = main;

		Bukkit.getPluginManager().registerEvents(this, main);

		stackLootingManager = main.getStackLootingManager();
	}

	@EventHandler
	void stackLooting(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		Action action = e.getAction();
		ItemStack item = p.getItemInHand();

		if (p.isSneaking()) {
			if (action == Action.RIGHT_CLICK_AIR) {
				if (item == null || item.getType() == Material.AIR)
					return;

				net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
				NBTTagCompound itemCompound = nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound();

				if (!itemCompound.hasKey("Looting"))
					return;

				if (item.getType().name().toLowerCase().contains("sword"))
					return;
				
				if (stackLootingManager.hasPermission(p))
					return;

				stackLootingManager.stackLooting(p, item);
			}

		}

	}

}
