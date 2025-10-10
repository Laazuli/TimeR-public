package me.laazuli.timer.display;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphics;

public interface Display {
    void setText(String text);

    void setX(int x);

    void setY(int y);

    void render(GuiGraphics guiGraphics, DeltaTracker deltaTracker);
}
