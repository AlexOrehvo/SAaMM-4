package generators;

public class TimeGenerator {

    public static Long getSleepTime(Double intensity, int baseTime) {
        double temp = baseTime * intensity * Math.pow(Math.E, -1 * intensity * Math.random());
        return Math.round(temp);
    }
}
