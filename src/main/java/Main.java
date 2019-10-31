import elements.Source;
import generators.TimeGenerator;

public class Main {

    public static void main(String[] args) {
        /*Source source = new Source();
        source.start();*/

        System.out.println(TimeGenerator.getSleepTime(0.9, 100));
    }
}
