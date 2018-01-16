package plc.listeners.player;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import plc.PLC;
import plc.serverselector.Serverselector;
import plc.util.ItemUtil;

public class InvClick implements Listener {

	public static HashMap<String, String> idToServer = new HashMap<>();

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		ItemStack clicked = event.getCurrentItem();
		Inventory inventory = event.getInventory();

		if (!inventory.getName().equals(Serverselector.inv.getName())) {
			return;
		}

		event.setCancelled(true);

		if (clicked != null) {
			for (int i = 0; i < ItemUtil.guiItems.size(); i++) {
				if (ItemUtil.guiItems.contains(clicked)) {
					String server = null;
					for (int j = 0; j < idToServer.size(); j++) {
						server = idToServer.get(ChatColor.stripColor(clicked.getItemMeta().getDisplayName()));
					}

					if (server != null) {
						ByteArrayDataOutput out = ByteStreams.newDataOutput();
						out.writeUTF("Connect");
						out.writeUTF(server);
						player.sendPluginMessage(PLC.getInstance(), "BungeeCord", out.toByteArray());
					}
				}
			}
		}
	}

	public static void addIDToServer(String id, String server) {
		idToServer.put(id, server);
	}
}
