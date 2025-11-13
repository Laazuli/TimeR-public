package me.laazuli.timer.gui.screen;

import me.laazuli.timer.TimeR;
import me.laazuli.timer.gui.widget.DraggableStringWidget;
import me.laazuli.timer.time.Timer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

@Environment(EnvType.CLIENT)
public class TimerSettingsScreen extends Screen {
    private final Screen last;

    private final DraggableStringWidget timerWidget = new DraggableStringWidget(Component.literal(Timer.formatTimer(TimeR.TIMER)), Minecraft.getInstance().font);

    public TimerSettingsScreen(Screen last) {
        super(Component.literal("TimeR Settings"));
        this.last = last;
    }

    @Override
    protected void init() {
        timerWidget.setPosition(TimeR.DISPLAY_X, TimeR.DISPLAY_Y);
        this.addRenderableWidget(this.timerWidget);
    }

    @Override
    public void onClose() {
        this.minecraft.setScreen(this.last);
    }

    @Override
    public void removed() {
        this.saveOptions();
    }

    public void saveOptions() {
        TimeR.DISPLAY_X = this.timerWidget.getX();
        TimeR.DISPLAY_Y = this.timerWidget.getY();
    }
}
