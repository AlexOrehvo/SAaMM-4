package generators;

public class TimeGenerator {

    public static Long getSleepTime(Double intensity, int baseTime) {
        double value = Math.random();
        double temp = baseTime * (-1.0f / intensity)* Math.log(value);
        return Math.round(temp);
    }
}
