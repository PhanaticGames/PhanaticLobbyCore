package code.matthew.plc.listeners.env;

import code.matthew.plc.PLC;
import code.matthew.plc.api.IEntityPersist;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldUnloadEvent;

import java.util.UUID;

public class WorldUnload implements Listener {

    @EventHandler
    public void onWordUnload(WorldUnloadEvent event) {
        System.out.println("I AM BEING VALLED ");
        for(Entity e : event.getWorld().getEntities()) {
            if(e.getType() == EntityType.VILLAGER) {
                if(e.hasMetadata("id")) {
                    PLC.saveTheseBois.add(new IEntityPersist() {
                        @Override
                        public UUID uuid() {
                            return e.getUniqueId();
                        }

                        @Override
                        public String value() {
                            return e.getMetadata("id").get(0).asString();
                        }
                    });
                }
            }
        }
    }
}
