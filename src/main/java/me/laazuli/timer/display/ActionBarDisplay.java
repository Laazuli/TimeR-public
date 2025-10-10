package me.laazuli.timer.display;

import me.laazuli.timer.time.Timer;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

public class ActionBarDisplay implements Display {
    private final Timer timer;

    public ActionBarDisplay(Timer timer) {
        this.timer = timer;
    }

    @Override
    public void setX(int x) {}

    @Override
    public void setY(int y) {}

    @Override
    public void render(GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
        Minecraft.getInstance().player.displayClientMessage(Component.literal(Display.formatTimer(timer)), true);
    }
}
