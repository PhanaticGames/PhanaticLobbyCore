package code.matthew.plc.entity;

import net.minecraft.server.v1_8_R3.*;
import org.bukkit.ChatColor;

public class ServerVillager extends EntityVillager {

    public ServerVillager(World world) {
        super(world);
    }

    protected void initAttributes() {
        super.initAttributes();
        this.setCustomName(ChatColor.GREEN + "Custom villager");
        this.setCustomNameVisible(true);
        this.setInvisible(false);
        this.persistent = true;
    }

}
