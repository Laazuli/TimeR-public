package me.laazuli.timer;

import me.laazuli.timer.command.TimerCommand;
import me.laazuli.timer.display.Display;
import me.laazuli.timer.display.StringWidgetDisplay;
import me.laazuli.timer.time.SystemTimeTimer;
import me.laazuli.timer.time.Timer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class TimeR implements ClientModInitializer {

    public static final Timer TIMER = new SystemTimeTimer();
    public static Display DISPLAY = new StringWidgetDisplay();

    public static final TimerCommand TIMER_COMMAND = new TimerCommand(TIMER);

    @Override
    public void onInitializeClient() {
//        DISPLAY = new ActionBarDisplay(Minecraft.getInstance().player);

        TIMER_COMMAND.register();

        ClientTickEvents.START_WORLD_TICK.register(clientLevel -> {
            DISPLAY.setText(formatTimer(TIMER));
        });
//        LocalPlayer localPlayer = Minecraft.getInstance().player;
//        localPlayer.displayClientMessage();
    }

    private static String formatTimer(Timer timer) {
        return String.format("%02d:%02d:%02d", timer.getHours(), timer.getMinutes()%60 , timer.getSeconds()%60);
    }
}
