package com.pixeldv.commons.yaml;

import java.io.File;
import java.util.Collections;
import java.util.List;

import static org.bukkit.ChatColor.translateAlternateColorCodes;

public class ColorizedYamlConfiguration
        extends LoadedYamlConfiguration {
    protected ColorizedYamlConfiguration(File file) {
        super(file);
    }

    @Override
    public String getString(String path) {
        String message = super.getString(path);

        if (message == null) {
            return path;
        }

        return colorize(message);
    }

    @Override
    public List<String> getStringList(String path) {
        List<String> list = super.getStringList(path);

        if (list.isEmpty()) {
            return Collections.singletonList(path);
        }

        list.replaceAll(this::colorize);
        return list;
    }

    public String colorize(String value) {
        return translateAlternateColorCodes('&', value);
    }
}
