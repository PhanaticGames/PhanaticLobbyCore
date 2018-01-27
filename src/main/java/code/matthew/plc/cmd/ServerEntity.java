package code.matthew.plc.cmd;

import code.matthew.plc.entity.ServerVillager;
import code.matthew.psc.api.command.ICommand;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.WorldServer;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class ServerEntity extends ICommand {

    public ServerEntity() {
        super("serverentity", "psc.serverentity", "Spawn a server selector entity", true);
    }

    @Override
    public boolean finalExe(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        WorldServer worldServer = ((CraftWorld) p.getWorld()).getHandle();

        ServerVillager villager = new ServerVillager(worldServer);
        villager.setLocation(p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), p.getLocation().getYaw(), p.getLocation().getPitch());
        worldServer.addEntity(villager, CreatureSpawnEvent.SpawnReason.CUSTOM);

        NBTTagCompound tag = villager.getNBTTag();
        if(tag == null) {
            tag = new NBTTagCompound();
        }
        villager.c(tag);
        tag.setInt("NoAI", 1);
        villager.f(tag);

        return true;
    }
}
