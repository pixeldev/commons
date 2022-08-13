package com.pixeldv.commons.yaml;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

import static com.pixeldv.commons.Validate.notNull;

public class YamlConfigurationBuilder {

    private boolean colorized;
    private String name;
    private String folderPath;
    private Plugin plugin;

    protected YamlConfigurationBuilder() {

    }

    public YamlConfigurationBuilder colorized() {
        this.colorized = true;
        return this;
    }

    public YamlConfigurationBuilder name(String name) {
        this.name = name + ".yml";
        return this;
    }

    public YamlConfigurationBuilder folderPath(String folderPath) {
        this.folderPath = folderPath;
        return this;
    }

    public YamlConfigurationBuilder plugin(Plugin plugin) {
        this.plugin = plugin;
        return this;
    }

    public LoadedYamlConfiguration build() throws IOException, InvalidConfigurationException {
        notNull(plugin, "plugin");
        notNull(name, "file name");

        File folder;

        if (folderPath == null) {
            folder = plugin.getDataFolder();
        } else {
            folder = new File(plugin.getDataFolder(), folderPath);
        }

        if (!folder.exists()) {
            if (!folder.mkdirs()) {
                throw new IllegalArgumentException(
                        "Couldn't create folder '"
                                + folderPath + "'.");
            }
        }

        File file = new File(folder, name);
        LoadedYamlConfiguration configuration;

        if (colorized) {
            configuration = new ColorizedYamlConfiguration(file);
        } else {
            configuration = new LoadedYamlConfiguration(file);
        }

        if (!file.exists()) {
            String path = folderPath == null ? name :
                    folderPath + File.separator + name;

            if (plugin.getResource(path) != null) {
                plugin.saveResource(path, false);
            } else {
                configuration.save();
            }

            configuration.reload();
        } else {
            configuration.reload();
            configuration.save();
        }

        return configuration;
    }

}
