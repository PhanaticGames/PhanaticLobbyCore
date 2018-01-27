package code.matthew.plc.listeners.env;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class MobSpawn implements Listener {

	@EventHandler
	public void onSpawn(CreatureSpawnEvent e) {

	    boolean cancel;

	    String entity = e.getEntityType().name();
	    if(entity.equalsIgnoreCase("VILLAGER")) {
	        cancel = false;
        } else {
	        cancel = true;
        }

        e.setCancelled(cancel);
	}
}
