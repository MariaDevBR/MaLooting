package com.maria.looting.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.maria.looting.Main;
import com.maria.looting.managers.GiveLootingManager;
import com.maria.looting.models.Messages;

public class LootingCommand implements CommandExecutor {

	protected Main main;

	private GiveLootingManager giveLootingManager;

	private Messages messages;

	public LootingCommand(Main main) {
		this.main = main;

		main.getCommand("looting").setExecutor(this);

		giveLootingManager = main.getGiveLootingManager();

		messages = main.getMessages();
	}

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lb, String[] a) {
		if (!s.hasPermission("malooting.admin")) {
			s.sendMessage(messages.noPerm);
			return true;

		}
		if (a.length != 3) {
			s.sendMessage(messages.incompleteCommand);
			return true;

		}
		if (a[0].equalsIgnoreCase("give")) {
			Player target = Bukkit.getPlayer(a[1]);
			if (target == null) {
				s.sendMessage(messages.playerOff);
				return true;

			}
			giveLootingManager.giveLootingPlayer(s, target, a);

		}
		return false;
	}

}
