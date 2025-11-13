package me.laazuli.timer.gui.widget;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.network.chat.Component;

import java.util.function.Supplier;

public class DraggableStringWidget extends StringWidget {
    private final Supplier<Integer> screenWidth, screenHeight;
    private double xOffsetMouseClicked;
    private double yOffsetMouseClicked;

    public DraggableStringWidget(Component message, Font font, Supplier<Integer> screenWidth, Supplier<Integer> screenHeight) {
        this(0, 0, font.width(message.getVisualOrderText()), font.lineHeight, message, font, screenWidth, screenHeight);
    }

    public DraggableStringWidget(int width, int height, Component message, Font font, Supplier<Integer> screenWidth, Supplier<Integer> screenHeight) {
        this(0, 0, width, height, message, font, screenWidth, screenHeight);
    }

    public DraggableStringWidget(int x, int y, int width, int height, Component message, Font font, Supplier<Integer> screenWidth, Supplier<Integer> screenHeight) {
        super(x, y, width, height, message, font);
        this.active = true;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
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
        int newX = (int) (mouseX - xOffsetMouseClicked);
        int newY = (int) (mouseY - yOffsetMouseClicked);
        this.setPosition(Math.clamp(newX, 0, this.screenWidth.get() - this.width), Math.clamp(newY, 0, this.screenHeight.get() - this.height));
    }
}
