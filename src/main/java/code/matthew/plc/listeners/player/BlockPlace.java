package code.matthew.plc.listeners.player;

import code.matthew.plc.PLC;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlace implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        if(!PLC.staffMode.contains(e.getPlayer())) {
            e.setCancelled(true);
        }
    }
}
