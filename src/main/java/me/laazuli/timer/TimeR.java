package me.laazuli.timer;

import me.laazuli.timer.display.ActionBarDisplay;
import me.laazuli.timer.display.Display;
import me.laazuli.timer.time.SystemTimeTimer;
import me.laazuli.timer.time.Timer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientWorldEvents;
import net.minecraft.client.Minecraft;

public class TimeR implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        Timer timer = new SystemTimeTimer();
        Display display = new ActionBarDisplay("initial text");

        ClientWorldEvents.AFTER_CLIENT_WORLD_CHANGE.register((minecraft, clientLevel) -> {
            if (!timer.isRunning()) {
                timer.run();
            }
            else {
                timer.set(3600000);
            }
        });

        ClientTickEvents.START_WORLD_TICK.register(clientLevel -> {
            if (Minecraft.getInstance().player == null) /* TODO: PRINT WARNING */ return;
            display.setText(translateTimer(timer.getMillis()));
            display.render(Minecraft.getInstance().player);
        });
    }

    private static String translateTimer(long millis) {
        return String.format("%02d:%02d:%02d", millis/3600000, (millis/60000)%60 , (millis/1000)%60);
    }
}
