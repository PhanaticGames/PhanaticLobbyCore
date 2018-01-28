package code.matthew.plc.listeners.player;

import code.matthew.plc.PLC;
import code.matthew.plc.api.IWait;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class Chat implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        if(PLC.waitingFor.containsKey(e.getPlayer())) {
            IWait wait = PLC.waitingFor.get(e.getPlayer());
            wait.run(e.getMessage());
            PLC.waitingFor.remove(e.getPlayer());
            e.setCancelled(true);
        }
    }
}
