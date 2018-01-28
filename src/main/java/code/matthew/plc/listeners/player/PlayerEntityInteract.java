package code.matthew.plc.listeners.player;

import code.matthew.plc.PLC;
import code.matthew.plc.util.GUIUtil;
import code.matthew.psc.utils.strings.ColorUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;


public class PlayerEntityInteract implements Listener {

    @EventHandler
    public void interact(PlayerInteractEntityEvent e) {
        Entity npc = e.getRightClicked();
        Player player = e.getPlayer();
        if (npc.getType() == EntityType.VILLAGER) {
            e.setCancelled(true);

            if(player.getItemInHand().getType() == Material.STICK) {
                if (player.hasPermission("plc.entityedit")) {
                    if(player.getItemInHand().getItemMeta().getDisplayName().equals(ColorUtil.colorStr("&2Entity Editor"))) {
                        Inventory inv = GUIUtil.getEntityGUI(npc);
                        if(inv != null) {
                            if(PLC.playerAndEntity.containsKey(player)) {
                                PLC.playerAndEntity.remove(player);
                            }
                            PLC.playerAndEntity.put(player, npc);
                            player.openInventory(GUIUtil.getEntityGUI(npc));
                        } else {
                            player.sendMessage(ChatColor.RED + "There was an error fetching the GUI for this entity!");
                        }
                    }
                } else {
                    // You have a stick but no perms???!?!??!?!
                    player.getInventory().remove(Material.STICK);
                }
            }
        }
    }
}
