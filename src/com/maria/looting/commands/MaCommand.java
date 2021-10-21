package com.maria.looting.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.HandlerList;

import com.maria.looting.Main;

public class MaCommand implements CommandExecutor {

	protected Main main;

	public MaCommand(Main main) {
		this.main = main;

		main.getCommand("ma").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lb, String[] a) {
		if (!s.hasPermission("malooting.admin")) {
			s.sendMessage("§cSem permissão.");
			return true;

		}
		if (a.length != 1) {
			s.sendMessage("§6§lLOOTING §8» §cUtilize: /ma reload");
			return true;

		}
		if (a[0].equalsIgnoreCase("reload") || a[0].equalsIgnoreCase("rl")) {
			Bukkit.getPluginManager().disablePlugin(main);
			Bukkit.getScheduler().cancelTasks(main);
			Bukkit.getServicesManager().unregisterAll(main);
			HandlerList.unregisterAll(main);

			Bukkit.getPluginManager().enablePlugin(main);

			s.sendMessage("§6§lLOOTING §8» §aPlugin recarregado com sucesso.");

		}
		return false;
	}

}
