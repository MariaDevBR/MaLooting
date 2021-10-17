package com.maria.looting.managers;

import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.maria.looting.Main;
import com.maria.looting.api.LootingAPI;
import com.maria.looting.models.Extras;
import com.maria.looting.models.LootingSettings;
import com.maria.looting.models.Messages;
import com.maria.looting.utils.Format;

import net.minecraft.server.v1_8_R3.NBTTagCompound;

public class LootingManager {

	protected Main main;

	private LootingAPI lootingAPI;

	private GiveLootingManager giveLootingManager;

	private LootingSettings lootingSettings;
	private Messages messages;
	private Extras extras;

	public LootingManager(Main main) {
		this.main = main;

		lootingAPI = main.getLootingAPI();
		lootingSettings = main.getLootingSettings();
		messages = main.getMessages();
		extras = main.getExtras();
	}

	@SuppressWarnings("deprecation")
	public void lootingUsed(Player p, ItemStack cursor, ItemStack item) {
		double levelBook = lootingAPI.getLevel(cursor);
		levelBook *= cursor.getAmount();

		if (extras.useTitle)
			p.sendTitle(extras.titleLine1.replace("{leveis}", Format.format(levelBook)),
					extras.titleLine2.replace("{leveis}", Format.format(levelBook)));

		if (extras.useSounds)
			p.playSound(p.getLocation(), extras.used, 1.0F, 1.0F);
	}

	public boolean limitReached(Player p, ItemStack item, ItemStack cursor) {
		double limit = lootingSettings.limit;

		double level = lootingAPI.getTotalLevel(item, cursor);

		if (limit != -1.0D && level >= limit) {
			if (extras.useSounds)
				p.playSound(p.getLocation(), extras.limitReached, 1.0F, 1.0F);

			p.sendMessage(messages.limitReached);
			return true;

		}
		return false;
	}

	public void convertedLooting(Player p, ItemStack item) {
		net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
		NBTTagCompound itemCompound = nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound();

		int levelDefaultLooting = item.getEnchantmentLevel(Enchantment.LOOT_BONUS_MOBS);
		double levelLooting = lootingAPI.getLevel(item);

		double totalLevel = levelLooting + levelDefaultLooting;

		if (item.containsEnchantment(Enchantment.LOOT_BONUS_MOBS))
			item.removeEnchantment(Enchantment.LOOT_BONUS_MOBS);

		if (itemCompound.hasKey("Looting"))
			lootingAPI.newItem(item, 0);

		p.setItemInHand(new ItemStack(item.getType()));
		giveLootingManager.giveLootingPlayer(p, totalLevel);
		p.sendMessage(messages.lootingConverted.replace("{leveis}", Format.format(totalLevel)));
	}

	public boolean isSword(Player p, ItemStack item) {
		if (item == null || !item.getType().name().toLowerCase().contains("sword")) {
			p.sendMessage(messages.swordInHand);
			return true;

		}
		return false;
	}

	public boolean hasLooting(Player p, ItemStack item) {
		net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
		NBTTagCompound itemCompound = nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound();

		if (!(itemCompound.hasKey("Looting") || item.containsEnchantment(Enchantment.LOOT_BONUS_MOBS))) {
			p.sendMessage(messages.noLootingOnSword);
			return true;

		}
		return false;
	}

}
