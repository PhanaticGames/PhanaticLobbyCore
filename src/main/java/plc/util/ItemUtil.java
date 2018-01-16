package plc.util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import plc.api.IGUIItem;

public class ItemUtil {

	private static ArrayList<IGUIItem> stack = new ArrayList<>();
	public static ArrayList<ItemStack> guiItems = new ArrayList<>();

	public static ItemStack genItemStack(String id) {
		IGUIItem item = null;

		for (int i = 0; i < stack.size(); i++) {
			if (stack.get(i).getID().equals(id)) {
				item = stack.get(i);
			}
		}

		ItemStack itemStack = null;

		if (item != null) {
			Material mat = item.getMaterial();
			itemStack = new ItemStack(mat, 1, (short) item.getData());
			ItemMeta isMeta = itemStack.getItemMeta();
			isMeta.setDisplayName(StringUtil.colorize(item.getName()));
			isMeta.setLore(StringUtil.colorizeList(item.getLore()));
			itemStack.setItemMeta(isMeta);
		} else {
			itemStack = new ItemStack(Material.AIR);
		}

		if (item.getType() == 1) {
			guiItems.add(itemStack);
		}
		return itemStack;
	}

	public static void scanForItems(FileConfiguration yml) {
		ConfigurationSection items = yml.getConfigurationSection("items");
		for (String key : items.getKeys(false)) {

			addGUIItem(key, yml.getString("items." + key + ".name"),
					Material.getMaterial(yml.getString("items." + key + ".material")),
					yml.getInt("items." + key + ".type"), yml.getStringList("items." + key + ".lore"),
					yml.getInt("items." + key + ".data"));
		}
	}

	public static void addGUIItem(String id, String name, Material mat, int type, List<String> lore, int data) {
		stack.add(new IGUIItem() {

			@Override
			public int getType() {
				return type;
			}

			@Override
			public String getName() {
				return name;
			}

			@Override
			public Material getMaterial() {
				return mat;
			}

			@Override
			public String getID() {
				return id;
			}

			@Override
			public List<String> getLore() {
				return lore;
			}

			@Override
			public int getData() {
				return data;
			}
		});
	}
}
