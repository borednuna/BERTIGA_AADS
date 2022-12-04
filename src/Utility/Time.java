package Utility;

import java.util.Timer;
import java.util.TimerTask;

public class Time {
    private int currentTime;
    private int second;

    public Time() {
        currentTime = 0;
        second = 0;
    }

    public void start() {
        Timer t = new Timer();
        t.scheduleAtFixedRate(
            new TimerTask() {
                public void run() {
                    ++currentTime;
                    if (currentTime % 100 == 0) {
                        second = currentTime / 100;
                    }
                }
            }, 0, 10);
    }

    public int getSecond() {
        return second;
    }
}
