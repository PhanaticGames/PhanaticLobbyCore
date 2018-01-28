package code.matthew.plc.listeners.player;

import code.matthew.plc.PLC;
import code.matthew.psc.api.player.PlayerUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryClose implements Listener {

    @EventHandler
    public void onInvlose(InventoryCloseEvent e) {
        if(ChatColor.stripColor(e.getInventory().getName()).equals("Edit entity")) {
            // e.getPlayer returns a human entity, NOT a player, so we have to find the player
            Player p = PlayerUtils.getOnlinePlayer(e.getPlayer().getName());
            if (PLC.playerAndEntity.containsKey(p)) {
                PLC.playerAndEntity.remove(p);
            }
        }
    }
}
