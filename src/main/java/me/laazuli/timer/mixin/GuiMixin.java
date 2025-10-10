package me.laazuli.timer.mixin;

import me.laazuli.timer.TimeR;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.LayeredDraw;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class GuiMixin {
    @Shadow @Final private LayeredDraw layers;

    @Shadow @Final private Minecraft minecraft;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void addTimerLayer(CallbackInfo ci) {
        LayeredDraw timerLayer = new LayeredDraw();
        timerLayer.add(((guiGraphics, deltaTracker) -> TimeR.DISPLAY.render(guiGraphics, deltaTracker)));
        this.layers.add(timerLayer, () -> !this.minecraft.options.hideGui);
        System.out.println("timer layer mixin");
    }
}
