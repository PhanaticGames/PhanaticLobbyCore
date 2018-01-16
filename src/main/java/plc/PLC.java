package plc;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;

import plc.listeners.env.Explosion;
import plc.listeners.env.MobSpawn;
import plc.listeners.env.Weather;
import plc.listeners.player.Damage;
import plc.listeners.player.Drop;
import plc.listeners.player.Hunger;
import plc.listeners.player.Interact;
import plc.listeners.player.InvClick;
import plc.listeners.player.Join;
import plc.listeners.player.Leave;
import plc.placeholder.OnlinePlayers;
import plc.placeholder.Rank;
import plc.serverselector.Serverselector;
import plc.time.ITimeCheck;
import plc.util.FileUtil;
import plc.util.ItemUtil;
import plc.util.Placeholder;
import plc.util.StringUtil;
import net.md_5.bungee.api.ChatColor;

public class PLC extends JavaPlugin {

	private FileUtil util;
	
	public static ItemStack hidePlayersItem;
	public static ItemStack serverSelectorItem;
	
	public static ArrayList<Player> hidden = new ArrayList<>();
	
	private static PLC plc;
	private ProtocolManager protocolManager;
	
	@Override
	public void onEnable() {
		plc = this;
		util = new FileUtil(this);
		this.protocolManager = ProtocolLibrary.getProtocolManager();
		
		Placeholder.registerServerPlaceholder(new OnlinePlayers());
		Placeholder.registerPlayerPlaceholder(new Rank(this));
		
		ItemUtil.scanForItems(FileUtil.getSS());
		
		getServer().getPluginManager().registerEvents(new Join(), this);
		getServer().getPluginManager().registerEvents(new Leave(), this);
		getServer().getPluginManager().registerEvents(new InvClick(), this);
		getServer().getPluginManager().registerEvents(new Drop(), this);
		getServer().getPluginManager().registerEvents(new Damage(), this);
		getServer().getPluginManager().registerEvents(new Weather(), this);
		getServer().getPluginManager().registerEvents(new Explosion(), this);
		getServer().getPluginManager().registerEvents(new Hunger(), this);
		getServer().getPluginManager().registerEvents(new MobSpawn(), this);
		
		setupItems();

		Serverselector.setup(FileUtil.getSS());
		
		getServer().getPluginManager().registerEvents(new Interact(), this);
		
		getCommand("setspawn").setExecutor(this);
		
		this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		
		new ITimeCheck().runTaskTimer(this, 0, 20L);
	}
	
	@Override
	public void onDisable() {
	
	}
	
	public ProtocolManager getProtocal() {
		return protocolManager;
	}
	
	public FileUtil getFileUtil() {
		return util;
	}
	
	private void setupItems() {
		hidePlayersItem = new ItemStack(Material.TORCH);
		ItemMeta hideMeta = hidePlayersItem.getItemMeta();
		String name = StringUtil.colorize(FileUtil.getConfig().getString("hideName"));
		hideMeta.setDisplayName(name);
		hidePlayersItem.setItemMeta(hideMeta);
		
		serverSelectorItem = new ItemStack(Material.getMaterial(FileUtil.getConfig().getString("serverSelecterMat")));
		ItemMeta serverSelectorItemMeta = serverSelectorItem.getItemMeta();
		String name2 = StringUtil.colorize(FileUtil.getConfig().getString("serverSelecterName"));
		serverSelectorItemMeta.setDisplayName(name2);
		serverSelectorItemMeta.setLore(StringUtil.colorizeList(FileUtil.getConfig().getStringList("serverSelecterLore")));
		serverSelectorItem.setItemMeta(serverSelectorItemMeta);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(label.equalsIgnoreCase("setspawn")) {
			if(sender.isOp()) {
				if(sender instanceof Player) {
					Player p = (Player) sender;
					FileUtil.getSpawnData().set("spawnX", p.getLocation().getX());
					FileUtil.getSpawnData().set("spawnY", p.getLocation().getY());
					FileUtil.getSpawnData().set("spawnZ", p.getLocation().getZ());
					FileUtil.getSpawnData().set("spawnPitch", p.getEyeLocation().getPitch());
					FileUtil.getSpawnData().set("spawnYaw", p.getEyeLocation().getYaw());
					FileUtil.saveSpawnData();
					p.sendMessage(ChatColor.BLUE + "Spawn set");
				}
			} else {
				return false;
			}
		}
		return false;
	}
	
	public static PLC getInstance() {
		return plc;
	}
}
