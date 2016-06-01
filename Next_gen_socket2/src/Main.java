import FFDENetwork.FFDEKernel;
import FFDENetwork.FFDETimer;

/**
 * Created by JM on 26.03.2016.
 */
public class Main {
    public static void main(String args[]) {
        System.out.println("Hallo");

        // I. TEST OF SUBSCRIPTION SYSTEM

//        FFDEKernel kernel = new FFDEKernel(666);
//
//        PublishingNode publishingNode = new PublishingNode();
//        SubscribingNode subscribingNode = new SubscribingNode();
//
//        while(true) {
//            try {
//                Thread.sleep(50);
//            }
//            catch(Exception e) {
//                e.printStackTrace();
//            }
//
//            //System.out.println(kernel.getCurrentState());
//        }

        // II. TEST OF MASTER - SLAVE RELATION

//        FFDEKernel kernel = new FFDEKernel(666);
//
//        SlaveNode slaveNode = new SlaveNode();
//        MasterNode masterNode = new MasterNode();
//
//        while(true) {
//            System.out.println(kernel.getCurrentState());
//
//            try {
//                Thread.sleep(100);
//            }
//            catch (Exception e) {
//                e.printStackTrace();
//            }
//        }

        // III. TEST OF TIMING

        FFDEKernel kernel = new FFDEKernel(666);

        TimingMasterNode masterNode = new TimingMasterNode();
        TimingSlaveNode slaveNode = new TimingSlaveNode();

        while(true) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}