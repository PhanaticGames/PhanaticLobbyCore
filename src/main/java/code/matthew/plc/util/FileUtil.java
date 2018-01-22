package code.matthew.plc.util;

import code.matthew.plc.PLC;
import code.matthew.psc.api.file.ConfigFile;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class FileUtil {

    private static ConfigFile config;
    private static ConfigFile sb;
    private static ConfigFile ss;
    private static File spawnDataFile;
    private static FileConfiguration spawnData;

    private PLC plc;

    public FileUtil(PLC plc) {
        this.plc = plc;
        init();
    }

    private void init() {
        config = new ConfigFile(plc, "config.yml");
        config.setup();
        config.reload();

        sb = new ConfigFile(plc, "sb.yml");
        sb.setup();
        sb.reload();

        ss = new ConfigFile(plc, "serverselector.yml");
        ss.setup();
        ss.reload();

        if (!plc.getDataFolder().exists()) {
            plc.getDataFolder().mkdir();
        }

        spawnDataFile = new File(plc.getDataFolder() + File.separator + "spawn.yml");

        if (!spawnDataFile.exists()) {
            plc.saveResource("spawn.yml", false);
        }

        reload();
    }

    public static void reload() {
        spawnData = YamlConfiguration.loadConfiguration(spawnDataFile);
    }

    public static FileConfiguration getConfig() {
        return config.getConfiguration();
    }

    public static FileConfiguration getSB() {
        return sb.getConfiguration();
    }

    public static FileConfiguration getSS() {
        return ss.getConfiguration();
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
