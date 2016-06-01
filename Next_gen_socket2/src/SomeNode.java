import FFDENetwork.*;

/**
 * Created by JM on 15.05.2016.
 */
public class SomeNode implements FFDEObserver {

    FFDEServer server;

    public SomeNode() {
        server = new FFDEServer("bagniak", 666, this);

        server.publish("strumien_guwna");
    }

    @Override
    public void notifyFFDE(FFDEEvent event) {

    }

    public String getState() {
        return server.getState();
    }
}
