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

    private DraggableStringWidget timerWidget;

    public TimerSettingsScreen(Screen last) {
        super(Component.literal("TimeR Settings"));
        this.last = last;
    }

    @Override
    protected void init() {
        this.timerWidget = new DraggableStringWidget(Component.literal(Timer.formatTimer(TimeR.TIMER)), Minecraft.getInstance().font, () -> this.width, () -> this.height);
        this.timerWidget.setPosition(TimeR.RENDERER.x, TimeR.RENDERER.y);
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
        TimeR.RENDERER.x = this.timerWidget.getX();
        TimeR.RENDERER.y = this.timerWidget.getY();
    }
}
