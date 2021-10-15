package com.maria.looting.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.maria.looting.Main;
import com.maria.looting.managers.GiveLootingManager;
import com.maria.looting.models.Messages;

public class ConvertLootingCommand implements CommandExecutor {

	protected Main main;

	private GiveLootingManager giveLootingManager;

	private Messages messages;

	public ConvertLootingCommand(Main main) {
		this.main = main;

		main.getCommand("converterlooting").setExecutor(this);

		giveLootingManager = main.getGiveLootingManager();

		messages = main.getMessages();
	}

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lb, String[] a) {
		if (!(s instanceof Player))
			return true;

		Player p = (Player) s;
		if (!p.hasPermission("malooting.converter")) {
			p.sendMessage(messages.noPerm);
			return true;

		}
		giveLootingManager.convertLooting(p);
		return false;
	}

}
