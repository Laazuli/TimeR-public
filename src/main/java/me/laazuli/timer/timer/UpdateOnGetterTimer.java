package me.laazuli.timer.timer;

public class UpdateOnGetterTimer implements SimpleTimer {
    private boolean running;
    private long referencePoint;
    private long currentMilliseconds;

    public long getCurrentMilliseconds() {
        if (running) update();
        return currentMilliseconds;
    }

    private void update() {
        long now = System.currentTimeMillis();
        currentMilliseconds += now - referencePoint;
        referencePoint = now;

    }

    @Override
    public void run() {
        if (running) return;

        referencePoint = System.currentTimeMillis();
        running = true;
    }

    @Override
    public void pause() {
        if (!running) return;

        running = false;
        update();
        referencePoint = 0; // so errors would immediately be visible
    }

    @Override
    public long getMillis() {
        return getCurrentMilliseconds();
    }

    @Override
    public void set(long milliseconds) {
        currentMilliseconds = milliseconds;
    }

    @Override
    public void reset() {
        pause();
        set(0);
    }

    @Override
    public boolean isRunning() {
        return running;
    }
}
