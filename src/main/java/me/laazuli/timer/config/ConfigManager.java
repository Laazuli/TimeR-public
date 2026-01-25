package me.laazuli.timer.config;

import com.google.gson.Gson;
import org.jetbrains.annotations.Contract;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class ConfigManager {
    private final Path path;
    public TimerConfig config;

    public ConfigManager(Path path) {
        this.path = path;
        this.config = new TimerConfig();
    }

    public void load() {
        String jsonString;

        try {
            jsonString = Files.readString(this.path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.config = new Gson().fromJson(jsonString, TimerConfig.class);

    }

    public void save() {
        String jsonString = new Gson().toJson(this.config);

        try {
            Files.createDirectories(this.path.getParent());
            Files.write(this.path, jsonString.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
