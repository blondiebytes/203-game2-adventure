
package adventure;
import adventure.Plane;
import static adventure.TestFunctions.randomButton;
//
//import static adventure.TestFunctions.testCollisionHyperMode;
//import static adventure.TestFunctions.testCollisionRegularMode;
//import static adventure.TestFunctions.testConstructor;
//import static adventure.TestFunctions.testGameOverLives;
//import static adventure.TestFunctions.testLaserColorsHyperMode;
//import static adventure.TestFunctions.testLaserColorsRegularMode;
//import static adventure.TestFunctions.testMeteorAppear;
//import static adventure.TestFunctions.testMeteorColor;
import static adventure.TestFunctions.testPlaneMoveRightAndLeftRM;
import static adventure.TestFunctions.testPlaneRotateHM;
//import static adventure.TestFunctions.testPowerUpHyperMode;
//import static adventure.TestFunctions.testShootLaser;
//import static adventure.TestFunctions.testStartUporRestartDown;
//import static adventure.TestFunctions.testTriggerHyperSpeedMode;
//

public class TestMeteorShower {
    
    static int tests = 99;
    
    public static void main(String[] args) throws Exception {
        // TESTING PLANE:
        Plane planeR = new Plane();
        for (int i = 0; i <= tests; i++) {
        String k = randomButton();
        Plane planeRegularReacted = planeR.react(k);
        testPlaneMoveRightAndLeftRM(planeR, planeRegularReacted, k);
        System.out.println("MOVE: " + k + " OLD: " + planeR.width + " NEW: " + planeRegularReacted.width);
        planeR = planeRegularReacted;
        }
        
       System.out.println("testPlaneMoveRightAndLeft success: " + testPlaneMoveRightAndLeftRM + " times");
        // 1 = hyperspeedmode
        Plane planeH = new Plane(1);
        for (int i = 0; i <= tests; i++) {
        String k = randomButton();
        Plane planeHyperReacted = planeH.react(k);
        testPlaneRotateHM(planeH, planeHyperReacted);
         System.out.println("MOVE: " + k + " OLD: " + planeH.width + " NEW: " + planeHyperReacted.width);
        planeH = planeHyperReacted;
        }
        
        System.out.println("testPlaneRotate: " + testPlaneRotateHM + " times");
        
        
        
//        while (!meteorShower.gameOver) {
//                  // Pick random Key
//                  // Flush out system
//                  // Update the game --> store in new Game variable
//                  // Verify the invariants
//                  // Have the old game equal the new Game
//            }
        
//             System.out.println("testConstructor success: " + testConstructor + " times");
//             System.out.println("testStartUporRestartDown success: " + testStartUporRestartDown + " times");
//             System.out.println("testShootLaser success: " + testShootLaser + " times");
//             System.out.println("testLaserColorsRegularMode success: " + testLaserColorsRegularMode + " times");
//             System.out.println("testLaserColorsHyperMode success: " + testLaserColorsHyperMode + " times");
//             System.out.println("testMeteorAppear success: " + testMeteorAppear + " times");
//             System.out.println("testMeteorColor success: " + testMeteorColor + " times");
//             System.out.println("testCollisionRegularMode success: " + testCollisionRegularMode + " times");
//             System.out.println("testTriggerHyperMode success: " + testTriggerHyperSpeedMode + " times");
//             System.out.println("testCollisionHyperMode success: " + testCollisionHyperMode + " times");
//             System.out.println("testGameOverLives success: " + testGameOverLives + " times");
//             System.out.println("testTriggerHyperMode success: " + testPowerUpHyperMode + " times");
    }
}
