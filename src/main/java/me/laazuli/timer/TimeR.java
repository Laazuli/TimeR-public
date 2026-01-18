package me.laazuli.timer;

import me.laazuli.timer.command.TimerCommand;
import me.laazuli.timer.renderer.SimpleTextRenderer;
import me.laazuli.timer.timer.SimpleTimer;
import me.laazuli.timer.timer.UpdateOnGetterTimer;
import net.fabricmc.api.ClientModInitializer;

public class TimeR implements ClientModInitializer {
    public static final String MOD_ID = "timer";

    public static final SimpleTimer TIMER = new UpdateOnGetterTimer();

    public static final TimerManager MANAGER = new TimerManager(TIMER);

    public static final SimpleTextRenderer RENDERER = new SimpleTextRenderer(MOD_ID, "timer", () -> SimpleTimer.formatTimer(TIMER), 0, 0);

    public static final TimerCommand TIMER_COMMAND = new TimerCommand(TIMER);

    @Override
    public void onInitializeClient() {
        MANAGER.register();

        RENDERER.register();

        TIMER_COMMAND.register();
    }
}
