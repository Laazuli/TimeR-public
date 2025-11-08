package me.laazuli.timer;

import me.laazuli.timer.command.TimerCommand;
import me.laazuli.timer.time.SystemTimeTimer;
import me.laazuli.timer.time.Timer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.network.chat.Component;

public class TimeR implements ClientModInitializer {
    public static final String MOD_ID = "timer";

    public static final Timer TIMER = new SystemTimeTimer();

    public static AbstractWidget DISPLAY;
//    public static final TimerDisplay DISPLAY = new StringWidgetTimerDisplay(TIMER);
//    public static final Display DISPLAY = new ActionBarDisplay(TIMER);

    public static final TimerCommand TIMER_COMMAND = new TimerCommand(TIMER);

    @Override
    public void onInitializeClient() {
        ClientLifecycleEvents.CLIENT_STARTED.register(client -> {
            DISPLAY = new StringWidget(Component.literal("IF YOU SEE THIS, PLEASE CONTACT THE DEVELOPER!!"), Minecraft.getInstance().font);
        });

        TIMER_COMMAND.register();
    }
}
