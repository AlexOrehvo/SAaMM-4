package common;

import java.util.Date;

public class Stat {

    public static volatile long allTime = 0;

    private static volatile Date startTime;

    private static volatile boolean bol = false;

    public static void finish() {
        if (bol) {
            Date now = new Date();
            allTime = allTime + (now.getTime() - startTime.getTime());
            bol = false;
        }
    }

    public static void start() {
        startTime = new Date();
        bol = true;
    }
}
