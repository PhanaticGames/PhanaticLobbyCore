package code.matthew.plc.listeners.player;

import code.matthew.plc.PLC;
import code.matthew.plc.sb.ScoreboardManager;
import code.matthew.plc.tab.TabList;
import code.matthew.plc.util.FileUtil;
import code.matthew.psc.utils.strings.ColorUtil;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.Scoreboard;

public class Join implements Listener {

	private Scoreboard pb;

	public Scoreboard getScoreboard() {
		return this.pb;
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		e.setJoinMessage("");
		String msg = ColorUtil.colorStr(FileUtil.getConfig().getString("joinMsg"));
		msg = msg.replaceAll("%PLAYER%", e.getPlayer().getName());
		Bukkit.broadcastMessage(msg);
		e.getPlayer().getInventory().setItem(FileUtil.getConfig().getInt("hideSlot"), PLC.hidePlayersItem);
		e.getPlayer().getInventory().setItem(FileUtil.getConfig().getInt("serverSelecterSlot"), PLC.serverSelectorItem);
		ScoreboardManager.setScoreBoard(e.getPlayer());
		if (!e.getPlayer().isOp()) {
			e.getPlayer().setGameMode(GameMode.ADVENTURE);
		}

		Location loc = new Location(Bukkit.getWorld("world"),
				Double.parseDouble(FileUtil.getSpawnData().getString("spawnX")),
				Double.parseDouble(FileUtil.getSpawnData().getString("spawnY")),
				Double.parseDouble(FileUtil.getSpawnData().getString("spawnZ")),
				Float.valueOf(FileUtil.getSpawnData().getString("spawnYaw")),
				Float.valueOf(FileUtil.getSpawnData().getString("spawnPitch")));

		e.getPlayer().teleport(loc);
		TabList.setTabList(e.getPlayer());
	}

}
