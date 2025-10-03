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
import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.player.Player;

public class TimeR implements ClientModInitializer {

    public static final Timer TIMER = new SystemTimeTimer();
    public static Display DISPLAY;
    public static final TimerCommand TIMER_COMMAND = new TimerCommand(TIMER);

    @Override
    public void onInitializeClient() {
         DISPLAY = new ActionBarDisplay(Minecraft.getInstance().player);

        TIMER_COMMAND.register();

//        ClientWorldEvents.AFTER_CLIENT_WORLD_CHANGE.register((minecraft, clientLevel) -> {
//            if (!timer.isRunning()) {
//                timer.run();
//            }
//            else {
//                timer.set(3600000);
//            }
//        });

        ClientTickEvents.START_WORLD_TICK.register(clientLevel -> {
           renderTimer(TIMER, DISPLAY, Minecraft.getInstance().player);
        });
    }

    private static void renderTimer(Timer timer, Display display, Player player) {
        if (timer == null || display == null || player == null) /* TODO: PRINT WARNING */ return;

        display.setText(String.format("%02d:%02d:%02d", timer.getHours(), timer.getMinutes()%60 , timer.getSeconds()%60));
        display.render(10, 10, 10, 10);
    }
}
