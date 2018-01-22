package code.matthew.plc.listeners.player;

import code.matthew.plc.util.FileUtil;
import code.matthew.psc.utils.strings.ColorUtil;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class Leave implements Listener {
	
	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		e.setQuitMessage("");
		String msg = ColorUtil.colorStr(FileUtil.getConfig().getString("quitMsg"));
		msg = msg.replaceAll("%PLAYER%", e.getPlayer().getName());
		Bukkit.broadcastMessage(msg);
	}

}
