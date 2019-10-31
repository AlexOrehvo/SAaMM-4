package elements;

public class Queue {

    private final static int CAPACITY = 2;

    private Source source;

    private Channel channel;

    private int state = 0;

    public Queue() {
        this.source = new Source(this);
        this.channel = new Channel(this);
    }
}
