package me.laazuli.timer.display;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphics;
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
    public void setX(int x) {}

    @Override
    public void setY(int y) {}

    @Override
    public void render(GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
        this.player.displayClientMessage(Component.literal(text), true);
    }
}
