package me.laazuli.timer;

import me.laazuli.timer.config.ConfigManager;
import me.laazuli.timer.config.TimerConfig;
import me.laazuli.timer.timer.SimpleTimer;
import me.laazuli.timer.timer.UpdateOnGetterTimer;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

public class Test {
    public static void main(String[] args) {
//        SimpleTimer timer = new UpdateOnGetterTimer();
//        testTimer(timer);

        testConfigManager();
    }

    private static void testTimer(SimpleTimer timer) {
        System.out.println("Timer created");

        timer.run();
        System.out.println("Timer start");

        print(timer);

        timer.pause();
        System.out.println("Timer paused");

        print(timer);

        timer.run();
        System.out.println("Timer run");

        print(timer);

        timer.reset();
        System.out.println("Timer reset");

        print(timer);
    }

    public static void print(SimpleTimer timer) {
        for (int i = 0; i < 50; i++) {
            System.out.println(/*"[" + new Date().getTime() + "] " + */timer.getMillis());
        }
    }

    private static void testConfigManager() {
        Path path = Path.of("").resolve("dev").resolve("test").resolve("test_config.json");
        ConfigManager manager = new ConfigManager(path);


        TimerConfig config = new TimerConfig();

        config.timerX = 10;
        config.timerY = 25;

        manager.config = config;

        manager.load();

        System.out.println(manager.config);

        manager.config.timerX = 5;

        System.out.println(manager.config);
    }
}
