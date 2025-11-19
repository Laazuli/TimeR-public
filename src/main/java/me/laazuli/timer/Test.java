package me.laazuli.timer;

import me.laazuli.timer.timer.SimpleTimer;
import me.laazuli.timer.timer.UpdateOnGetterTimer;

public class Test {
    public static void main(String[] args) {
        SimpleTimer timer = new UpdateOnGetterTimer();
        System.out.println("Timer created");

        timer.run();
        System.out.println("Timer start");

        print(timer);

        timer.pause();
        System.out.println("Timer paused");

        print(timer);

        timer.run();
        System.out.println("Timer run");

        print(timer);

        timer.reset();
        System.out.println("Timer reset");

        print(timer);
    }

    public static void print(SimpleTimer timer) {
        for (int i = 0; i < 50; i++) {
            System.out.println(/*"[" + new Date().getTime() + "] " + */timer.getMillis());
        }
    }
}
