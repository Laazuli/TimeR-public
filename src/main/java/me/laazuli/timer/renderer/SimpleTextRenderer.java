package me.laazuli.timer.renderer;

import me.laazuli.timer.TimeR;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

import java.util.function.Supplier;

public class SimpleTextRenderer {
    public final String namespace;
    public final String id;
    public final Supplier<String> textSupplier;
    private int xOffset;
    private int yOffset;

    public SimpleTextRenderer(String namespace, String id, Supplier<String> textSupplier, int xOffsetCenter, int yOffsetCenter) {
        this.namespace = namespace;
        this.id = id;
        this.textSupplier = textSupplier;
        this.xOffset = xOffsetCenter;
        this.yOffset = yOffsetCenter;
    }

//    public SimpleTextRenderer(String namespace, String id, Supplier<String> textSupplier, int x, int y, int screenWidth, int screenHeight) {
//        this(namespace, id, textSupplier, x - screenWidth / 2, y - screenHeight / 2);
//    }

    public int getxOffset() {
        return xOffset;
    }

    public int getyOffset() {
        return yOffset;
    }

    public int getX(int screenWidth, int screenHeight) {
        return this.xOffset + screenWidth/2;
    }

    public int getY(int screenWidth, int screenHeight) {
        return this.yOffset + screenHeight/2;
    }

    public void setX(int x, int screenWidth, int screenHeight) {
        this.xOffset = x - screenWidth / 2;
    }

    public void setY(int y, int screenWidth, int screenHeight) {
        this.yOffset = y - screenHeight / 2;
    }

    public void register() {
        HudRenderCallback.EVENT.register((guiGraphics, deltaTracker) -> render(guiGraphics, deltaTracker, Minecraft.getInstance()));
    }

    private void render(GuiGraphics guiGraphics, DeltaTracker deltaTracker, Minecraft minecraft) {
        minecraft.getProfiler().push(TimeR.MOD_ID + "$" + this.id);

        guiGraphics.drawStringWithBackdrop(
                minecraft.font,
                Component.literal(this.textSupplier.get()),
                this.getX(guiGraphics.guiWidth(), guiGraphics.guiHeight()),
                this.getY(guiGraphics.guiWidth(), guiGraphics.guiHeight()),
                minecraft.font.width(this.textSupplier.get()),
                -1);

        minecraft.getProfiler().pop();
    }
}
