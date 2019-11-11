package elements;

import common.Request;
import generators.TimeGenerator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Channel extends Thread {

    private Request processedRequest;

    private Double intensity;

    private boolean activated = true;

    private Queue queue;

    public List<Request> list = new ArrayList<Request>();

    public Channel(Queue queue) {
        super("Channel");
        this.queue = queue;
    }

    @Override
    public void run() {
        while (activated) {
            try {
                processedRequest = queue.getRequest();
                if (processedRequest.isWasInLine()) {
                    Thread.sleep(TimeGenerator.getSleepTime(2 * intensity, 100));
                } else {
                    Thread.sleep(TimeGenerator.getSleepTime(intensity, 100));
                }
                processedRequest.setDeadTime(new Date());
                list.add(processedRequest);
                processedRequest = null;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isBusy() {
        return processedRequest != null;
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
