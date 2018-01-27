package code.matthew.plc.listeners.player;

import code.matthew.plc.PLC;
import code.matthew.plc.serverselector.Serverselector;
import code.matthew.plc.util.ItemUtil;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class InvClick implements Listener {

    public static HashMap<String, String> idToServer = new HashMap<>();

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack clicked = event.getCurrentItem();
        Inventory inventory = event.getInventory();

        if (!PLC.staffMode.contains(player)) {
            event.setCancelled(true);
        }

        if (inventory.getName().equals(Serverselector.inv.getName())) {
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
        } if(ChatColor.stripColor(inventory.getName()).equals("Edit entity")) {
            event.setCancelled(true);

        }
    }

    public static void addIDToServer(String id, String server) {
        idToServer.put(id, server);
    }
}
