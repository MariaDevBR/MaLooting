package com.maria.looting.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import com.maria.looting.Main;

public class ySpawnersEvent implements Listener {

	protected Main main;

	public ySpawnersEvent(Main main) {
		this.main = main;

		Bukkit.getPluginManager().registerEvents(this, main);
	}

}
