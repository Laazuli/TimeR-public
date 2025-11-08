package me.laazuli.timer.display;

import me.laazuli.timer.time.Timer;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.network.chat.Component;

public class StringWidgetTimerDisplay implements TimerDisplay {
    protected final Timer timer;
    protected StringWidget widget;

    public StringWidgetTimerDisplay(Timer timer) {
        this.timer = timer;
    }

    public void setPosition(int x, int y) {
        this.widget.setPosition(x, y);
    }

    @Override
    public void setX(int x) {
        this.widget.setX(x);
    }

    @Override
    public void setY(int y) {
        this.widget.setY(y);
    }

    @Override
    public int getX() {
        return this.widget.getX();
    }

    @Override
    public int getY() {
        return this.widget.getY();
    }

    @Override
    public void initialize() {
        this.widget = new StringWidget(Component.literal("IF YOU SEE THIS, PLEASE CONTACT THE DEVELOPER!!"), Minecraft.getInstance().font);
    }

    @Override
    public void render(GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
        if (widget == null) {
            initialize();
        }
        this.widget.setMessage(Component.literal(TimerDisplay.formatTimer(this.timer)));
        this.widget.renderWidget(guiGraphics, (int) Minecraft.getInstance().mouseHandler.xpos(), (int) Minecraft.getInstance().mouseHandler.ypos(), deltaTracker.getGameTimeDeltaPartialTick(false));
    }
}
