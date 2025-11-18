package me.laazuli.timer;

import me.laazuli.timer.command.TimerCommand;
import me.laazuli.timer.renderer.SimpleTextRenderer;
import me.laazuli.timer.time.SystemTimeTimer;
import me.laazuli.timer.time.Timer;
import net.fabricmc.api.ClientModInitializer;

public class TimeR implements ClientModInitializer {
    public static final String MOD_ID = "timer";

    public static final Timer TIMER = new SystemTimeTimer();

    public static final SimpleTextRenderer RENDERER = new SimpleTextRenderer(MOD_ID, "timer", () -> Timer.formatTimer(TIMER));

    public static final TimerCommand TIMER_COMMAND = new TimerCommand(TIMER);

    @Override
    public void onInitializeClient() {
        RENDERER.initialize();

        TIMER_COMMAND.register();
    }
}
