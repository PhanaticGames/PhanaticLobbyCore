package code.matthew.plc.serverselector;

import code.matthew.plc.util.ItemUtil;
import code.matthew.psc.utils.strings.ColorUtil;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import code.matthew.plc.listeners.player.InvClick;

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
		title = ColorUtil.colorStr(invcc.getString("title"));
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
