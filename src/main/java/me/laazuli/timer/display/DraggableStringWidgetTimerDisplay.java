package me.laazuli.timer.display;

import me.laazuli.timer.time.Timer;
import me.laazuli.timer.gui.widget.DraggableStringWidget;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

public class DraggableStringWidgetTimerDisplay extends StringWidgetTimerDisplay {
    public DraggableStringWidgetTimerDisplay(Timer timer) {
        super(timer);
    }

    @Override
    public void initialize() {
        this.widget = new DraggableStringWidget(Component.literal("IF YOU SEE THIS, PLEASE CONTACT THE DEVELOPER!!"), Minecraft.getInstance().font);
    }
}
