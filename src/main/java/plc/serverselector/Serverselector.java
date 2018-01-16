package plc.serverselector;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import plc.listeners.player.InvClick;
import plc.util.ItemUtil;
import plc.util.StringUtil;
import net.md_5.bungee.api.ChatColor;

public class Serverselector {

	public static Inventory inv;
	private static int size;
	private static String title;

	public static void openServerSelector(Player p) {
		p.closeInventory();
		p.openInventory(inv);
	}

	public static void setup(FileConfiguration invcc) {
		size = invcc.getInt("guiSize");
		title = StringUtil.colorize(invcc.getString("title"));
		inv = Bukkit.createInventory(null, size, title);

		ConfigurationSection configSection = invcc.getConfigurationSection("slots");
		for (String key : configSection.getKeys(false)) {
			ItemStack stack = ItemUtil.genItemStack(invcc.getString("slots." + key + ".link"));
			InvClick.addIDToServer(ChatColor.stripColor(stack.getItemMeta().getDisplayName()),
					invcc.getString("slots." + key + ".server"));
			inv.setItem(Integer.parseInt(key) - 1, stack);
		}

	}

}
