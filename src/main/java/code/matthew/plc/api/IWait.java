package code.matthew.plc.api;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public interface IWait {

    Player player();

    Entity entity();

    void run(String responce);

}
