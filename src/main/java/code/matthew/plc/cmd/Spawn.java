package code.matthew.plc.cmd;

import code.matthew.plc.util.FileUtil;
import code.matthew.psc.api.command.ICommand;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Spawn extends ICommand {

    public Spawn() {
        super("spawn", "", "Go to spawn", true);
    }

    @Override
    public boolean finalExe(CommandSender sender, String[] args) {
        Player p = (Player) sender;

        Location loc = new Location(Bukkit.getWorld("world"),
                Double.parseDouble(FileUtil.getSpawnData().getString("spawnX")),
                Double.parseDouble(FileUtil.getSpawnData().getString("spawnY")),
                Double.parseDouble(FileUtil.getSpawnData().getString("spawnZ")),
                Float.valueOf(FileUtil.getSpawnData().getString("spawnYaw")),
                Float.valueOf(FileUtil.getSpawnData().getString("spawnPitch")));

        p.teleport(loc);
        return true;
    }
}
