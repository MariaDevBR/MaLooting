package com.maria.looting.hook;

import org.bukkit.Bukkit;

public class Hooks {

	public static boolean hookAtlasSpawners() {
		if (Bukkit.getPluginManager().getPlugin("AtlasSpawnersV2") != null) {
			Bukkit.getConsoleSender().sendMessage("§6[MaLooting] §aAtlasSpawnersV2 §fHookado com sucesso");
			return true;

		}
		return false;
	}

	public static boolean hookStormSpawners() {
		if (Bukkit.getPluginManager().getPlugin("StormSpawners") != null) {
			Bukkit.getConsoleSender().sendMessage("§6[MaLooting] §aStormSpawners §fHookado com sucesso");
			return true;

		}
		return false;
	}

	public static boolean hookYSpawners() {
		if (Bukkit.getPluginManager().getPlugin("ySpawners") != null) {
			Bukkit.getConsoleSender().sendMessage("§6[MaLooting] §aySpawners §fHookado com sucesso");
			return true;

		}
		return false;
	}

	public static boolean hookYSpawnersV2() {
		if (Bukkit.getPluginManager().getPlugin("ySpawnersV2") != null) {
			Bukkit.getConsoleSender().sendMessage("§6[MaLooting] §aySpawnersV2 §fHookado com sucesso");
			return true;

		}
		return false;
	}

}
