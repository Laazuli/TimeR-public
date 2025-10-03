package me.laazuli.timer.command;

import com.mojang.brigadier.context.CommandContext;
import me.laazuli.timer.time.Timer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.network.chat.Component;

public class TimerCommand {

    private final Timer timer;

    public TimerCommand(Timer timer) {
        this.timer = timer;
    }

    public void register() {
        ClientCommandRegistrationCallback.EVENT
                .register((dispatcher, registryAccess) -> {
                    dispatcher.register(ClientCommandManager.literal("timer")
                            .executes(this::printHelp)
                            .then(
                                    ClientCommandManager.literal("start")
                                            .executes(this::start)
                            )
                            .then(
                                    ClientCommandManager.literal("pause")
                                            .executes(this::pause)
                            )
                            .then(
                                    ClientCommandManager.literal("reset")
                                            .executes(this::reset)
                            )
                    );
                });
    }

    private int printHelp(CommandContext<FabricClientCommandSource> context) {
        context.getSource().sendFeedback(Component.literal("/timer"));
        context.getSource().sendFeedback(Component.literal("    start - Starts the timer"));
        context.getSource().sendFeedback(Component.literal("    pause - Pauses the timer"));
        context.getSource().sendFeedback(Component.literal("    reset - Resets and pauses the timer"));
        return 1;
        // AMBITIOUS TODO: Make every subcommand require a description so this can be generated automatically
    }

    private int start(CommandContext<FabricClientCommandSource> context) {
        if (timer.isRunning()) {
            context.getSource().sendFeedback(Component.literal("Timer already running!"));
            return 0;
        }
        timer.run();
        context.getSource().sendFeedback(Component.literal("Timer started"));
        return 1;
    }

    private int pause(CommandContext<FabricClientCommandSource> context) {
        if (!timer.isRunning()) {
            context.getSource().sendFeedback(Component.literal("Timer already paused!"));
            return 0;
        }
        timer.pause();
        context.getSource().sendFeedback(Component.literal("Timer paused"));
        return 1;
    }

    private int reset(CommandContext<FabricClientCommandSource> context) {
        timer.pause();
        timer.reset();
        context.getSource().sendFeedback(Component.literal("Timer reset!"));
        return 1;
    }
}
