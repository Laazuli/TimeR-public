package me.laazuli.timer;

import me.laazuli.timer.command.TimerCommand;
import me.laazuli.timer.time.SystemTimeTimer;
import me.laazuli.timer.time.Timer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

public class TimeR implements ClientModInitializer {
    public static final String MOD_ID = "timer";

    public static final Timer TIMER = new SystemTimeTimer();

    public static int DISPLAY_X;
    public static int DISPLAY_Y;

    public static final TimerCommand TIMER_COMMAND = new TimerCommand(TIMER);

    @Override
    public void onInitializeClient() {
        HudRenderCallback.EVENT.register((guiGraphics, deltaTracker) -> {
            TimeR.renderTimer(guiGraphics, deltaTracker, Minecraft.getInstance());
        });

        TIMER_COMMAND.register();
    }

    public static void renderTimer(GuiGraphics guiGraphics, DeltaTracker deltaTracker, Minecraft minecraft) {
        minecraft.getProfiler().push("timer:timer");

        String timerString = Timer.formatTimer(TIMER);
        guiGraphics.drawStringWithBackdrop(minecraft.font, Component.literal(timerString), DISPLAY_X, DISPLAY_Y, minecraft.font.width(timerString), -1);

        minecraft.getProfiler().pop();
    }
}
