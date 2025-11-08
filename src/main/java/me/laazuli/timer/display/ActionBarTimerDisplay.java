package me.laazuli.timer.display;

import me.laazuli.timer.time.Timer;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

public class ActionBarTimerDisplay implements TimerDisplay {
    private final Timer timer;

    public ActionBarTimerDisplay(Timer timer) {
        this.timer = timer;
    }

    @Override
    public void setX(int x) {}

    @Override
    public void setY(int y) {}

    @Override
    public int getX() {
        return -1;
    }

    @Override
    public int getY() {
        return -1;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void render(GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
        Minecraft.getInstance().player.displayClientMessage(Component.literal(TimerDisplay.formatTimer(timer)), true);
    }
}
