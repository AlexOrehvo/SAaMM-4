package elements;

public class Channel extends Thread {

    private double intensity = 0.9;

    private Queue queue;

    public Channel(Queue queue) {
        super("Channel");
        this.queue = queue;
    }

    @Override
    public void run() {

    }
}
