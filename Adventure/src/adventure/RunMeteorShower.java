package adventure;

public class RunMeteorShower {
    /// TO DO:
    // Make AI components not overlap each other in creation
    // Change name of browser;
    
    
    public static void main(String[] args) throws Exception {
        if ( false ) {
        MeteorShowerRM meteorShowerR = new MeteorShowerRM();
        meteorShowerR.bigBang();
        } else {
        MeteorShowerHM meteorShower = new MeteorShowerHM();
       meteorShower.bigBang();
        }


//        while (true) {
//            if (meteorShower instanceof MeteorShowerHM) {
//                String key = meteorShower.key;
//                MeteorShowerHM nG = (MeteorShowerHM) meteorShowerR.onKeyEvent(key).onTick();
//                verifyInvarientsHM(meteorShower, nG, key);
//                meteorShower = nG;
//            } else {
//                System.out.println("testConstructorHM " + testConstructorHM + " times");
//                System.out.println("testShootLaserHM " + testShootLaserHM + " times");
//                System.out.println("testLaserMeteorRemovedHM " + testLaserMeteorRemovedHM + " times");
//                System.out.println("testCollisionHyperMode " + testCollisionHyperMode + " times");
//                System.out.println("ran HM tests");
//
//                break;
//            }
//
//        }
    }
}
