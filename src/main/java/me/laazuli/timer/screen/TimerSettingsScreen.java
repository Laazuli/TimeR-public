package me.laazuli.timer.screen;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class TimerSettingsScreen extends Screen {
    private final Screen last;

    public TimerSettingsScreen(Screen last) {
        super(Component.literal("TimeR Settings"));
        this.last = last;
    }

    @Override
    public void onClose() {
        this.minecraft.setScreen(this.last);
    }
}
