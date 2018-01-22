package code.matthew.plc.listeners.env;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class MobSpawn implements Listener {

	@EventHandler
	public void onSpawn(CreatureSpawnEvent e) {
		if(e.getEntityType() != EntityType.PLAYER) {
			e.setCancelled(true);
		}
	}
}
