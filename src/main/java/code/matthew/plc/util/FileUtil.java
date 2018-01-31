package code.matthew.plc.util;

import code.matthew.plc.PLC;
import code.matthew.psc.api.file.ConfigFile;
import code.matthew.psc.utils.logs.Logger;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class FileUtil {

    private static ConfigFile config;
    private static ConfigFile sb;
    private static ConfigFile ss;
    private static ConfigFile esi;
    private static File spawnDataFile;
    private static FileConfiguration spawnData;
    private static File entityDataFile;
    private static FileConfiguration entityData;

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

        esi = new ConfigFile(plc, "entityserverids.yml");
        esi.setup();
        esi.reload();

        if (!plc.getDataFolder().exists()) {
            plc.getDataFolder().mkdir();
        }

        spawnDataFile = new File(plc.getDataFolder() + File.separator + "spawn.yml");

        if (!spawnDataFile.exists()) {
            plc.saveResource("spawn.yml", false);
        }

        entityDataFile = new File(plc.getDataFolder() + File.separator + "entityDat.yml");

        if (!spawnDataFile.exists()) {
            try {
                entityDataFile.createNewFile();
            } catch (IOException ex) {
                Logger.log(Logger.LogType.ERROR, "ERROR SAVING ENTITY DATA FILE");
                if (Logger.isDebug()) {
                    ex.printStackTrace();
                }
            }
        }

        reload();
    }

    public static void reload() {
        spawnData = YamlConfiguration.loadConfiguration(spawnDataFile);
        entityData = YamlConfiguration.loadConfiguration(entityDataFile);
    }

    public static FileConfiguration getConfig() {
        return config.getConfiguration();
    }

    public static FileConfiguration getESI() {
        return esi.getConfiguration();
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

    public static FileConfiguration getEntityData() {
        return entityData;
    }

    public static void saveSpawnData() {
        try {
            spawnData.save(spawnDataFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveEntityData() {
        try {
            entityData.save(entityDataFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
