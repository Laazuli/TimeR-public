package me.laazuli.timer.display;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.network.chat.Component;

public class StringWidgetDisplay implements Display {
    private String text;
    private int x;
    private int y;

    public StringWidgetDisplay() {
        this.text = "initial text";
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void setText(String text) {
        this.text = text;
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
        StringWidget timer = new StringWidget(Component.literal(this.text), Minecraft.getInstance().font);
        timer.setPosition(this.x, this.y);
        timer.renderWidget(guiGraphics, (int) Minecraft.getInstance().mouseHandler.xpos(), (int) Minecraft.getInstance().mouseHandler.ypos(), deltaTracker.getGameTimeDeltaPartialTick(false));
    }
}
