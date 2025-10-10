package me.laazuli.timer;

import me.laazuli.timer.command.TimerCommand;
import me.laazuli.timer.display.ActionBarDisplay;
import me.laazuli.timer.display.Display;
import me.laazuli.timer.display.StringWidgetDisplay;
import me.laazuli.timer.time.SystemTimeTimer;
import me.laazuli.timer.time.Timer;
import net.fabricmc.api.ClientModInitializer;

public class TimeR implements ClientModInitializer {

    public static final Timer TIMER = new SystemTimeTimer();

    public static final Display DISPLAY = new StringWidgetDisplay(TIMER);
//    public static final Display DISPLAY = new ActionBarDisplay(TIMER);

    public static final TimerCommand TIMER_COMMAND = new TimerCommand(TIMER);

    @Override
    public void onInitializeClient() {
        TIMER_COMMAND.register();
    }
}
