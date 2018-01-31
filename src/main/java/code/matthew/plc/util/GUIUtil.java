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
            ItemStack setServerID = new ItemStack(Material.BOOK);
            ItemStack lookAtMe = new ItemStack(Material.BLAZE_POWDER);
            ItemStack move = new ItemStack(Material.BEDROCK);
            ItemStack kill = new ItemStack(Material.DIAMOND_SWORD);

            ItemMeta exitMeta = exit.getItemMeta();
            ItemMeta editMeta = editName.getItemMeta();
            ItemMeta setMeta = setServerID.getItemMeta();
            ItemMeta lookMeta = lookAtMe.getItemMeta();
            ItemMeta moveMeta = move.getItemMeta();
            ItemMeta killMeta = kill.getItemMeta();

            List<String> exitLore = new ArrayList<>();
            List<String> editLore = new ArrayList<>();
            List<String> setLore = new ArrayList<>();
            List<String> lookLore = new ArrayList<>();
            List<String> moveLore = new ArrayList<>();
            List<String> killLore = new ArrayList<>();

            exitLore.add(ChatColor.GREEN + "Use this to exit this GUI");
            editLore.add(ChatColor.GREEN + "Use this to edit this entity's name");
            setLore.add(ChatColor.GREEN + "Use this to edit the server this entity is mapped to");
            lookLore.add(ChatColor.GREEN + "Use this to make this entity look the way you do");
            moveLore.add(ChatColor.GREEN + "Use this to move the entity");
            killLore.add(ChatColor.GREEN + "Use this to kill this entity");

            exitMeta.setDisplayName(ChatColor.RED + "Exit");
            editMeta.setDisplayName(ChatColor.AQUA + "Edit name");
            setMeta.setDisplayName(ChatColor.AQUA + "Edit server name");
            lookMeta.setDisplayName(ChatColor.AQUA + "Look how I do");
            moveMeta.setDisplayName(ChatColor.AQUA + "Move");
            killMeta.setDisplayName(ChatColor.RED + "Kill");

            exitMeta.setLore(exitLore);
            editMeta.setLore(editLore);
            setMeta.setLore(setLore);
            lookMeta.setLore(lookLore);
            moveMeta.setLore(moveLore);
            killMeta.setLore(killLore);

            exit.setItemMeta(exitMeta);
            editName.setItemMeta(editMeta);
            setServerID.setItemMeta(setMeta);
            lookAtMe.setItemMeta(lookMeta);
            move.setItemMeta(moveMeta);
            kill.setItemMeta(killMeta);

            inv.setItem(8, exit);
            inv.setItem(0, editName);
            inv.setItem(1, setServerID);
            inv.setItem(2, lookAtMe);
            inv.setItem(4, move);
            inv.setItem(7, kill);
            inv.setItem(8, exit);

            return inv;
        }
        return null;
    }
}
