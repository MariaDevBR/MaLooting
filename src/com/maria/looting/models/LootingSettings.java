package com.maria.looting.models;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import com.maria.looting.Main;

public class LootingSettings {

	protected Main main;

	private FileConfiguration config;

	public String name;
	public List<String> lore;
	public Material material;
	public double limit;
	public int data;
	public boolean glow;

	public LootingSettings(Main main) {
		this.main = main;

		config = main.getConfig();

		glow = config.getBoolean("Looting.Item.Glow");

		name = config.getString("Looting.Item.Nome").replace("&", "§");
		lore = config.getStringList("Looting.Item.Lore");
		material = Material.valueOf(config.getString("Looting.Item.Material").split(":")[0]);
		data = Integer.parseInt(config.getString("Looting.Item.Material").split(":")[1]);

		limit = config.getDouble("Looting.Limite");
	}

}
