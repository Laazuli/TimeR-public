package me.laazuli.timer.gui.widget;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.network.chat.Component;

public class DraggableStringWidget extends StringWidget {
    private double xOffsetMouseClicked;
    private double yOffsetMouseClicked;

    public DraggableStringWidget(Component message, Font font) {
        this(0, 0, font.width(message.getVisualOrderText()), font.lineHeight, message, font);
    }

    public DraggableStringWidget(int width, int height, Component message, Font font) {
        this(0, 0, width, height, message, font);
    }

    public DraggableStringWidget(int x, int y, int width, int height, Component message, Font font) {
        super(x, y, width, height, message, font);
        this.active = true;
    }

    @Override
    public void onClick(double mouseX, double mouseY) {
        xOffsetMouseClicked = mouseX - this.getX();
        yOffsetMouseClicked = mouseY - this.getY();
        System.out.println(xOffsetMouseClicked + " " + yOffsetMouseClicked);
    }

    @Override
    protected void onDrag(double mouseX, double mouseY, double dragX, double dragY) {
//        System.out.println("on drag");
        this.setPosition((int) (mouseX - xOffsetMouseClicked), (int) (mouseY - yOffsetMouseClicked));
    }
}
