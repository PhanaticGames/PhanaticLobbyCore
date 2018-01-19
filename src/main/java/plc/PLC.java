package plc;

import code.matthew.psc.utils.core.CommandManager;
import code.matthew.psc.utils.strings.ColorUtil;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import plc.cmd.SetSpawn;
import plc.listeners.env.Explosion;
import plc.listeners.env.MobSpawn;
import plc.listeners.env.Weather;
import plc.listeners.player.*;
import plc.placeholder.OnlinePlayers;
import plc.placeholder.Rank;
import plc.serverselector.Serverselector;
import plc.time.ITimeCheck;
import plc.util.FileUtil;
import plc.util.ItemUtil;
import plc.util.Placeholder;

import java.util.ArrayList;

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

        CommandManager.regCommand(new SetSpawn());
		
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
		String name = ColorUtil.colorStr(FileUtil.getConfig().getString("hideName"));
		hideMeta.setDisplayName(name);
		hidePlayersItem.setItemMeta(hideMeta);
		
		serverSelectorItem = new ItemStack(Material.getMaterial(FileUtil.getConfig().getString("serverSelecterMat")));
		ItemMeta serverSelectorItemMeta = serverSelectorItem.getItemMeta();
		String name2 = ColorUtil.colorStr(FileUtil.getConfig().getString("serverSelecterName"));
		serverSelectorItemMeta.setDisplayName(name2);
		serverSelectorItemMeta.setLore(ColorUtil.colorList(FileUtil.getConfig().getStringList("serverSelecterLore")));
		serverSelectorItem.setItemMeta(serverSelectorItemMeta);
	}
	
	public static PLC getInstance() {
		return plc;
	}
}
