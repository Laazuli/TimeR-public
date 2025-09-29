package me.laazuli.timer.time;

public interface Timer {
    int get();

    void set(int milliseconds);

    void run();

    void pause();

    default void reset() {
        set(0);
    }

    boolean isRunning();
}
