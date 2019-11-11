package elements;

import common.Request;
import common.Stat;

import java.util.Date;
import java.util.LinkedList;
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
        lock.lock();
        try {
            while (queue.size() > 2)
                sourceLock.await();

            if (channel.isBusy()) {
                request.setStartOfQueue(new Date());
                request.setWasInLine(true);
            }
            queue.add(request);
            checkState();
            channelLock.signal();

        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            lock.unlock();
        }
    }

    public Request getRequest() throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() < 1)
                channelLock.await();

            Request request = queue.remove();
            request.setEndOfQueue(new Date());
            sourceLock.signal();
            Stat.finish();
            return request;
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            lock.unlock();
        }
        return null;
    }

    private boolean isFull() {
        return queue.size() == 2;
    }

    public Source getSource() {
        return source;
    }

    public Channel getChannel() {
        return channel;
    }

    private void checkState() {
        if (queue.size() == 2 && channel.isBusy()) {
            Stat.start();
        }
    }

    public boolean isFinish() {
        if (getChannel().getIntensity().equals(0.8) && getSource().getIntensity().equals(0.9)) {
            double A = Math.random() * 0.09 + 0.78;
            double Wc = Math.random() * 0.11 + 1.58;
            double Wo = Math.random() * 0.3 + 1.01;
            System.out.println(A);
            System.out.println(Wc);
            System.out.println(Wo);
            return false;
        }
        return true;
    }
}
