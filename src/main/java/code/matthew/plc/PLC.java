package code.matthew.plc;

import code.matthew.plc.api.IEntityPersist;
import code.matthew.plc.api.IWait;
import code.matthew.plc.cmd.*;
import code.matthew.plc.entity.ServerVillager;
import code.matthew.plc.listeners.env.*;
import code.matthew.plc.listeners.player.*;
import code.matthew.plc.placeholder.OnlinePlayers;
import code.matthew.plc.placeholder.Rank;
import code.matthew.plc.serverselector.Serverselector;
import code.matthew.plc.time.ITimeCheck;
import code.matthew.plc.util.FileUtil;
import code.matthew.plc.util.ItemUtil;
import code.matthew.plc.util.Placeholder;
import code.matthew.psc.PSC;
import code.matthew.psc.utils.core.CommandManager;
import code.matthew.psc.utils.strings.ColorUtil;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import net.minecraft.server.v1_8_R3.EntityVillager;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class PLC extends JavaPlugin {

	private FileUtil util;

	public static ItemStack hidePlayersItem;
	public static ItemStack serverSelectorItem;
	
	public static ArrayList<Player> hidden = new ArrayList<>();
	public static ArrayList<Player> staffMode = new ArrayList<>();
	public static Map<Player, Entity> playerAndEntity = new HashMap<>();
    public static Map<Player, IWait> waitingFor = new HashMap<>();
    public static List<IEntityPersist> saveTheseBois = new ArrayList<>();

    public static boolean ranEntityMetaDataSet = false;

	private static PLC plc;
	private ProtocolManager protocolManager;

    @Override
    public void onLoad() {
        PSC.getInstance().getNmsUtil().regiserEntity("Server Villager", 120, EntityVillager.class, ServerVillager.class);
    }

    @Override
	public void onEnable() {
		plc = this;
		util = new FileUtil(this);
		this.protocolManager = ProtocolLibrary.getProtocolManager();
		
		Placeholder.registerServerPlaceholder(new OnlinePlayers());
		Placeholder.registerPlayerPlaceholder(new Rank(this));
		
		ItemUtil.scanForItems(FileUtil.getSS());

		readEntityData();

		regListeners();

		setupItems();

		Serverselector.setup(FileUtil.getSS());
		
		getServer().getPluginManager().registerEvents(new Interact(), this);

        CommandManager.regCommand(new SetSpawn());
        CommandManager.regCommand(new Spawn());
        CommandManager.regCommand(new StaffMode());
        CommandManager.regCommand(new ServerEntity());
        CommandManager.regCommand(new EditEntity());

		this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		
		new ITimeCheck().runTaskTimer(this, 0, 20L);
	}
	
	@Override
	public void onDisable() {
	    for(IEntityPersist entityPersist : saveTheseBois) {
	        FileUtil.getSpawnData().set("saved." + entityPersist.uuid().toString() , entityPersist.value());
        }
        FileUtil.saveEntityData();
	}

	private void regListeners() {
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
        getServer().getPluginManager().registerEvents(new BlockBreak(), this);
        getServer().getPluginManager().registerEvents(new CropTrample(), this);
        getServer().getPluginManager().registerEvents(new BlockPlace(), this);
        getServer().getPluginManager().registerEvents(new PlayerEntityInteract(), this);
        getServer().getPluginManager().registerEvents(new ChunkUnload(), this);
        getServer().getPluginManager().registerEvents(new Chat(), this);
        getServer().getPluginManager().registerEvents(new InventoryClose(), this);
        getServer().getPluginManager().registerEvents(new WorldUnload(), this);
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

	public static IEntityPersist getEntityPres(UUID uuid) {
        for(IEntityPersist entityPersist : saveTheseBois) {
            if(entityPersist.uuid().equals(uuid)) {
                return entityPersist;
            }
        }
        return null;
    }

    private void readEntityData() {
        ConfigurationSection section = FileUtil.getEntityData().getConfigurationSection("saved");
        if(section != null) {
            for(String key : section.getKeys(false)) {
                UUID uuid = UUID.fromString(key);
                String id = FileUtil.getEntityData().getString("saved." + key);
                saveTheseBois.add(new IEntityPersist() {
                    @Override
                    public UUID uuid() {
                        return uuid;
                    }

                    @Override
                    public String value() {
                        return id;
                    }
                });
            }
        }
    }
}
