package com.maria.looting.models;

import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;

import com.maria.looting.Main;

public class Extras {

	protected Main main;

	private FileConfiguration config;

	public String titleLine1;
	public String titleLine2;
	public Sound used;
	public Sound limitReached;
	public boolean useTitle;
	public boolean useSounds;

	public Extras(Main main) {
		this.main = main;

		config = main.getConfig();

		titleLine1 = config.getString("Extras.Title.Linha1").replace("&", "§");
		titleLine2 = config.getString("Extras.Title.Linha2").replace("&", "§");

		used = Sound.valueOf(config.getString("Extras.Sons.Utilizou"));
		limitReached = Sound.valueOf(config.getString("Extras.Sons.Limite atingido"));

		useTitle = config.getBoolean("Extras.Title ativar");
		useSounds = config.getBoolean("Extras.Sons ativar");
	}

}
