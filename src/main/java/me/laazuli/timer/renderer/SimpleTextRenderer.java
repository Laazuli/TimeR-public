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
    public int x;
    public int y;

    public SimpleTextRenderer(String namespace, String id, Supplier<String> textSupplier) {
        this.namespace = namespace;
        this.id = id;
        this.textSupplier = textSupplier;
    }

    public void register() {
        HudRenderCallback.EVENT.register((guiGraphics, deltaTracker) -> {
            render(guiGraphics, deltaTracker, Minecraft.getInstance());
        });
    }

    private void render(GuiGraphics guiGraphics, DeltaTracker deltaTracker, Minecraft minecraft) {
        minecraft.getProfiler().push(TimeR.MOD_ID + "$" + this.id);

        guiGraphics.drawStringWithBackdrop(minecraft.font, Component.literal(this.textSupplier.get()), this.x, this.y, minecraft.font.width(this.textSupplier.get()), -1);

        minecraft.getProfiler().pop();
    }

}
