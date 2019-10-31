package elements;

import common.Request;

public class Channel extends Thread {

    private Request processedRequest;

    private double intensity = 0.9;

    private boolean isActive = true;

    private Queue queue;

    public Channel(Queue queue) {
        super("Channel");
        this.queue = queue;
    }

    @Override
    public void run() {
        while (isActive) {

        }
    }

    public boolean isBusy() {
        return processedRequest != null;
    }
}
