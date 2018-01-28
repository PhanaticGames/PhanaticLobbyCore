package code.matthew.plc.listeners.env;

import code.matthew.plc.entity.ServerVillager;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkUnloadEvent;

public class ChunkUnload implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onEntityDespawn(ChunkUnloadEvent e) {
        for (Entity entity : e.getChunk().getEntities()) {
            if (!(entity instanceof ServerVillager)) continue;
            e.setCancelled(true);
            break;
        }
    }
}
