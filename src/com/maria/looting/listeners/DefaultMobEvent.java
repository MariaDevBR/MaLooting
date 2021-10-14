package com.maria.looting.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import com.maria.looting.Main;
import com.maria.looting.api.LootingAPI;

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

		for (ItemStack items : e.getDrops())
			items.setAmount((int) (items.getAmount() * lootingAPI.getLevel(item)));
	}

}
