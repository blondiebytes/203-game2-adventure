
package adventure;
import static adventure.TestFunctions.randomButton;
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

import static adventure.TestFunctions.testingIndividualComponents;
import static adventure.TestFunctions.verifyInvariantsHM;
import static adventure.TestFunctions.verifyInvariantsRM;
import javalib.funworld.World;

public class TestMeteorShower {
    
static int tests = 50;
    
    public static void main(String[] args) throws Exception {
        testingIndividualComponents();
        System.out.println("Individual Components DONE");
        MeteorShowerRM meteorShowerR = new MeteorShowerRM();
        for(int i = 0; i < tests; i++) {
            String key = randomButton();
            // We can do this because in our tests, we don't ever have the key to be 0 in our random button
            MeteorShowerRM nG = (MeteorShowerRM) meteorShowerR.onKeyEvent(key).onTick();
           verifyInvariantsRM(meteorShowerR, nG, key);
           meteorShowerR = nG;
        }
        System.out.println();
        System.out.println("testConstructorRM " + testConstructorRM + " times");
        System.out.println("testShootLaserRM " + testShootLaserRM + " times");
        System.out.println("testLaserMeteorRemovedRM " + testLaserMeteorRemovedRM + " times");
        System.out.println("testTriggerHyperSpeedMode " + testTriggerHyperSpeedMode + " times");
        System.out.println("testCollisionRegularMode " + testCollisionRegularMode + " times");
        System.out.println("testPowerUpHyperMode " + testPowerUpHyperMode + " times");
        System.out.println("testGameOverLives " + testGameOverLives + " times");
        System.out.println("ran RM tests ");
        System.out.println();
        
        
        MeteorShowerHM mH = new MeteorShowerHM();
        for(int i = 0; i < tests; i++) {
           String key = randomButton();
           World nG = (mH.onTick()).onKeyEvent(key);
           // If you miss too many = You go back to regular mode 
           // -> so break b/c invalid for our tests
           if (nG instanceof MeteorShowerRM) {
               System.out.println("testConstructorHM " + testConstructorHM + " times");
               System.out.println("testShootLaserHM " + testShootLaserHM + " times");
               System.out.println("testLaserMeteorRemovedHM " + testLaserMeteorRemovedHM + " times");
               System.out.println("testCollisionHyperMode " + testCollisionHyperMode + " times");
               System.out.println("ran HM tests");
               break;
           }
           MeteorShowerHM mG = (MeteorShowerHM) nG;
           verifyInvariantsHM(mH, mG, key);
           mH = mG;
         }
        System.out.println("testConstructorHM " + testConstructorHM + " times");
        System.out.println("testShootLaserHM " + testShootLaserHM + " times");
        System.out.println("testLaserMeteorRemovedHM " + testLaserMeteorRemovedHM + " times");
        System.out.println("testCollisionHyperMode " + testCollisionHyperMode + " times");
        System.out.println("ran HM tests");
        
        }
}
        
        
        
        
        
        
        
        
        
        
