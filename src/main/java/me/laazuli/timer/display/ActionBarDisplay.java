package me.laazuli.timer.display;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class ActionBarDisplay implements Display {
    private final Player player;
    private String text;

    public ActionBarDisplay(Player player) {
        this.player = player;
        this.text = "";
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void render(int x, int y, int width, int height) {
        this.player.displayClientMessage(Component.literal(text), true);
    }
}
