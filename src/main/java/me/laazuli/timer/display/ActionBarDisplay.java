package me.laazuli.timer.display;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;

public class ActionBarDisplay implements Display {
    private String text;

    public ActionBarDisplay(String text) {
        this.text = text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void render(LocalPlayer player) {
        player.displayClientMessage(Component.literal(text), true);
    }
}
