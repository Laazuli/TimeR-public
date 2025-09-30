package me.laazuli.timer.time;

import java.util.Date;

public class SystemTimeTimer implements Timer {
    private boolean running = false;
    private long lastStartTime;
    private long passedTimeBeforeLastStart = 0;

    @Override
    public long getMillis() {
        return running ? timeSinceLastStart() + passedTimeBeforeLastStart : passedTimeBeforeLastStart;
    }

    @Override
    public void set(long milliseconds) {
        if (milliseconds < 0) return;

        passedTimeBeforeLastStart = milliseconds;
        lastStartTime = currentDateTime();
    }

    @Override
    public void run() {
        if (running) return;
        running = true;
        this.lastStartTime = currentDateTime();
    }

    @Override
    public void pause() {
        if (!running) return;
        running = false;
        passedTimeBeforeLastStart += timeSinceLastStart();
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    private int timeSinceLastStart() {
        return (int) (currentDateTime() - this.lastStartTime);
    }

    private long currentDateTime() {
        return new Date().getTime();
    }
}
