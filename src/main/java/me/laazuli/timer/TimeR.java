package me.laazuli.timer;

import com.mojang.blaze3d.platform.InputConstants;
import me.laazuli.timer.command.TimerCommand;
import me.laazuli.timer.gui.screen.TimerSettingsScreen;
import me.laazuli.timer.renderer.SimpleTextRenderer;
import me.laazuli.timer.timer.SimpleTimer;
import me.laazuli.timer.timer.UpdateOnGetterTimer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import org.lwjgl.glfw.GLFW;

public class TimeR implements ClientModInitializer {
    public static final String MOD_ID = "timer";

    public static final SimpleTimer TIMER = new UpdateOnGetterTimer();

    public static final TimerManager MANAGER = new TimerManager(TIMER);

    public static final SimpleTextRenderer RENDERER = new SimpleTextRenderer(MOD_ID, "timer", () -> SimpleTimer.formatTimer(TIMER), 0, 0);

    public static final TimerCommand TIMER_COMMAND = new TimerCommand(TIMER);

    public static final KeyMapping OPTIONS_SCREEN_KEYMAPPING = KeyBindingHelper.registerKeyBinding(new KeyMapping("key.timer.options_screen", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_RIGHT_SHIFT, "key.categories.misc"));


    @Override
    public void onInitializeClient() {
        MANAGER.register();

        RENDERER.register();

        TIMER_COMMAND.register();

        registerKeyMapping();
    }

    private void registerKeyMapping() {
        ClientTickEvents.START_WORLD_TICK.register(
                clientLevel -> {
                    Minecraft minecraft = Minecraft.getInstance();

                    if (minecraft.screen instanceof TimerSettingsScreen) return;

                    if (OPTIONS_SCREEN_KEYMAPPING.consumeClick()) {
                        minecraft.setScreen(new TimerSettingsScreen(minecraft.screen));
                    }
                }
        );
    }
}
