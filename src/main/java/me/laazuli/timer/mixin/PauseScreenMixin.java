package me.laazuli.timer.mixin;

import me.laazuli.timer.TimeR;
import me.laazuli.timer.screen.TimerSettingsScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.SpriteIconButton;
import net.minecraft.client.gui.screens.PauseScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PauseScreen.class)
public abstract class PauseScreenMixin extends Screen {
    protected PauseScreenMixin(Component title) {
        super(title);
    }

    @Inject(method = "init", at = @At("TAIL"))
    private void addTimerSettingsButton(CallbackInfo info) {
        Button timerSettings = SpriteIconButton
                .builder(Component.literal("TimeR Settings"),button -> this.minecraft.setScreen(new TimerSettingsScreen((PauseScreen)(Object)this)), true)
                .width(20)
                .sprite(ResourceLocation.fromNamespaceAndPath(TimeR.MOD_ID, "icon/hourglass"), 15, 15)
                .build();
        timerSettings.setPosition(this.width - timerSettings.getWidth() - 5, 5);

        this.addRenderableWidget(timerSettings);

    }
}
