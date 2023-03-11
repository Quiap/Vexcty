package com.moonsworkshop.vexcty.config;

import com.moonsworkshop.vexcty.Vexcty;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ConfigManager {

    private static FileConfiguration config;

    public static void setConfiguration(FileConfiguration c) {
        config = c;
    }

    private Map<ConfigType, ConfigHandler> configurations;

    public ConfigManager() {
        configurations = new HashMap<>();
    }

    public void loadFiles(Vexcty plugin) {

        registerFile(ConfigType.SETTINGS, new ConfigHandler(plugin, "config"));
        registerFile(ConfigType.MESSAGES, new ConfigHandler(plugin, "messages"));
        registerFile(ConfigType.DATA, new ConfigHandler(plugin, "data"));
        registerFile(ConfigType.COMMANDS, new ConfigHandler(plugin, "commands"));

        configurations.values().forEach(ConfigHandler::saveDefaultConfig);

        setConfiguration(getFile(ConfigType.MESSAGES).getConfig(ConfigType.SETTINGS));
    }

    public ConfigHandler getFile(ConfigType type) {
        return configurations.get(type);
    }

    public void reloadFiles() {
        configurations.values().forEach(ConfigHandler::reload);
        setConfiguration(getFile(ConfigType.MESSAGES).getConfig(ConfigType.SETTINGS));
    }

    public void saveFiles() {
        getFile(ConfigType.DATA).save();
    }

    public void registerFile(ConfigType type, ConfigHandler config) {
        configurations.put(type, config);
    }

    public FileConfiguration getFileConfiguration(File file) {
        return YamlConfiguration.loadConfiguration(file);
    }

}
