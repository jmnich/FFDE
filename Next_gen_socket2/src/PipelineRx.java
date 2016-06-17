import FFDENetwork.FFDEEvent;
import FFDENetwork.FFDEObserver;
import FFDENetwork.FFDEServer;

/**
 * Created by JM on 17.06.2016.
 */
public class PipelineRx implements FFDEObserver {

    FFDEServer ffdeServer;

    public PipelineRx() {
        ffdeServer = new FFDEServer("rx", 666, this);
    }

    @Override
    public void notifyFFDE(FFDEEvent event) {
        switch(event.getIdentifier()) {
            case "pipe_tx":
                System.out.println("Rx got:   " + event.getMessage().toString());
                break;

            default:
                System.out.println("Incorrect message");
                break;
        }
    }
}
