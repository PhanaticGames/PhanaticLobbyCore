package code.matthew.plc.entity;

import net.minecraft.server.v1_8_R3.EntityVillager;
import net.minecraft.server.v1_8_R3.World;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity;
import org.bukkit.entity.Villager;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class ServerVillager extends EntityVillager {

    public ServerVillager(World world) {
        super(world);
    }


    protected void initAttributes() {
        super.initAttributes();
        this.setCustomName(ChatColor.GREEN + "Custom villager");
        this.setCustomNameVisible(true);
        this.ai = false;
    }



    public static Villager spawn(Location loc) {
        World world = (World) ((CraftWorld) loc.getWorld()).getHandle();

        final ServerVillager villiger = new ServerVillager(world);

        villiger.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
        ((CraftLivingEntity) villiger.getBukkitEntity()).setRemoveWhenFarAway(false);
        world.addEntity(villiger, CreatureSpawnEvent.SpawnReason.CUSTOM);
        return (Villager) villiger.getBukkitEntity();
    }
}
