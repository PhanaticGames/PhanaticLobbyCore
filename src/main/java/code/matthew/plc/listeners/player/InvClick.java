package code.matthew.plc.listeners.player;

import code.matthew.plc.PLC;
import code.matthew.plc.api.IWait;
import code.matthew.plc.serverselector.Serverselector;
import code.matthew.plc.util.ItemUtil;
import code.matthew.psc.utils.strings.ColorUtil;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

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
        }
        if (ChatColor.stripColor(inventory.getName()).equals("Edit entity")) {
            event.setCancelled(true);

            // Because the name of this inv is what we need, it is safe to assume item functions
            ItemStack stack = event.getCurrentItem();

            if (stack != null && PLC.playerAndEntity.containsKey(player)) {

                if (stack.getType() == Material.BARRIER) {
                    player.closeInventory();
                    if (PLC.playerAndEntity.containsKey(player)) {
                        PLC.playerAndEntity.remove(player);
                    }
                } else if (stack.getType() == Material.BOOK_AND_QUILL) {
                    Entity entity = PLC.playerAndEntity.get(player);
                    if (entity != null) {
                        player.sendMessage(ChatColor.GREEN + "Please, enter the name of the villager in your chat. Color codes are allowed.");
                        PLC.waitingFor.put(player, new IWait() {
                            @Override
                            public Player player() {
                                return player;
                            }

                            @Override
                            public Entity entity() {
                                return entity;
                            }

                            @Override
                            public void run(String res) {
                                entity().setCustomName(ColorUtil.colorStr(res));
                            }
                        });
                    }
                    if (PLC.playerAndEntity.containsKey(player)) {
                        PLC.playerAndEntity.remove(player);
                    }
                    player.closeInventory();
                } else if (stack.getType() == Material.BLAZE_POWDER) {
                    Entity entity = PLC.playerAndEntity.get(player);
                    if(entity != null) {
                        Vector dirBetweenLocations = player.getLocation().toVector().subtract(entity.getLocation().toVector());
                        Location loc = entity.getLocation();
                        dirBetweenLocations = dirBetweenLocations.multiply(-1);
                        loc.setDirection(dirBetweenLocations);
                        entity.teleport(loc);
                    }
                    if (PLC.playerAndEntity.containsKey(player)) {
                        PLC.playerAndEntity.remove(player);
                    }
                    player.closeInventory();
                } else if (stack.getType() == Material.BEDROCK) {
                    // MOVE
                } else if (stack.getType() == Material.DIAMOND_SWORD) {
                    Entity entity = PLC.playerAndEntity.get(player);
                    if (entity != null) {
                        entity.remove();
                    }
                    if (PLC.playerAndEntity.containsKey(player)) {
                        PLC.playerAndEntity.remove(player);
                    }
                    player.closeInventory();
                }
            }
        }
    }

    public static void addIDToServer(String id, String server) {
        idToServer.put(id, server);
    }
}
