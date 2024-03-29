package elements;

import common.Request;
import generators.TimeGenerator;

public class Source extends Thread {

    private Double intensity;

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
                Thread.sleep(TimeGenerator.getSleepTime(intensity,100));
                Request request = new Request();
                queue.addRequest(request);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void off() {
        activated = false;
    }

    public void setIntensity(Double intensity) {
        this.intensity = intensity;
    }

    public Double getIntensity() {
        return intensity;
    }
}
