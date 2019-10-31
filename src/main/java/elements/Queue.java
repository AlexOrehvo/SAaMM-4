package elements;

import common.Request;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Queue {

    final Lock lock = new ReentrantLock();
    final Condition sourceLock  = lock.newCondition();
    final Condition channelLock = lock.newCondition();

    private java.util.Queue<Request> queue = new LinkedList<Request>();

    private Source source;

    private Channel channel;

    public Queue() {
        this.source = new Source(this);
        this.channel = new Channel(this);
    }

    public void addRequest(Request request) throws InterruptedException {
        if (isFull()) {
            sourceLock.await();
        } else {
            if (channel.isBusy()) {
                request.setWasInLine(true);
                queue.add(request);
            }
            channelLock.signal();
        }
    }

    public Request getRequest() throws InterruptedException {
        if (queue.isEmpty()) {
            channelLock.await();
        } else {
            Request req = queue.remove();
            sourceLock.signal();
            return req;
        }
        return null;
    }

    private boolean isFull() {
        return queue.size() == 2;
    }
}
