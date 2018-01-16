package plc.listeners.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import plc.PLC;
import plc.serverselector.Serverselector;
import plc.util.FileUtil;
import plc.util.StringUtil;

public class Interact implements Listener {

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_AIR
				|| e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_BLOCK) {
			
			if (e.getPlayer().getItemInHand().getType() == PLC.hidePlayersItem.getType()) {

				if (!e.getPlayer().hasPermission("lc.hideplayers")) {
					return;
				}

				if (!PLC.hidden.contains(e.getPlayer())) {

					for (Player p : Bukkit.getOnlinePlayers()) {
						e.getPlayer().hidePlayer(p);
					}

					PLC.hidden.add(e.getPlayer());

					e.getPlayer().sendMessage(StringUtil.colorize(FileUtil.getConfig().getString("playersHidden")));

				} else {
					for (Player p : Bukkit.getOnlinePlayers()) {
						e.getPlayer().showPlayer(p);
					}

					PLC.hidden.remove(e.getPlayer());

					e.getPlayer().sendMessage(StringUtil.colorize(FileUtil.getConfig().getString("playersRevaled")));
				}
			} else if(e.getPlayer().getItemInHand().getType() == PLC.serverSelectorItem.getType()) {
				e.getPlayer().closeInventory();
				Serverselector.openServerSelector(e.getPlayer());
			} else {
				return;
			}
		}
	}
}
