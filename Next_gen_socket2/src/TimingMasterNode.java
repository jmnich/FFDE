import FFDENetwork.FFDEEvent;
import FFDENetwork.FFDEObserver;
import FFDENetwork.FFDEServer;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by JM on 29.05.2016.
 */
public class TimingMasterNode implements FFDEObserver, Runnable{

    FFDEServer ffdeServer;

    public TimingMasterNode() {
        ffdeServer = new FFDEServer("masterNode1", 666, this);
        ffdeServer.takeControl("slaveNode1");

        Thread timingMasterThread = new Thread(this);
        timingMasterThread.setName("timingMaster");
        timingMasterThread.start();
    }

    @Override
    public void notifyFFDE(FFDEEvent event) {
        switch (event.getIdentifier()) {
            case "srx_slaveNode1":
                long nanoTime = System.nanoTime();
                long slaveNanoTime = Long.parseLong(event.getMessage().get(0));
                long overallTimeOfTranssmision = nanoTime - slaveNanoTime;
                double asMills = overallTimeOfTranssmision / 1000000.0;

                System.out.println("Time taken =   " + Double.toString(asMills) + "  [ms]");
                break;

            default:
                System.out.println("Master got:   incorrect message");
                break;
        }
    }

    @Override
    public void run() {
        ffdeServer.waitUntilNetworkIsReady();
    }
}
