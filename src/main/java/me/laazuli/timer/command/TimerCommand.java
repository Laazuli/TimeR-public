package me.laazuli.timer.command;

import com.mojang.brigadier.arguments.IntegerArgumentType;
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
                                    ClientCommandManager.literal("set")
                                            .then(ClientCommandManager.argument("milliseconds", IntegerArgumentType.integer(0))
                                                    .executes(this::set)
                                            )
                            ).then(
                                    ClientCommandManager.literal("add")
                                            .then(ClientCommandManager.argument("milliseconds", IntegerArgumentType.integer(0))
                                                    .executes(this::add)
                                            )
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
        context.getSource().sendFeedback(Component.literal("    set   - Sets the timer to a given value"));
        context.getSource().sendFeedback(Component.literal("    add   - Adds a given amount to the timer"));
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

    private int set(CommandContext<FabricClientCommandSource> context) {
        int milliseconds = IntegerArgumentType.getInteger(context, "milliseconds");
        timer.set(milliseconds);
        context.getSource().sendFeedback(Component.literal("Timer set to " + milliseconds + "ms!"));
        return 1;
    }

    private int add(CommandContext<FabricClientCommandSource> context) {
        int milliseconds = IntegerArgumentType.getInteger(context, "milliseconds");
        timer.set(timer.getMillis() + milliseconds);
        context.getSource().sendFeedback(Component.literal("Timer set to " + milliseconds + "ms!"));
        return 1;
    }

    private int reset(CommandContext<FabricClientCommandSource> context) {
        timer.reset();
        context.getSource().sendFeedback(Component.literal("Timer reset!"));
        return 1;
    }
}
