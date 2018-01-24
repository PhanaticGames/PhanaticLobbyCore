package code.matthew.plc.listeners.player;

import code.matthew.plc.PLC;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreak implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        if(!PLC.staffMode.contains(e.getPlayer())) {
            e.setCancelled(true);
        }
    }
}
