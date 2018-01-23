package code.matthew.plc;

import code.matthew.plc.cmd.SetSpawn;
import code.matthew.plc.cmd.Spawn;
import code.matthew.plc.listeners.env.Explosion;
import code.matthew.plc.listeners.env.MobSpawn;
import code.matthew.plc.listeners.env.Weather;
import code.matthew.plc.listeners.player.*;
import code.matthew.plc.placeholder.OnlinePlayers;
import code.matthew.plc.placeholder.Rank;
import code.matthew.plc.serverselector.Serverselector;
import code.matthew.plc.time.ITimeCheck;
import code.matthew.plc.util.FileUtil;
import code.matthew.plc.util.ItemUtil;
import code.matthew.plc.util.Placeholder;
import code.matthew.psc.utils.core.CommandManager;
import code.matthew.psc.utils.strings.ColorUtil;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

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
		getServer().getPluginManager().registerEvents(new Move(), this);
		
		setupItems();

		Serverselector.setup(FileUtil.getSS());
		
		getServer().getPluginManager().registerEvents(new Interact(), this);

        CommandManager.regCommand(new SetSpawn());
        CommandManager.regCommand(new Spawn());
		
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
