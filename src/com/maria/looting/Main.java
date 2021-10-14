package com.maria.looting;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import com.maria.looting.api.LootingAPI;
import com.maria.looting.commands.LootingCommand;
import com.maria.looting.commands.MaCommand;
import com.maria.looting.hook.Hooks;
import com.maria.looting.listeners.AtlasEvent;
import com.maria.looting.listeners.LootingEvents;
import com.maria.looting.listeners.StormEvent;
import com.maria.looting.listeners.ySpawnersEvent;
import com.maria.looting.managers.GiveLootingManager;
import com.maria.looting.managers.LootingManager;
import com.maria.looting.models.Extras;
import com.maria.looting.models.LootingSettings;
import com.maria.looting.models.Messages;

public class Main extends JavaPlugin {

	private GiveLootingManager giveLootingManager;

	private LootingAPI lootingAPI;

	private LootingManager lootingManager;
	private LootingSettings lootingSettings;
	private Messages messages;
	private Extras extras;

	@Override
	public void onEnable() {
		saveDefaultConfig();
		ConsoleCommandSender consoleMessage = Bukkit.getConsoleSender();
		consoleMessage.sendMessage("§6[" + getDescription().getName() + "] §fIniciado com sucesso");
		consoleMessage.sendMessage("§6[" + getDescription().getName() + "] §fCriado por §6Maria_BR");
		loadingObjects();
		registerFunctions();
	}

	private void registerFunctions() {
		new MaCommand(this);
		new LootingCommand(this);
		new LootingEvents(this);

		if (Hooks.hookAtlasSpawners())
			new AtlasEvent(this);

		else if (Hooks.hookStormSpawners())
			new StormEvent(this);
		
		else if (Hooks.hookYSpawners())
			new ySpawnersEvent(this);
	}

	private void loadingObjects() {
		giveLootingManager = new GiveLootingManager(this);

		lootingAPI = new LootingAPI(this);

		lootingManager = new LootingManager(this);
		lootingSettings = new LootingSettings(this);
		messages = new Messages(this);
		extras = new Extras(this);
	}

	public GiveLootingManager getGiveLootingManager() {
		return giveLootingManager;
	}

	public LootingAPI getLootingAPI() {
		return lootingAPI;
	}

	public LootingManager getLootingManager() {
		return lootingManager;
	}

	public LootingSettings getLootingSettings() {
		return lootingSettings;
	}

	public Messages getMessages() {
		return messages;
	}

	public Extras getExtras() {
		return extras;
	}

}
