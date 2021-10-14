package com.maria.looting.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import com.maria.looting.Main;
import com.maria.looting.api.LootingAPI;
import com.storm.spawners.events.SpawnerMobDeathEvent;

public class StormEvent implements Listener {

	protected Main main;

	private LootingAPI lootingAPI;

	public StormEvent(Main main) {
		this.main = main;

		Bukkit.getPluginManager().registerEvents(this, main);

		lootingAPI = main.getLootingAPI();
	}

	@EventHandler
	void mobDeath(SpawnerMobDeathEvent e) {
		Player p = e.getKiller();
		ItemStack item = p.getItemInHand();

		double lootingLevel = lootingAPI.getLevel(item);

		e.setLootingMultiplier(lootingLevel);
	}

}
