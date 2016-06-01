import FFDENetwork.FFDEEvent;
import FFDENetwork.FFDEObserver;
import FFDENetwork.FFDEServer;

import java.util.Arrays;
import java.util.Random;

import static java.lang.System.nanoTime;

/**
 * Created by JM on 29.05.2016.
 */
public class TimingSlaveNode implements FFDEObserver, Runnable {

    private FFDEServer ffdeServer;

    public TimingSlaveNode() {
        ffdeServer = new FFDEServer("slaveNode1", 666, this);

        Thread timingSlaveThread = new Thread(this);
        timingSlaveThread.setName("timingSlave");
        timingSlaveThread.start();
    }

    @Override
    public void notifyFFDE(FFDEEvent event) {
        String identifier = event.getIdentifier();

        switch(identifier) {
            case "crx":
                System.out.println("Slave got:   " + event.getMessage().toString());
                break;

            default:
                System.out.println("Slave got:   unknown message");
                break;
        }
    }

    @Override
    public void run() {
        ffdeServer.waitUntilNetworkIsReady();

        while(true) {
            long nanoTime = System.nanoTime();

            ffdeServer.sendToMaster(Arrays.asList(Long.toString(nanoTime)));

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}