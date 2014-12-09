package adventure;

import static adventure.TestFunctions.testCollisionHyperMode;
import static adventure.TestFunctions.testCollisionRegularMode;
import static adventure.TestFunctions.testConstructorHM;
import static adventure.TestFunctions.testConstructorRM;
import static adventure.TestFunctions.testGameOverLives;
import static adventure.TestFunctions.testLaserMeteorRemovedHM;
import static adventure.TestFunctions.testLaserMeteorRemovedRM;
import static adventure.TestFunctions.testPowerUpHyperMode;
import static adventure.TestFunctions.testShootLaserHM;
import static adventure.TestFunctions.testShootLaserRM;
import static adventure.TestFunctions.testTriggerHyperSpeedMode;
import static adventure.TestFunctions.verifyInvarientsHM;
import static adventure.TestFunctions.verifyInvarientsRM;

public class RunMeteorShower {
    /// TO DO:
    // Make AI components not overlap each other in creation
    // Change name of browser;
    
    
    public static void main(String[] args) throws Exception {
        MeteorShowerRM meteorShowerR = new MeteorShowerRM();
        meteorShowerR.bigBang();
//        MeteorShowerHM meteorShower = new MeteorShowerHM();
//       meteorShower.bigBang();

        // Testing Invidually;
//        while (true) {
//            String key = meteorShowerR.key;
//            if (!key.equals(0)) {
//                MeteorShowerRM nG = (MeteorShowerRM) meteorShowerR.onKeyEvent(key).onTick();
//                verifyInvarientsRM(meteorShowerR, nG, key);
//                meteorShowerR = nG;
//            } else {
//                System.out.println();
//                System.out.println("testConstructorRM " + testConstructorRM + " times");
//                System.out.println("testShootLaserRM " + testShootLaserRM + " times");
//                System.out.println("testLaserMeteorRemovedRM " + testLaserMeteorRemovedRM + " times");
//                System.out.println("testTriggerHyperSpeedMode " + testTriggerHyperSpeedMode + " times");
//                System.out.println("testCollisionRegularMode " + testCollisionRegularMode + " times");
//                System.out.println("testPowerUpHyperMode " + testPowerUpHyperMode + " times");
//                System.out.println("testGameOverLives " + testGameOverLives + " times");
//                System.out.println("ran RM tests ");
//                System.out.println();
//                System.out.println();
//                break;
//            }
//        }
//
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
