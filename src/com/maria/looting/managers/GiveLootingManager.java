package com.maria.looting.managers;

import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.maria.looting.Main;
import com.maria.looting.models.LootingSettings;
import com.maria.looting.models.Messages;
import com.maria.looting.utils.Format;
import com.maria.looting.utils.ItemBuilder;

import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagDouble;

public class GiveLootingManager {

	protected Main main;

	private LootingSettings lootingSettings;

	private Messages messages;

	private ItemStack lootingItem;

	public GiveLootingManager(Main main) {
		this.main = main;
	}

	public void giveLootingPlayer(CommandSender s, Player target, String[] a) {
		lootingSettings = main.getLootingSettings();
		messages = main.getMessages();

		try {
			double amount = Double.parseDouble(a[2]);

			if (amount <= 0) {
				s.sendMessage("§6§lLOOTING §8» §cO nível tem que ser maior que §70§c.");
				return;

			}
			List<String> lore = lootingSettings.lore;
			lore = lore.stream().map(l -> l.replace("&", "§").replace("{level}", Format.format(amount)))
					.collect(Collectors.toList());

			ItemStack item = new ItemStack(lootingSettings.material, 1, (short) lootingSettings.data);
			net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);

			NBTTagCompound itemCompound = nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound();
			itemCompound.set("Looting", new NBTTagDouble(amount));

			nmsItem.setTag(itemCompound);

			ItemStack craftItem = CraftItemStack.asBukkitCopy(nmsItem);

			ItemStack itemBuilded = new ItemBuilder(craftItem).setName(lootingSettings.name).setLore(lore)
					.addGlow(lootingSettings.glow).build();

			target.getInventory().addItem(itemBuilded);

			lootingItem = itemBuilded;

			s.sendMessage("§6§lLOOTING §8» §7Você deu §f1 §7livro de Looting com §e" + Format.format(amount)
					+ " §fleveis §7para §e" + target.getName() + "§7.");

		} catch (NumberFormatException e) {
			s.sendMessage(messages.invalidNumber);
		}

	}

	public ItemStack getLootingItem() {
		return lootingItem;
	}

}
