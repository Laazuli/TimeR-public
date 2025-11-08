package me.laazuli.timer.mixin;

import me.laazuli.timer.TimeR;
import me.laazuli.timer.time.Timer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.client.gui.components.DebugScreenOverlay;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class AddTimerRenderLayer {
    @Shadow @Final private LayeredDraw layers;

    @Shadow @Final private Minecraft minecraft;

    @Shadow @Final private DebugScreenOverlay debugOverlay;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void addTimerLayer(CallbackInfo ci) {
        LayeredDraw timerLayer = new LayeredDraw();

        timerLayer.add((guiGraphics, deltaTracker) -> {
            TimeR.DISPLAY.setMessage(Component.literal(Timer.formatTimer(TimeR.TIMER)));
            TimeR.DISPLAY.render(guiGraphics, -1, -1, deltaTracker.getRealtimeDeltaTicks());
        });

        this.layers.add(timerLayer, () -> !this.minecraft.options.hideGui && !this.debugOverlay.showDebugScreen() /* && !this.minecraft.player.isReducedDebugInfo() && !this.minecraft.options.reducedDebugInfo().get().booleanValue() */);
//        System.out.println("timer layer mixin");
    }
}
