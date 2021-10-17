package com.maria.looting.managers;

import java.util.Map;

import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.maria.looting.Main;
import com.maria.looting.api.LootingAPI;
import com.maria.looting.models.Extras;
import com.maria.looting.models.LootingSettings;
import com.maria.looting.models.Messages;
import com.maria.looting.utils.Format;

import net.minecraft.server.v1_8_R3.NBTTagCompound;

public class StackLootingManager {

	protected Main main;

	private LootingAPI lootingAPI;

	private GiveLootingManager giveLootingManager;

	private LootingSettings lootingSettings;
	private Messages messages;
	private Extras extras;

	public StackLootingManager(Main main) {
		this.main = main;

		lootingAPI = main.getLootingAPI();

		giveLootingManager = main.getGiveLootingManager();

		lootingSettings = main.getLootingSettings();
		messages = main.getMessages();
		extras = main.getExtras();
	}

	public void stackLooting(Player p, ItemStack item) {
		net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
		NBTTagCompound itemCompound = nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound();

		if (!itemCompound.hasKey("Looting"))
			return;

		if (item.getType().name().toLowerCase().contains("sword"))
			return;

		if (limitReached(p, item))
			return;

		sucess(p, item);
	}

	public void sucess(Player p, ItemStack item) {
		double level = lootingAPI.getLevel(item);
		int total = getTotalAmount(p, p.getInventory(), item);

		level = total;

		removeItem(p.getInventory(), item, total);
		giveLootingManager.giveLootingPlayer(p, level);

		if (extras.useSounds)
			p.playSound(p.getLocation(), extras.used, 1.0F, 1.0F);

		p.sendMessage(messages.stackLooting.replace("{leveis}", Format.format(level)));
	}

	public int getTotalAmount(Player p, Inventory inventory, ItemStack item) {
		int amount = 0;

		for (ItemStack items : inventory.all(item.getType()).values()) {
			net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(items);
			NBTTagCompound itemCompound = nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound();

			if (itemCompound.hasKey("Looting")) {
				if (items == item)
					continue;

				int itemsAmount = items.getAmount();
				double level = lootingAPI.getLevel(items) * itemsAmount;
				amount += level;
			}

		}
		return amount;
	}

	public void removeItem(Inventory inventory, ItemStack item, int amount) {
		for (Map.Entry<Integer, ? extends ItemStack> entry : inventory.all(item.getType()).entrySet()) {
			net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(entry.getValue());
			NBTTagCompound itemCompound = nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound();

			if (itemCompound.hasKey("Looting")) {
				ItemStack currentItem = CraftItemStack.asBukkitCopy(nmsItem);

				if (currentItem.getAmount() <= amount) {
					amount -= currentItem.getAmount();
					inventory.clear(entry.getKey().intValue());

				} else {
					currentItem.setAmount(currentItem.getAmount() - amount);
					amount = 0;
				}

			}
			if (amount == 0)
				break;
		}

	}

	public boolean limitReached(Player p, ItemStack item) {
		double level = lootingAPI.getLevel(item);
		int total = getTotalAmount(p, p.getInventory(), item);

		level = total;

		double limit = lootingSettings.limit;
		double levelTotal = level + (total - level);

		if (limit != -1.0D && levelTotal > limit) {
			if (extras.useSounds)
				p.playSound(p.getLocation(), extras.limitReached, 1.0F, 1.0F);

			p.sendMessage(messages.limitReached);
			return true;

		}
		return false;
	}

	public boolean hasPermission(Player p) {
		if (!p.hasPermission("malooting.stackar")) {
			if (extras.stackMessage)
				p.sendMessage(extras.noPerm);
			return true;

		}
		return false;
	}

}
