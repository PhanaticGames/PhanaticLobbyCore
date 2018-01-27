package code.matthew.plc.cmd;

import code.matthew.plc.PLC;
import code.matthew.psc.api.command.ICommand;
import code.matthew.psc.utils.strings.ColorUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class EditEntity extends ICommand {

    public EditEntity() {
        super("editentity", "plc.entityedit", "Edit an entity", true);
    }

    @Override
    public boolean finalExe(CommandSender sender, String[] args) {
        Player p = (Player) sender;

        if(!PLC.staffMode.contains(p)) {
            p.sendMessage(ChatColor.RED + "You must be in staff mode is get this item");
            return false;
        }

        ItemStack stick = new ItemStack(Material.STICK);
        ItemMeta meta = stick.getItemMeta();
        List<String> lore = new ArrayList<>();

        lore.add(ChatColor.GOLD + "Use this to edit an entity");
        meta.setDisplayName(ColorUtil.colorStr("&2Entity Editor"));
        meta.setLore(lore);
        stick.setItemMeta(meta);

        p.getInventory().addItem(stick);
        return true;
    }
}
