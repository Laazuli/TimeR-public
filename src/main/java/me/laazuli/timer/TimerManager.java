package me.laazuli.timer;

import me.laazuli.timer.timer.SimpleTimer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.minecraft.client.Minecraft;

public class TimerManager {
    private final SimpleTimer timer;
    private boolean wasRunningBefore;

    public TimerManager(SimpleTimer timer) {
        this.timer = timer;
    }

    public void register() {
        ClientTickEvents.START_CLIENT_TICK.register(this::updateTimerState);

        ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> this.pauseAndReset());
    }

    private void updateTimerState(Minecraft client) {
        if (client.isPaused() && this.timer.isRunning()) {
            this.wasRunningBefore = this.timer.isRunning();
            this.timer.pause();
        }
        if (!client.isPaused() && this.wasRunningBefore) {
            this.timer.run();
        }
    }

    private void pauseAndReset() {
        this.timer.pause();
        this.wasRunningBefore = false;
        this.timer.reset();
    }
}
