package code.matthew.plc.cmd;

import code.matthew.plc.PLC;
import code.matthew.plc.util.FileUtil;
import code.matthew.psc.api.command.ICommand;
import code.matthew.psc.utils.strings.ColorUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StaffMode extends ICommand {

    public StaffMode() {
        super("staff", "psc.staff", "Enter/Exit staff mode", true);
    }

    @Override
    public boolean finalExe(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        if (PLC.staffMode.contains(p)) {
            PLC.staffMode.remove(p);
            sender.sendMessage(ColorUtil.colorStr(FileUtil.getConfig().getString("staffOff")));
        } else {
            PLC.staffMode.add(p);
            sender.sendMessage(ColorUtil.colorStr(FileUtil.getConfig().getString("staffOn")));
        }
        return true;
    }
}
