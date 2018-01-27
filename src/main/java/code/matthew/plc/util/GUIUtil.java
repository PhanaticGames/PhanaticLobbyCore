package code.matthew.plc.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class GUIUtil {

    public static Inventory getEntityGUI(Entity entity) {

        if(entity.getType() == EntityType.VILLAGER) {
            Inventory inv = Bukkit.createInventory(null, 9, ChatColor.GREEN + "Edit entity");

            ItemStack exit = new ItemStack(Material.BARRIER);
            ItemStack editName = new ItemStack(Material.BOOK_AND_QUILL);
            ItemStack lookAtMe = new ItemStack(Material.BLAZE_POWDER);
            ItemStack move = new ItemStack(Material.BEDROCK);

            ItemMeta exitMeta = exit.getItemMeta();
            ItemMeta editMeta = editName.getItemMeta();
            ItemMeta lookMeta = lookAtMe.getItemMeta();
            ItemMeta moveMeta = move.getItemMeta();

            List<String> exitLore = new ArrayList<>();
            List<String> editLore = new ArrayList<>();
            List<String> lookLore = new ArrayList<>();
            List<String> moveLore = new ArrayList<>();

            exitLore.add(ChatColor.GREEN + "Use this to exit this GUI");
            editLore.add(ChatColor.GREEN + "Use this to edit this entity's name");
            lookLore.add(ChatColor.GREEN + "Use this to make this entity look at you");
            moveLore.add(ChatColor.GREEN + "Use this to move the entity");

            exitMeta.setDisplayName(ChatColor.RED + "Exit");
            editMeta.setDisplayName(ChatColor.AQUA + "Edit name");
            lookMeta.setDisplayName(ChatColor.AQUA + "Look at me");
            moveMeta.setDisplayName(ChatColor.AQUA + "Move");

            exitMeta.setLore(exitLore);
            editMeta.setLore(editLore);
            lookMeta.setLore(lookLore);
            moveMeta.setLore(moveLore);

            exit.setItemMeta(exitMeta);
            editName.setItemMeta(editMeta);
            lookAtMe.setItemMeta(lookMeta);
            move.setItemMeta(moveMeta);

            inv.setItem(8, exit);
            inv.setItem(0, editName);
            inv.setItem(1, lookAtMe);
            inv.setItem(4, move);
            inv.setItem(8, exit);

            return inv;
        }
        return null;
    }
}
