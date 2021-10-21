package com.maria.looting.utils.checkers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class UpdateCheck {

	public enum UpdateCheckerResult {
		NONE_RESULT, OUT_DATED, UP_TO_DATE, UANRELEASED;
	}

	private final JavaPlugin javaPlugin;

	private int resourceID;
	private URL resourceURL;
	private String currentVersion;
	private String lastVersion;
	private UpdateCheckerResult updateCheckerResult;

	public UpdateCheck(JavaPlugin javaPlugin, int resourceID) {
		this.javaPlugin = javaPlugin;

		try {
			this.resourceID = resourceID;
			resourceURL = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + resourceID);

		} catch (Exception e) {
			return;
		}

		currentVersion = javaPlugin.getDescription().getVersion();
		lastVersion = getLastedVersion();

		if (lastVersion == null) {
			updateCheckerResult = UpdateCheckerResult.NONE_RESULT;
			return;

		}

		int currentVersion = Integer.parseInt(this.currentVersion.replace("v", "").replace(".", ""));
		int lastVersion = Integer.parseInt(this.lastVersion.replace("v", "").replace(".", ""));

		if (currentVersion < lastVersion)
			updateCheckerResult = UpdateCheckerResult.OUT_DATED;

		else if (currentVersion == lastVersion)
			updateCheckerResult = UpdateCheckerResult.UP_TO_DATE;

		else
			updateCheckerResult = UpdateCheckerResult.UANRELEASED;

	}

	public String getLastedVersion() {
		try {
			URLConnection urlConnection = resourceURL.openConnection();
			return new BufferedReader(new InputStreamReader(urlConnection.getInputStream())).readLine();

		} catch (Exception e) {
			return null;
		}

	}

	public int getResourceID() {
		return resourceID;
	}

	public String getResourceURL() {
		return "https://www.spigotmc.org/resources/" + resourceID;
	}

	public String getCurrentVersion() {
		return currentVersion;
	}

	public String getLastVersion() {
		return lastVersion;
	}

	public UpdateCheckerResult getUpdateCheckerResult() {
		return updateCheckerResult;
	}

	public void messageOutOfDated(CommandSender sender) {
		String pluginName = javaPlugin.getDescription().getName();
		String version = javaPlugin.getDescription().getVersion();

		List<String> message = new ArrayList<>();
		message.add("");
		message.add("§6[" + pluginName + "] §fHá uma nova atualização");
		message.add("§6[" + pluginName + "] §fdisponível para Download.");
		message.add("");
		message.add("§6[" + pluginName + "] §fSua Versão: §6" + version);
		message.add("§6[" + pluginName + "] §fNova Versão: §6" + lastVersion);
		message.add("");
		message.add("§6[" + pluginName + "] §fDownload:");
		message.add("§e" + getResourceURL());
		message.add("");
		for (String line : message)
			sender.sendMessage(line);
	}

}
