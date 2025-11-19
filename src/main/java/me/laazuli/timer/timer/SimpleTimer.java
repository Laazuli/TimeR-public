package me.laazuli.timer.timer;

public interface SimpleTimer {
    long getMillis();

    default long getSeconds() {
        return getMillis() / 1000;
    }

    default long getMinutes() {
        return getSeconds() / 60;
    }

    default long getHours() {
        return getMinutes() / 60;
    }

    void set(long milliseconds);

    void run();

    void pause();

    default void reset() {
        set(0);
    }

    boolean isRunning();

    static String formatTimer(SimpleTimer timer) {
        return String.format("%02d:%02d:%02d", timer.getHours(), timer.getMinutes()%60 , timer.getSeconds()%60);
    }
}
