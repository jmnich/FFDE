import FFDENetwork.FFDEEvent;
import FFDENetwork.FFDEObserver;
import FFDENetwork.FFDEServer;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by JM on 26.05.2016.
 */
public class SlaveNode implements FFDEObserver, Runnable {

    private FFDEServer ffdeServer;

    public SlaveNode() {
        ffdeServer = new FFDEServer("slaveNode1", 666, this);

        new Thread(this).start();
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

        Random rand = new Random();

        while(true) {
            System.out.println("Slave is here");

            ffdeServer.sendToMaster(Arrays.asList(Integer.toString(rand.nextInt())));

            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
