package plc.util;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import plc.PLC;

public class FileUtil {

	private static File configFile;
	private static FileConfiguration config;
	private static File sbFile;
	private static FileConfiguration sb;
	private static File ssFile;
	private static FileConfiguration ss;
	private static File spawnDataFile;
	private static FileConfiguration spawnData;
	
	private PLC plc;
	
	public FileUtil(PLC plc) {
		this.plc = plc;
		init();
	}
	
	private void init() {
		if(!plc.getDataFolder().exists()) {
			plc.getDataFolder().mkdir();
		}
		
		configFile = new File(plc.getDataFolder() + File.separator + "config.yml");
		
		if(!configFile.exists()) {
			plc.saveResource("config.yml", false);
		}
		
		sbFile = new File(plc.getDataFolder() + File.separator + "sb.yml");
		
		if(!sbFile.exists()) {
			plc.saveResource("sb.yml", false);
		}
		
		ssFile = new File(plc.getDataFolder() + File.separator + "serverselector.yml");
		
		if(!ssFile.exists()) {
			plc.saveResource("serverselector.yml", false);
		}
		
		spawnDataFile = new File(plc.getDataFolder() + File.separator + "spawn.yml");
		
		if(!spawnDataFile.exists()) {
			plc.saveResource("spawn.yml", false);
		}
		
		reload();
	}
	
	public static void reload() {
		config = YamlConfiguration.loadConfiguration(configFile);
		sb = YamlConfiguration.loadConfiguration(sbFile);
		ss = YamlConfiguration.loadConfiguration(ssFile);
		spawnData = YamlConfiguration.loadConfiguration(spawnDataFile);
	}
	
	public static FileConfiguration getConfig() {
		return config;
	}
	
	public static FileConfiguration getSB() {
		return sb;
	}
	
	public static FileConfiguration getSS() {
		return ss;
	}
	
	public static FileConfiguration getSpawnData() {
		return spawnData;
	}
	
	public static void saveSpawnData() {
		try {
			spawnData.save(spawnDataFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
