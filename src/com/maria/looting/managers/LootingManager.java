package com.maria.looting.managers;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.maria.looting.Main;
import com.maria.looting.api.LootingAPI;
import com.maria.looting.models.Extras;
import com.maria.looting.models.LootingSettings;
import com.maria.looting.models.Messages;
import com.maria.looting.utils.Format;

public class LootingManager {

	protected Main main;

	private LootingAPI lootingAPI;

	private LootingSettings lootingSettings;
	private Messages messages;
	private Extras extras;

	public LootingManager(Main main) {
		this.main = main;

		lootingAPI = main.getLootingAPI();
	}

	@SuppressWarnings("deprecation")
	public void lootingUsed(Player p, ItemStack cursor, ItemStack item) {
		lootingSettings = main.getLootingSettings();
		extras = main.getExtras();

		double levelBook = lootingAPI.getLevel(cursor);
		levelBook *= cursor.getAmount();

		if (extras.useTitle)
			p.sendTitle(extras.titleLine1.replace("{leveis}", Format.format(levelBook)),
					extras.titleLine2.replace("{leveis}", Format.format(levelBook)));

		if (extras.useSounds)
			p.playSound(p.getLocation(), extras.used, 1.0F, 1.0F);
	}

	public boolean limitReached(Player p, ItemStack item, ItemStack cursor) {
		lootingSettings = main.getLootingSettings();
		messages = main.getMessages();
		extras = main.getExtras();

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

}
