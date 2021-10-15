package com.maria.looting.models;

import org.bukkit.configuration.file.FileConfiguration;

import com.maria.looting.Main;
import com.maria.looting.utils.Format;

public class Messages {

	protected Main main;

	private FileConfiguration config;

	private LootingSettings lootingSettings;

	public String noPerm;
	public String incompleteCommand;
	public String playerOff;
	public String invalidNumber;

	public String used;
	public String limitReached;

	public String swordInHand;
	public String noLootingOnSword;
	public String lootingConverted;

	public Messages(Main main) {
		this.main = main;

		config = main.getConfig();

		lootingSettings = main.getLootingSettings();

		noPerm = config.getString("Mensagens.Sem permissao").replace("&", "§");
		incompleteCommand = config.getString("Mensagens.Comando incompleto").replace("&", "§");
		playerOff = config.getString("Mensagens.Player off").replace("&", "§");
		invalidNumber = config.getString("Mensagens.Numero invalido").replace("&", "§");

		used = config.getString("Mensagens.Utilizou").replace("&", "§").replace("{maxlevel}",
				Format.format(lootingSettings.limit));
		limitReached = config.getString("Mensagens.Limite atingido").replace("&", "§").replace("{maxlevel}",
				Format.format(lootingSettings.limit));

		swordInHand = config.getString("Mensagens.Mao vazia").replace("&", "§");
		noLootingOnSword = config.getString("Mensagens.Sem looting").replace("&", "§");
		lootingConverted = config.getString("Mensagens.Looting convertida").replace("&", "§");
	}

}
