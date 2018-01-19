package plc.cmd;

import code.matthew.psc.api.command.ICommand;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import plc.util.FileUtil;

public class SetSpawn extends ICommand {

    public SetSpawn() {
        super("setspawn", "psc.setspawn", "Set the spawn", true);
    }

    @Override
    public boolean finalExe(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        FileUtil.getSpawnData().set("spawnX", p.getLocation().getX());
        FileUtil.getSpawnData().set("spawnY", p.getLocation().getY());
        FileUtil.getSpawnData().set("spawnZ", p.getLocation().getZ());
        FileUtil.getSpawnData().set("spawnPitch", p.getEyeLocation().getPitch());
        FileUtil.getSpawnData().set("spawnYaw", p.getEyeLocation().getYaw());
        FileUtil.saveSpawnData();
        p.sendMessage(ChatColor.BLUE + "Spawn set");
        return true;
    }
}
