package code.matthew.plc.cmd;

import code.matthew.plc.entity.ServerVillager;
import code.matthew.psc.api.command.ICommand;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ServerEntity extends ICommand {

    public ServerEntity() {
        super("serverentity", "psc.serverentity", "Spawn a server selector entity", true);
    }

    @Override
    public boolean finalExe(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        World w = p.getWorld();
        ServerVillager.spawn(p.getLocation());
        return true;

    }
}
