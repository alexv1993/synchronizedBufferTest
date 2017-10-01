/**
 * Created by Alex on 30.09.2017.
 */
public class SharedBufferTest {
    public static void main(String[] args) {
//        Buffer sharedLocation = new UnsynchronizedBuffer();
        SynchronizedBuffer sharedLocation = new SynchronizedBuffer();
        StringBuffer columnHeaders = new StringBuffer("Operation");
        columnHeaders.setLength(40);
        columnHeaders.append("The value of buffer\t\tIndications of work");
        System.err.println(columnHeaders);
        System.err.println();
        sharedLocation.displayState("The first state");


        Producer producer = new Producer(sharedLocation);
        Consumer consumer = new Consumer(sharedLocation);

        producer.start();
        consumer.start();
    }
}
