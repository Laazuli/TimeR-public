package me.laazuli.timer;

import me.laazuli.timer.command.TimerCommand;
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
        TimerCommand timerCommand = new TimerCommand(timer);

        timerCommand.register();

//        ClientWorldEvents.AFTER_CLIENT_WORLD_CHANGE.register((minecraft, clientLevel) -> {
//            if (!timer.isRunning()) {
//                timer.run();
//            }
//            else {
//                timer.set(3600000);
//            }
//        });

        ClientTickEvents.START_WORLD_TICK.register(clientLevel -> {
            if (Minecraft.getInstance().player == null) /* TODO: PRINT WARNING */ return;
            display.setText(String.format("%02d:%02d:%02d", timer.getHours(), timer.getMinutes()%60 , timer.getSeconds()%60));
            display.render(Minecraft.getInstance().player);
        });
    }
}
