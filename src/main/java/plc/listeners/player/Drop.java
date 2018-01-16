package plc.listeners.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class Drop implements Listener {

	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		if(!e.getPlayer().isOp()) {
			e.setCancelled(true);
		}
	}
}
