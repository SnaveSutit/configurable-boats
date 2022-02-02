package net.snavesutit.configurable_boats.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.snavesutit.configurable_boats.config.data.ConfigData;

import java.io.*;
import java.nio.file.Paths;

public class ConfigManager {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    private static Config CONFIG;

    public static Config getConfig() {
        return CONFIG;
    }

    public static Boolean loadConfig() {

        // Instead of nullifying just initialize a default config to prevent crashes.
        // (And avoid having to make default try catch blocks for all config values in-use)
        CONFIG = new Config(new ConfigData());
        try {
            File configDir = Paths.get("", "config", "configurable_boats").toFile();

            ConfigData config;

            File configFile = new File(configDir, "config.json");

            if (configFile.exists()) {
                config = GSON.fromJson(new InputStreamReader(new FileInputStream(configFile), "UTF-8"), ConfigData.class);
            } else {
                configDir.mkdirs();
                config = new ConfigData();
            }

            config = verifyConfigData(config);

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(configFile), "UTF-8"));
            writer.write(GSON.toJson(config));
            writer.close();

            System.out.println("Successfully updated configurable_boats config");

            CONFIG = new Config(config);
            return true;

        } catch (IOException exception) {
            System.out.println("Something went wrong reading the configurable_boats config!");
            exception.printStackTrace();
            return false;
        }
    }

    private static ConfigData verifyConfigData(ConfigData configData) {
        Config defaultConfig = new Config(new ConfigData());

        if ((float) configData.collisionBoxSizeX == 0f) {
            configData.collisionBoxSizeX = defaultConfig.collisionBoxSizeX;
        }

        if ((float) configData.collisionBoxSizeY == 0f) {
            configData.collisionBoxSizeY = defaultConfig.collisionBoxSizeY;
        }

        if ((float) configData.collisionBoxSizeZ == 0f) {
            configData.collisionBoxSizeZ = defaultConfig.collisionBoxSizeZ;
        }

        return configData;
    }
}
