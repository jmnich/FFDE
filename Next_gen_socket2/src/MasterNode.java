import FFDENetwork.FFDEEvent;
import FFDENetwork.FFDEObserver;
import FFDENetwork.FFDEServer;
import com.sun.org.apache.xml.internal.security.utils.resolver.implementations.ResolverAnonymous;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by JM on 26.05.2016.
 */
public class MasterNode implements FFDEObserver, Runnable{

    FFDEServer ffdeServer;

    public MasterNode() {
        ffdeServer = new FFDEServer("masterNode1", 666, this);
        ffdeServer.takeControl("slaveNode1");

        new Thread(this).start();
    }

    @Override
    public void notifyFFDE(FFDEEvent event) {
        switch (event.getIdentifier()) {
            case "srx_slaveNode1":
                System.out.println("Master got:   " + event.getMessage().toString());
                break;

            default:
                System.out.println("Master got:   incorrect message");
                break;
        }
    }

    @Override
    public void run() {
        ffdeServer.waitUntilNetworkIsReady();

        Random rand = new Random();

        while(true) {
            System.out.println("Master is here");

            ffdeServer.sendToSlave("slaveNode1", Arrays.asList(Integer.toString(rand.nextInt())));

            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
