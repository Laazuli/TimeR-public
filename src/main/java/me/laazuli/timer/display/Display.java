package me.laazuli.timer.display;

import net.minecraft.client.player.LocalPlayer;

public interface Display {
    void setText(String text);

    void render(LocalPlayer player);
}
