package me.laazuli.timer.display;

import me.laazuli.timer.time.Timer;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphics;

public interface Display {
//    void setText(String text);

    void setX(int x);

    void setY(int y);

    void render(GuiGraphics guiGraphics, DeltaTracker deltaTracker);

    static String formatTimer(Timer timer) {
        return String.format("%02d:%02d:%02d", timer.getHours(), timer.getMinutes()%60 , timer.getSeconds()%60);
    }
}
