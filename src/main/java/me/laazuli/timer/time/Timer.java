package me.laazuli.timer.time;

public interface Timer {
    long getMillis();

    void set(long milliseconds);

    void run();

    void pause();

    default void reset() {
        set(0);
    }

    boolean isRunning();
}
