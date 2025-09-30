package me.laazuli.timer;

import me.laazuli.timer.time.SystemTimeTimer;
import me.laazuli.timer.time.Timer;

public class Test {
    public static void main(String[] args) {
        Timer timer = new SystemTimeTimer();
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

    public static void print(Timer timer) {
        for (int i = 0; i < 50; i++) {
            System.out.println(/*"[" + new Date().getTime() + "] " + */timer.getMillis());
        }
    }
}
