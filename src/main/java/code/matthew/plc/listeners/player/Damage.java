package code.matthew.plc.listeners.player;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class Damage implements Listener {

	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		if(e.getEntityType() == EntityType.PLAYER) {
			Player p = (Player) e.getEntity();
			
			if(!p.isOp()) {
				e.setCancelled(true);
			}
		} else if(e.getEntityType() == EntityType.VILLAGER) {
			e.setCancelled(true);
		}
	}
}
