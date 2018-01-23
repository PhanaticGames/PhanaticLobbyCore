package code.matthew.plc.listeners.player;

import code.matthew.plc.PLC;
import code.matthew.plc.serverselector.Serverselector;
import code.matthew.plc.util.FileUtil;
import code.matthew.psc.utils.strings.ColorUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class Interact implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_AIR
                || e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_BLOCK) {

            ItemStack itemStack = e.getPlayer().getItemInHand();

            if (itemStack.getType() == PLC.hidePlayersItem.getType()) {

                if (itemStack.getItemMeta().getDisplayName().equalsIgnoreCase(PLC.hidePlayersItem.getItemMeta().getDisplayName())) {
                    if (!e.getPlayer().hasPermission("plc.hideplayers")) {
                        return;
                    }

                    if (!PLC.hidden.contains(e.getPlayer())) {

                        for (Player p : Bukkit.getOnlinePlayers()) {
                            e.getPlayer().hidePlayer(p);
                        }

                        PLC.hidden.add(e.getPlayer());

                        e.getPlayer().sendMessage(ColorUtil.colorStr(FileUtil.getConfig().getString("playersHidden")));

                    } else {
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            e.getPlayer().showPlayer(p);
                        }

                        PLC.hidden.remove(e.getPlayer());

                        e.getPlayer().sendMessage(ColorUtil.colorStr(FileUtil.getConfig().getString("playersRevaled")));
                    }
                }
            } else if (itemStack.getType() == PLC.serverSelectorItem.getType()) {
                if (itemStack.getItemMeta().getDisplayName().equalsIgnoreCase(PLC.serverSelectorItem.getItemMeta().getDisplayName())) {
                    e.getPlayer().closeInventory();
                    Serverselector.openServerSelector(e.getPlayer());
                }
            }
        }
    }
}
