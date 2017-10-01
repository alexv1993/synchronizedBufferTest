/**
 * Created by Alex on 01.10.2017.
 */
public class SynchronizedBuffer implements Buffer {
    private int buffer = -1;
    private int occupiedBuffers = 0;

    public synchronized void set(int value) {
        String name = Thread.currentThread().getName();
        while (occupiedBuffers == 1) {
            try {
                System.err.println(name + " try to write");
                displayState("Buffer is full. " + name + " is waiting now");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        buffer = value;
        ++occupiedBuffers;

        displayState(name + " is writing " + buffer);
        notify();
    }

    public synchronized int get() {
        String name = Thread.currentThread().getName();

        while (occupiedBuffers == 0) {
            try {
                System.err.println(name + " is try to start reading.");
                displayState("Buffer is clear. " + name + " is waiting");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        --occupiedBuffers;
        displayState(name + " is reading " + buffer);
        notify();
        return buffer;
    }

    public void displayState(String operation) {
        StringBuffer outputLine = new StringBuffer(operation);
        outputLine.setLength(40);
        outputLine.append(buffer + "\t\t" + occupiedBuffers);
        System.err.println(outputLine);
        System.err.println();
    }


}
