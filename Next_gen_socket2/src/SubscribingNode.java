import FFDENetwork.FFDEEvent;
import FFDENetwork.FFDEObserver;
import FFDENetwork.FFDEServer;

/**
 * Created by JM on 23.05.2016.
 */
public class SubscribingNode implements FFDEObserver {

    private FFDEServer ffdeServer;

    public SubscribingNode() {
        ffdeServer = new FFDEServer("subscriber1", 666, this);

        ffdeServer.subscribe("publisher1", "a");
        ffdeServer.subscribe("publisher1", "b");
    }

    @Override
    public void notifyFFDE(FFDEEvent event) {
        String identifier = event.getIdentifier();

        switch(identifier) {
            case "sub_publisher1.a":
                System.out.println("Subscriber; ch.A   |   " + event.getMessage().toString());
                break;

            case "sub_publisher1.b":
                System.out.println("Subscriber; ch.B   |   " + event.getMessage().toString());
                break;

            default:
                System.out.println("Subscriber; unknown message   |   " + event.getMessage().toString());
                break;
        }
    }
}
