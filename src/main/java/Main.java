import common.Request;
import elements.Channel;
import elements.Queue;
import elements.Source;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Queue queue = new Queue();
        try {
            Source source = queue.getSource();
            Channel channel = queue.getChannel();
            System.out.print("Source intensity: ");
            source.setIntensity(in.nextDouble());
            System.out.print("Channel intensity");
            channel.setIntensity(in.nextDouble());
            source.start();
            channel.start();
            Date st = new Date();
            Thread.sleep(10000);
            source.off();
            channel.off();
            source.join();
            channel.join();
            Date fn = new Date();
            long workTime = fn.getTime() - st.getTime();
            double unitTimeAmount = (float) workTime / 100;
            System.out.println((float)channel.list.size() / unitTimeAmount);
            System.out.println(showWc(channel.list));
            System.out.println(showWo(channel.list));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static float showWc(List<Request> requests) {
        long allTime = 0;
        for (Request request : requests) {
            allTime += request.getDeadTime().getTime() - request.getBornTime().getTime();
        }
        return (float)allTime / requests.size() / 100;
    }

    private static float showWo(List<Request> requests) {
        long allTime = 0;
        long count = 0;
        for (Request request : requests) {
            if (request.isWasInLine()) {
                allTime += request.getEndOfQueue().getTime() - request.getStartOfQueue().getTime();
                count++;
            }
        }
        return (float)allTime / count / 100;
    }
}
