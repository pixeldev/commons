package com.pixeldv.commons.yaml;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class LoadedYamlConfiguration extends YamlConfiguration {

    private final File file;

    protected LoadedYamlConfiguration(File file) {
        this.file = file;
    }

    public static YamlConfigurationBuilder builder() {
        return new YamlConfigurationBuilder();
    }

    public void reload()
            throws IOException,
            InvalidConfigurationException {
        load(file);
    }

    public void save() throws IOException {
        save(file);
    }
}
