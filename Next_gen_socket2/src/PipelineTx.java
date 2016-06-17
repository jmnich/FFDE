import FFDENetwork.FFDEEvent;
import FFDENetwork.FFDEObserver;
import FFDENetwork.FFDEServer;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by JM on 17.06.2016.
 */
public class PipelineTx implements FFDEObserver, Runnable {

    FFDEServer ffdeServer;

    public PipelineTx() {
        ffdeServer = new FFDEServer("tx", 666, this);
        ffdeServer.openPipeline("rx");

        new Thread(this).start();
    }

    @Override
    public void notifyFFDE(FFDEEvent event) {
        switch(event.getIdentifier()) {
            case "pipe_rx":
                System.out.println("Tx got: " + event.getMessage().toString());
                break;

            default:
                System.out.println("Incorrect message");
                break;
        }
    }

    @Override
    public void run() {
        ffdeServer.waitUntilNetworkIsReady();

        Random rand = new Random();

        while(true) {
            System.out.println("Tx is here");

            ffdeServer.sendThroughPipeline("rx", Arrays.asList(Integer.toString(rand.nextInt())));

            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
