package com.maria.looting.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import com.maria.looting.Main;
import com.maria.looting.api.LootingAPI;
import com.maria.looting.managers.LootingManager;
import com.maria.looting.models.Messages;
import com.maria.looting.utils.Format;

import net.minecraft.server.v1_8_R3.NBTTagCompound;

public class LootingEvents implements Listener {

	protected Main main;

	private LootingAPI lootingAPI;

	private LootingManager lootingManager;

	private Messages messages;

	public LootingEvents(Main main) {
		this.main = main;

		Bukkit.getPluginManager().registerEvents(this, main);

		lootingAPI = main.getLootingAPI();

		lootingManager = main.getLootingManager();

		messages = main.getMessages();
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	void playerUseLooting(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();

		ItemStack cursor = e.getCursor();
		ItemStack item = e.getCurrentItem();

		if (cursor == null || cursor.getType() == Material.AIR || item == null || item.getType() == Material.AIR)
			return;

		net.minecraft.server.v1_8_R3.ItemStack nmsCursor = CraftItemStack.asNMSCopy(cursor);
		NBTTagCompound cursorCompound = nmsCursor.hasTag() ? nmsCursor.getTag() : new NBTTagCompound();

		if (!item.getType().name().toLowerCase().contains("sword"))
			return;

		if (!cursorCompound.hasKey("Looting"))
			return;

		net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
		NBTTagCompound itemCompound = nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound();

		double levelLooting = lootingAPI.getLevel(cursor);

		levelLooting *= cursor.getAmount();

		if (lootingManager.limitReached(p, item, cursor))
			return;

		if (itemCompound.hasKey("Looting")) {
			lootingManager.lootingUsed(p, cursor, item);
			e.setCancelled(true);
			e.setCursor(null);
			e.setCurrentItem(lootingAPI.newItem(item, levelLooting));

			p.sendMessage(messages.used.replace("{leveis}", Format.format(levelLooting)).replace("{level}",
					Format.format(lootingAPI.getTotalLevel(cursor, item))));
			return;

		}
		lootingManager.lootingUsed(p, cursor, item);
		e.setCancelled(true);
		e.setCursor(null);
		e.setCurrentItem(lootingAPI.newItem(item, levelLooting));

		p.sendMessage(messages.used.replace("{leveis}", Format.format(levelLooting)).replace("{level}",
				Format.format(lootingAPI.getTotalLevel(cursor, item))));
	}

	@EventHandler
	void blockPlace(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		ItemStack item = p.getItemInHand();

		net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
		NBTTagCompound itemCompound = nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound();

		if (itemCompound.hasKey("Looting"))
			e.setCancelled(true);
	}

}
