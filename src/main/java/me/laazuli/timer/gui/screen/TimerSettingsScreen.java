package me.laazuli.timer.gui.screen;

import me.laazuli.timer.TimeR;
import me.laazuli.timer.gui.widget.DraggableStringWidget;
import me.laazuli.timer.timer.SimpleTimer;
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
        this.timerWidget = new DraggableStringWidget(Component.literal(SimpleTimer.formatTimer(TimeR.TIMER)), Minecraft.getInstance().font, () -> this.width, () -> this.height);
        this.timerWidget.setPosition((int) (TimeR.RENDERER.getRelativeX() * this.width), (int) (TimeR.RENDERER.getRelativeY() * this.height));
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
        TimeR.RENDERER.setRelativeX((double) this.timerWidget.getX() / this.width);
        TimeR.RENDERER.setRelativeY((double) this.timerWidget.getY() / this.height);
    }
}
