package code.matthew.plc.listeners.player;

import code.matthew.plc.PLC;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class CropTrample implements Listener {

    @EventHandler
    public void onTrample(PlayerInteractEvent e) {
        if(e.getAction() == Action.PHYSICAL) {
            Block b = e.getClickedBlock();
            if(b.getType() == Material.WHEAT) {
                if(!PLC.staffMode.contains(e.getPlayer())) {
                    e.setCancelled(true);
                }
            }
        }
    }
}
