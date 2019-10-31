package elements;

import common.Request;
import generators.TimeGenerator;

public class Source extends Thread {

    private double intensity = 0.8;

    private Queue queue;

    private boolean activated = true;

    public Source(Queue queue) {
        super("Source");
        this.queue = queue;
    }

    @Override
    public void run() {
        while (activated) {
            try {
                Thread.sleep(TimeGenerator.getSleepTime(0.9, 100));
                Request request = new Request();
                queue.addRequest(request);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
