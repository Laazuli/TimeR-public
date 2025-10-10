package me.laazuli.timer.display;

import me.laazuli.timer.time.Timer;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.network.chat.Component;

public class StringWidgetDisplay implements Display {
    private final Timer timer;
    private int x;
    private int y;

    public StringWidgetDisplay(Timer timer) {
        this.timer = timer;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void render(GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
        StringWidget timer = new StringWidget(Component.literal(Display.formatTimer(this.timer)), Minecraft.getInstance().font);
        timer.setPosition(this.x, this.y);
        timer.renderWidget(guiGraphics, (int) Minecraft.getInstance().mouseHandler.xpos(), (int) Minecraft.getInstance().mouseHandler.ypos(), deltaTracker.getGameTimeDeltaPartialTick(false));
    }
}
