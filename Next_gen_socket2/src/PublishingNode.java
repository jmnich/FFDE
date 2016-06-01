import FFDENetwork.FFDEEvent;
import FFDENetwork.FFDEObserver;
import FFDENetwork.FFDEServer;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by JM on 23.05.2016.
 */
public class PublishingNode implements FFDEObserver, Runnable{

    private FFDEServer ffdeServer;

    public PublishingNode() {
        ffdeServer = new FFDEServer("publisher1", 666, this);

        ffdeServer.publish("a");
        ffdeServer.publish("b");
        ffdeServer.publish("c");

        Thread th = new Thread(this);
        th.start();
    }


    @Override
    public void notifyFFDE(FFDEEvent event) {

    }

    @Override
    public void run() {
        Random rand = new Random();

        while(!ffdeServer.getState().equals("frozen")) {
            System.out.println("Pub_server state is    " + ffdeServer.getState());
            try {
                Thread.sleep(50);
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }

        while(true) {
            try {
                Thread.sleep(5);
            }
            catch(Exception e) {
                e.printStackTrace();
            }

            String m1 = Integer.toString(rand.nextInt());
            String m2 = Integer.toString(rand.nextInt());
            String m3 = Integer.toString(rand.nextInt());

            ffdeServer.updateDataSubChannel("a", Arrays.asList(m1));
            ffdeServer.updateDataSubChannel("b", Arrays.asList(m2, m3));

            //System.out.println("Publisher; ch. A  |  " + m1);
            //System.out.println("Publisher; ch. B  |  " + m2 + " ; " + m3);
        }
    }
}
