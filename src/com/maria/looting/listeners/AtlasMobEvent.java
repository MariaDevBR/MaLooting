package com.maria.looting.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import com.dont.spawnersv2.models.AtlasMobDeathEvent;
import com.maria.looting.Main;
import com.maria.looting.api.LootingAPI;

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

		double drops = e.getDrops();
		double lootingLevel = lootingAPI.getLevel(item);

		e.setDrops(drops * lootingLevel);
	}

}
