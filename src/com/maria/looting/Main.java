package com.maria.looting;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import com.maria.looting.api.LootingAPI;
import com.maria.looting.commands.ConvertLootingCommand;
import com.maria.looting.commands.LootingCommand;
import com.maria.looting.commands.MaCommand;
import com.maria.looting.hook.Hooks;
import com.maria.looting.listeners.AtlasMobEvent;
import com.maria.looting.listeners.DefaultMobEvent;
import com.maria.looting.listeners.LootingEvents;
import com.maria.looting.listeners.StackLootingEvent;
import com.maria.looting.listeners.StormMobEvent;
import com.maria.looting.listeners.UpdateEvent;
import com.maria.looting.managers.GiveLootingManager;
import com.maria.looting.managers.LootingManager;
import com.maria.looting.managers.StackLootingManager;
import com.maria.looting.models.Extras;
import com.maria.looting.models.LootingSettings;
import com.maria.looting.models.Messages;
import com.maria.looting.utils.checkers.UpdateCheck;

public class Main extends JavaPlugin {

	private UpdateCheck updateCheck;

	private GiveLootingManager giveLootingManager;
	private StackLootingManager stackLootingManager;

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
		checkUpdate();
	}

	private void registerFunctions() {
		new MaCommand(this);
		new LootingCommand(this);
		new ConvertLootingCommand(this);
		new LootingEvents(this);
		new StackLootingEvent(this);

		if (Hooks.hookAtlasSpawners())
			new AtlasMobEvent(this);

		else if (Hooks.hookStormSpawners())
			new StormMobEvent(this);

		else if (Hooks.hookYSpawners()) {

		} else if (Hooks.hookYSpawnersV2()) {

		} else if (Hooks.noHooks())
			new DefaultMobEvent(this);
	}

	private void loadingObjects() {
		giveLootingManager = new GiveLootingManager(this);

		lootingAPI = new LootingAPI(this);

		lootingSettings = new LootingSettings(this);
		messages = new Messages(this);
		extras = new Extras(this);

		lootingManager = new LootingManager(this);
		stackLootingManager = new StackLootingManager(this);
	}

	@SuppressWarnings("deprecation")
	private void checkUpdate() {
		updateCheck = new UpdateCheck(this, 94064);
		if (updateCheck.getUpdateCheckerResult().equals(UpdateCheck.UpdateCheckerResult.OUT_DATED)) {
			updateCheck.messageOutOfDated(Bukkit.getConsoleSender());
			new UpdateEvent(this);

		} else
			Bukkit.getConsoleSender()
					.sendMessage("§6[" + getDescription().getName() + "] §fNão há nenhuma atualização no momento.");

		Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, () -> {
			if (updateCheck.getUpdateCheckerResult().equals(UpdateCheck.UpdateCheckerResult.OUT_DATED)) {
				updateCheck.messageOutOfDated(Bukkit.getConsoleSender());

			}
		}, 288000, 288000);

	}

	public UpdateCheck getUpdateCheck() {
		return updateCheck;
	}

	public GiveLootingManager getGiveLootingManager() {
		return giveLootingManager;
	}

	public StackLootingManager getStackLootingManager() {
		return stackLootingManager;
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
