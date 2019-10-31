package elements;

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
            Thread.sleep();
        }
    }


}
