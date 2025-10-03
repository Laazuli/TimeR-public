package me.laazuli.timer.display;

import net.minecraft.world.entity.player.Player;

public interface Display {
    void setText(String text);

    void render(int x, int y, int width, int height);
}
