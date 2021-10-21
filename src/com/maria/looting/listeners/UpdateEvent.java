package com.maria.looting.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.maria.looting.Main;
import com.maria.looting.utils.checkers.UpdateCheck;

public class UpdateEvent implements Listener {

	protected Main main;

	public UpdateEvent(Main main) {
		this.main = main;

		Bukkit.getPluginManager().registerEvents(this, main);
	}

	@EventHandler
	void staffJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		UpdateCheck updateCheck = main.getUpdateCheck();

		String pluginName = main.getDescription().getName();
		String version = main.getDescription().getVersion();

		if (p.hasPermission("malooting.admin")) {
			p.sendMessage("");
			p.sendMessage("§6[" + pluginName + "] §fHá uma nova atualização");
			p.sendMessage("§6[" + pluginName + "] §fdisponível para Download.");
			p.sendMessage("");
			p.sendMessage("§6[" + pluginName + "] §fSua Versão: §6" + version);
			p.sendMessage("§6[" + pluginName + "] §fNova Versão: §6" + updateCheck.getLastVersion());
			p.sendMessage("");
			p.sendMessage("§6[" + pluginName + "] §fDownload:");
			p.sendMessage("§e" + updateCheck.getResourceURL());
			p.sendMessage("");
		}

	}

}
