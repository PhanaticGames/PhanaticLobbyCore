package code.matthew.plc.listeners.player;

import code.matthew.plc.util.FileUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class Move implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if(e.getPlayer().getLocation().getY() < 20) {
            Location loc = new Location(Bukkit.getWorld("world"),
                    Double.parseDouble(FileUtil.getSpawnData().getString("spawnX")),
                    Double.parseDouble(FileUtil.getSpawnData().getString("spawnY")),
                    Double.parseDouble(FileUtil.getSpawnData().getString("spawnZ")),
                    Float.valueOf(FileUtil.getSpawnData().getString("spawnYaw")),
                    Float.valueOf(FileUtil.getSpawnData().getString("spawnPitch")));

            e.getPlayer().teleport(loc);
        }
    }
}
