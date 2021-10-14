package com.maria.looting.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

@SuppressWarnings("all")
public class ItemBuilder {

	private ItemStack item;

	public ItemBuilder(Material m) {
		this(m, 1);
	}

	public ItemBuilder(ItemStack is) {
		this.item = is;
	}

	public ItemBuilder(Material m, int quantia) {
		this.item = new ItemStack(m, quantia);

	}

	public ItemBuilder(Material m, int quantia, byte durabilidade) {
		this.item = new ItemStack(m, quantia, durabilidade);
	}

	public ItemBuilder(Material m, int quantia, int durabilidade) {
		this.item = new ItemStack(m, quantia, (short) durabilidade);
	}

	public ItemBuilder clone() {
		return new ItemBuilder(this.item);
	}

	public ItemBuilder setDurability(short durabilidade) {
		this.item.setDurability(durabilidade);

		return this;
	}

	public ItemBuilder setAmount(int amount) {
		this.item.setAmount(amount);
		ItemMeta im = this.item.getItemMeta();
		im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_POTION_EFFECTS });
		this.item.setItemMeta(im);

		return this;
	}

	public ItemBuilder setDurability(String durabilidade) {
		this.item.setDurability(Short.valueOf(durabilidade).shortValue());

		return this;
	}

	public ItemBuilder setName(String name) {
		ItemMeta im = this.item.getItemMeta();
		im.setDisplayName(name);
		this.item.setItemMeta(im);

		return this;
	}

	public ItemBuilder addUnsafeEnchantment(Enchantment ench, int level) {
		this.item.addUnsafeEnchantment(ench, level);

		return this;
	}

	public ItemBuilder removeEnchantment(Enchantment ench) {
		this.item.removeEnchantment(ench);

		return this;
	}

	public ItemBuilder setSkullOwner(String dono) {
		try {
			SkullMeta im = (SkullMeta) this.item.getItemMeta();
			im.setOwner(dono);
			this.item.setItemMeta((ItemMeta) im);

		} catch (ClassCastException classCastException) {

		}
		return this;
	}

	public ItemBuilder addEnchant(Enchantment ench, int level) {
		ItemMeta im = this.item.getItemMeta();
		im.addEnchant(ench, level, true);
		this.item.setItemMeta(im);

		return this;
	}

	public ItemBuilder addEnchantments(Map<Enchantment, Integer> enchantments) {
		this.item.addEnchantments(enchantments);

		return this;
	}

	public ItemBuilder setInfinityDurability() {
		this.item.setDurability((short) 63);

		return this;
	}

	public ItemBuilder addItemFlag(ItemFlag flag) {
		ItemMeta im = this.item.getItemMeta();
		im.addItemFlags(new ItemFlag[] { flag });
		this.item.setItemMeta(im);

		return this;
	}

	public ItemBuilder setLore(String... lore) {
		ItemMeta im = this.item.getItemMeta();
		im.setLore(Arrays.asList(lore));
		this.item.setItemMeta(im);

		return this;
	}

	public ItemBuilder setLore(List<String> lore) {
		ItemMeta im = this.item.getItemMeta();
		im.setLore(lore);
		this.item.setItemMeta(im);

		return this;
	}

	public ItemBuilder removeLoreLine(String linha) {
		ItemMeta im = this.item.getItemMeta();
		List<String> lore = new ArrayList<>(im.getLore());
		if (!lore.contains(linha))
			return this;

		lore.remove(linha);
		im.setLore(lore);
		this.item.setItemMeta(im);

		return this;
	}

	public ItemBuilder removeLoreLine(int index) {
		ItemMeta im = this.item.getItemMeta();
		List<String> lore = new ArrayList<>(im.getLore());
		if (index < 0 || index > lore.size())
			return this;

		lore.remove(index);
		im.setLore(lore);
		this.item.setItemMeta(im);

		return this;
	}

	public ItemBuilder addLoreLine(String linha) {
		ItemMeta im = this.item.getItemMeta();
		List<String> lore = new ArrayList<>();
		if (im.hasLore())
			lore = new ArrayList<>(im.getLore());
		lore.add(linha);
		im.setLore(lore);
		this.item.setItemMeta(im);

		return this;
	}

	public ItemBuilder addLores(List<String> linha) {
		ItemMeta im = this.item.getItemMeta();
		List<String> lore = new ArrayList<>();
		if (im.hasLore())
			lore = new ArrayList<>(im.getLore());
		for (String s : linha)
			lore.add(s);
		im.setLore(lore);
		this.item.setItemMeta(im);

		return this;
	}

	public ItemBuilder addLoreLine(String linha, int pos) {
		ItemMeta im = this.item.getItemMeta();
		List<String> lore = new ArrayList<>(im.getLore());
		lore.set(pos, linha);
		im.setLore(lore);
		this.item.setItemMeta(im);

		return this;
	}

	public ItemBuilder owner(String owner) {
		try {
			SkullMeta im = (SkullMeta) this.item.getItemMeta();
			im.setOwner(owner);
			this.item.setItemMeta((ItemMeta) im);

		} catch (ClassCastException classCastException) {

		}
		return this;
	}

	public ItemBuilder lore(String... lore) {
		ItemMeta im = this.item.getItemMeta();
		List<String> out = (im.getLore() == null) ? new ArrayList<>() : im.getLore();
		byte b;
		int i;
		String[] arrayOfString;
		for (i = (arrayOfString = lore).length, b = 0; b < i;) {
			String string = arrayOfString[b];
			out.add(ChatColor.translateAlternateColorCodes('&', string));
			b = (byte) (b + 1);
		}
		im.setLore(out);
		this.item.setItemMeta(im);

		return this;
	}

	public ItemBuilder setLore2(String... lore) {
		ItemMeta im = this.item.getItemMeta();
		List<String> out = (im.getLore() == null) ? new ArrayList<>() : im.getLore();
		byte b;
		int i;
		String[] arrayOfString;
		for (i = (arrayOfString = lore).length, b = 0; b < i;) {
			String string = arrayOfString[b];
			out.add(ChatColor.translateAlternateColorCodes('&', string));
			b = (byte) (b + 1);

		}
		im.setLore(out);
		this.item.setItemMeta(im);

		return this;
	}

	public ItemBuilder removeAttributes() {
		ItemMeta meta = this.item.getItemMeta();
		meta.addItemFlags(ItemFlag.values());
		this.item.setItemMeta(meta);

		return this;
	}

	public ItemBuilder color(Color color) {
		if (!this.item.getType().name().contains("LEATHER_"))
			return this;
		LeatherArmorMeta meta = (LeatherArmorMeta) this.item.getItemMeta();
		meta.setColor(color);
		this.item.setItemMeta((ItemMeta) meta);

		return this;
	}

	public ItemBuilder setDyeColor(DyeColor cor) {
		this.item.setDurability(cor.getData());

		return this;
	}

	@Deprecated
	public ItemBuilder setWoolColor(DyeColor cor) {
		if (!this.item.getType().equals(Material.WOOL))
			return this;
		this.item.setDurability(cor.getData());

		return this;
	}

	public ItemBuilder setLeatherArmorColor(Color cor) {
		try {
			LeatherArmorMeta im = (LeatherArmorMeta) this.item.getItemMeta();
			im.setColor(cor);
			this.item.setItemMeta((ItemMeta) im);

		} catch (ClassCastException classCastException) {

		}
		return this;
	}

	public ItemBuilder builder(Consumer<ItemStack> consumer) {
		consumer.accept(item);

		return this;

	}

	public ItemBuilder builderMeta(Consumer<ItemMeta> consumer) {
		ItemMeta meta = item.getItemMeta();
		consumer.accept(meta);
		item.setItemMeta(meta);

		return this;

	}

	public ItemBuilder addGlow(boolean glow) {
		if (!glow)
			return this;

		builder(it -> it.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 1));
		builderMeta(meta -> meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS }));
		return this;

	}

	public ItemBuilder setNBTTag(String key, String value) {
		try {
			Object nmsCopy = NMSReflect.getCraftBukkitClass("inventory", "CraftItemStack")
					.getMethod("asNMSCopy", new Class[] { ItemStack.class }).invoke(null, new Object[] { item });
			Object nbtTagCompound = NMSReflect.getNMSClass("NBTTagCompound").getConstructor(new Class[0])
					.newInstance(new Object[0]);

			boolean b = (nmsCopy.getClass().getMethod("getTag", new Class[0]).invoke(nmsCopy, new Object[0]) != null);
			Object nbtTag = b ? nmsCopy.getClass().getMethod("getTag", new Class[0]).invoke(nmsCopy, new Object[0])
					: nbtTagCompound;

			Constructor nbsString = NMSReflect.getNMSClass("NBTTagString").getConstructor(new Class[] { String.class });
			nbtTag.getClass().getMethod("set", new Class[] { String.class, NMSReflect.getNMSClass("NBTBase") })
					.invoke(nbtTag, new Object[] { key, nbsString.newInstance(new Object[] { value }) });

			nmsCopy.getClass().getMethod("setTag", new Class[] { NMSReflect.getNMSClass("NBTTagCompound") })
					.invoke(nmsCopy, new Object[] { nbtTag });

			item = (ItemStack) NMSReflect.getCraftBukkitClass("inventory", "CraftItemStack")
					.getMethod("asBukkitCopy", new Class[] { NMSReflect.getNMSClass("ItemStack") })
					.invoke(null, new Object[] { nmsCopy });

		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException
				| InstantiationException e) {
			e.printStackTrace();

		}
		return this;
	}

	public static String getNBTTag(ItemStack item, String key) {
		try {
			Object nmsCopy = NMSReflect.getCraftBukkitClass("inventory", "CraftItemStack")
					.getMethod("asNMSCopy", new Class[] { ItemStack.class }).invoke(null, new Object[] { item });

			if (nmsCopy.getClass().getMethod("getTag", new Class[0]).invoke(nmsCopy, new Object[0]) == null)
				return null;

			Object tagCompound = nmsCopy.getClass().getMethod("getTag", new Class[0]).invoke(nmsCopy, new Object[0]);
			return (String) tagCompound.getClass().getMethod("getString", new Class[] { String.class })
					.invoke(tagCompound, new Object[] { key });

		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
			return null;
		}

	}

	public ItemStack toItemStack() {
		return this.item;
	}

	public ItemStack build() {
		return this.item;

	}

}
