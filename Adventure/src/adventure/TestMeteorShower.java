
package adventure;

import static adventure.TestFunctions.testCollisionHyperMode;
import static adventure.TestFunctions.testCollisionRegularMode;
import static adventure.TestFunctions.testConstructor;
import static adventure.TestFunctions.testGameOverLives;
import static adventure.TestFunctions.testLaserColorsHyperMode;
import static adventure.TestFunctions.testLaserColorsRegularMode;
import static adventure.TestFunctions.testMeteorAppear;
import static adventure.TestFunctions.testMeteorColor;
import static adventure.TestFunctions.testPlaneMoveRightAndLeft;
import static adventure.TestFunctions.testShootLaser;
import static adventure.TestFunctions.testStartUporRestartDown;
import static adventure.TestFunctions.testTriggerHyperMode;


public class TestMeteorShower {
    
    public static void main(String[] args) throws Exception {
        
        MeteorShower meteorShower = new MeteorShower();
        
        while (!meteorShower.gameOver) {
                  // Pick random Key
                  // Flush out system
                  // Update the game --> store in new Game variable
                  // Verify the invariants
                  // Have the old game equal the new Game
            }
        
             System.out.println("testConstructor success: " + testConstructor + " times");
             System.out.println("testStartUporRestartDown success: " + testStartUporRestartDown + " times");
             System.out.println("testPlaneMoveRightAndLeft success: " + testPlaneMoveRightAndLeft + " times");
             System.out.println("testShootLaser success: " + testShootLaser + " times");
             System.out.println("testLaserColorsRegularMode success: " + testLaserColorsRegularMode + " times");
             System.out.println("testLaserColorsHyperMode success: " + testLaserColorsHyperMode + " times");
             System.out.println("testMeteorAppear success: " + testMeteorAppear + " times");
             System.out.println("testMeteorColor success: " + testMeteorColor + " times");
             System.out.println("testCollisionRegularMode success: " + testCollisionRegularMode + " times");
             System.out.println("testTriggerHyperMode success: " + testTriggerHyperMode + " times");
             System.out.println("testCollisionHyperMode success: " + testCollisionHyperMode + " times");
             System.out.println("testGameOverLives success: " + testGameOverLives + " times");
    }
}
