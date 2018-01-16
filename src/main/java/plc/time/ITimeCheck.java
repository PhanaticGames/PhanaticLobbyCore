package plc.time;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class ITimeCheck extends BukkitRunnable {

	@Override
	public void run() {
		if (Bukkit.getWorld("world").getTime() != 0) {
			Bukkit.getWorld("world").setTime(0);
		}
	}

}
