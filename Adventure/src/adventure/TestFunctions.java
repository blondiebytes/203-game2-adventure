
package adventure;

import java.util.Random;


public class TestFunctions {
    
    // Possible Testing Functions ALL

    // ----------------------------------------------------------------
    // INITIAL CONDITIONS: 
    
    // Does the plane start at the top middle of the screen?
    // Are there no meteors on screen?
    // Does the player start with three lives?
    // Does the player start with a score of 0?
    // When the game starts, is the gameOver false?
    // Does the game start in Regular Mode?
        
    // ----------------------------------------------------------------
    // CONTROLS:
    
    // After pressing the UP button, does the game start?
    // After pressing the DOWN button, does the game restart?
    
    // In Regular Mode, after pressing the RIGHT arrow, does the plane move right?
    // If it is about to go off-screen, does it stay where it is?
    
    // In Regular Mode, after pressing the LEFT arrow, does the plane move left? 
    // If it is about to go off-screen, does it stay where it is?
    
    // In Regular Mode, if those buttons aren't is pressed, the plane shouldn't move. 
    
    // In Hyper-Speed Mode, after pressing the RIGHT arrow, does the plane turn right?
    // does the plane turn left after pressing the LEFT arrow? Does the plane 
    // stay the same if those buttons aren't pressed? If the plane is about to
    // go past the 180 degree line, does the rotation stop?

    
    // After pressing the SPACEBAR, does the plane shoot an laser?
    
    // When the player has a Hyper-Speed PowerUp, is HyperSpeed mode triggered
    // after pressing Enter?
    
    // REGUALR MODE: After pressing R, do the lasers turn red?
    
    // REGUALR MODE: After pressing B, do the lasers turn blue?
    
    // HYPERSPEED MODE: After pressing R or B, do the lasers stay the 
    // same color?
    
    // REGULAR MODE: Is a meteor created every other second?
    
    // HYPERSPEED MODE: Is a meteor created every half second?
    
    // Is the meteor either red or blue in REGULAR MODE? white in HYPER MODE?
    
    // ----------------------------------------------------------------
    // REGULAR MODE & SCORING:

    // When a laser and a meteor collide and they are the same color,
    // does the score increase? do the lives stay the same?
    
    // When a laser and a meteor collide and they are different colors,
    // does the score decrease? do the lives stay the same?
    
    // When a meteor passes the top of the screen, do you lose a life?
    // does the score stay the same?
    
    // ----------------------------------------------------------------
    // HYPERSPEED MODE & SCORING:
    
    // After shooting 20 meteors in a row correctly, 
    // is Hyper-Speed Mode triggered?
    
    // When a laser and meteor collide, does the score increase? 
    // do the lives stay the same?
    
    // When a meteor passes the top of the screen, do your lives and score
    // stay the same?
    
    // ----------------------------------------------------------------
    // GAME OVER:
   
    // When a player loses his or her last life, does the game end? 
    // If the player still has lives, is the game still going?
    
     
    // ================================================================

    // For all tests oG = old Game; nG = new Game; mS = Meteor Shower
    
    // ================================================================
    
     static int testConstructor = 0;
     static int testStartUporRestartDown = 0;
     static int testPlaneMoveRightAndLeft = 0;
     static int testPlaneRotate = 0;
     static int testShootLaser = 0;
     static int testLaserColorsRegularMode = 0;
     static int testLaserColorsHyperMode = 0;
     static int testMeteorAppear = 0;
     static int testMeteorColor = 0;
     static int testCollisionRegularMode = 0;
     static int testTriggerHyperSpeedMode = 0;
     static int testPowerUpHyperMode = 0;
     static int testCollisionHyperMode = 0;
     static int testGameOverLives = 0;
     static int tests = 5;
    
    
    // ----------------------------------------------------------------
    // INITIAL CONDITIONS: 
    
    // Does the plane start at the top middle of the screen?
    // Are there no meteors on screen?
    // Does the player start with three lives?
    // Does the player start with a score of 0?
    
    
    public static void testConstructor() throws Exception {
        MeteorShower mS = new MeteorShower();
        // Does the plane start at the top middle of the screen?
        if (mS.plane.height != 0 || mS.plane.width != Plane.middleOfScreenWidth){
            throw new Exception("The player does not start at the top middle of "
                    + "screen");
        }
        
        // Are there no meteors on screen?
        if (!mS.meteors.isEmpty()){
            throw new Exception("There is a meteor on-screen when the game "
                    + "starts");
        }
            
        // Does the player start with three lives?
        if (mS.lives.life!= 3) {
            throw new Exception("Starting lives doesn't equal 3");
        }
        
        // Does the player start with a score of 0?
        if (mS.score.score != 0) {
            throw new Exception("Starting score isn't 0");
        }
        
        // Does the game start?
        if (!mS.gameOver) {
            throw new Exception("The game is starting, but the game is over");
        }
        
        // Does the game start in Regular Mode
        if (mS.mode != MeteorShower.REGULARMODE) {
            throw new Exception("The game is starting in the wrong mode!");
        }
        
        testConstructor++;
    }
    
    
     // ----------------------------------------------------------------
    // CONTROLS:
    
    // After pressing the UP button, does the game start? 
    // After pressing the DOWN button, does the game restart?
    
    // In Regular Mode, after pressing the RIGHT arrow, does the plane move right?
    // If it is about to go off-screen, does it stay where it is?
    
    // In Regular Mode, after pressing the LEFT arrow, does the plane move left? 
    // If it is about to go off-screen, does it stay where it is?
    
    // In Regular Mode, if those buttons aren't is pressed, the plane shouldn't move. 
    
    // In Hyper-Speed Mode, after pressing the RIGHT arrow, does the plane turn right?
    // does the plane turn left after pressing the LEFT arrow? Does the plane 
    // stay the same if those buttons aren't pressed? If the plane is about to
    // go past the 180 degree line, does the rotation stop?
    
    // After pressing the SPACEBAR, does the plane shoot an arrow?
    
    // When the player has a Hyper-Speed PowerUp, is HyperSpeed mode triggered
    // after pressing Enter?
    
    // REGUALR MODE: After pressing R, do the lasers turn red?
    
    // REGUALR MODE: After pressing B, do the lasers turn blue?
    
    // HYPERSPEED MODE: After pressing R or B, do the lasers stay the 
    // same color?
    
    // Is a meteor created every other second?
    
    // Is the meteor either red or blue in REGULAR MODE? white in HYPER MODE?
    
    
    public static void testStartUpOrRestartDown() throws Exception{
       // Some kind of graphics where you pick a random button
   
        String rk = randomButton();
        
        // After pressing the UP button, does the game start?
        if (!shouldStart(rk) && rk.matches("up")){
                       throw new Exception("Our game isn't starting even"
                               + "though we are pressing the up arrow");
                         }
        if (shouldStart(rk) && !rk.matches("up")){
                     throw new Exception("Our game is starting even though"
                             + "we aren't pressing the up arrow");
        }
        
        // After pressing the DOWN button, does the game restart?
        if (!shouldRestart(rk) && rk.matches("down")) {
                       throw new Exception("Our game isn't starting even"
                               + "though we are pressing the up arrow");
                         }
        if (shouldRestart(rk) && !rk.matches("down")){
                     throw new Exception("Our game is starting even though"
                             + "we aren't pressing the up arrow"); 
                     
            }
        testStartUporRestartDown++;
    }
    
   public static boolean shouldStart(String rk) {
       return rk.matches("up");
   }
   
   public static boolean shouldRestart(String  rk) {
       return rk.matches("down");
   }
   
   public static void testPlaneMoveRightAndLeft(MeteorShower oG, MeteorShower nG) {
   // After pressing the RIGHT arrow, does the plane move right? If it is
   // about to go off-screen, does it stay where it is?
   // After pressing the LEFT arrow, does the plane move left? If it is
   // about to go off-screen, does it stay where it is?
   // If those buttons aren't is pressed, the plane shouldn't move. 
       // (or it shouldn't change direction)
       
       testPlaneMoveRightAndLeft++;
   }
   
   public static void testPlaneRotate(MeteorShower oG, MeteorShower nG) {
    // In Hyper-Speed Mode, after pressing the RIGHT arrow, does the plane turn right?
    // does the plane turn left after pressing the LEFT arrow? Does the plane 
    // stay the same if those buttons aren't pressed? If the plane is about to
    // go past the 180 degree line, does the rotation stop?
       testPlaneRotate++;
   }
   
   
   public static void testShootLaser(MeteorShower oG, MeteorShower nG) {
       // After pressing the SPACEBAR, does the plane shoot an arrow?
       testShootLaser++;
   }
   
   public static void testTriggerHyperSpeedMode(MeteorShower oG, MeteorShower nG) {
    // When the player has a Hyper-Speed PowerUp, is HyperSpeed mode triggered
    // after pressing Enter?
       testTriggerHyperSpeedMode++;
   }
   
   public static void testLaserColorsRegularMode(MeteorShower oG, MeteorShower nG) {
    // REGUALR MODE: After pressing R, do the lasers turn red?
    // REGUALR MODE: After pressing B, do the lasers turn blue?
       testLaserColorsRegularMode++;
   }
   
   public static void testLaserColorsHyperMode(MeteorShower oG, MeteorShower nG) {
    // HYPERSPEED MODE: After pressing R or B, do the lasers stay the 
    // same color?
       testLaserColorsHyperMode++;
   }
   
   public static void testMeteorAppear(MeteorShower oG, MeteorShower nG) {
     // REGULAR MODE: Is a meteor created every other second?
    // HYPERSPEED MODE: Is a meteor created every half second?
       testMeteorAppear++;
   }
   
   public static void testMeteorColor(MeteorShower mS) {
       // Is the meteor either red or blue in REGULAR MODE? white in HYPER MODE?
       testMeteorColor++;
   }
   
    // ----------------------------------------------------------------
    // REGULAR MODE & SCORING:
    
   public static void testCollisionRegularMode(MeteorShower oG, MeteorShower nG) {
    // When a laser and a meteor collide and they are the same color,
    // does the score increase? do the lives stay the same?
    
    // When a laser and a meteor collide and they are different colors,
    // does the score decrease? do the lives stay the same?
    
    // When a meteor passes the top of the screen, do you lose a life?
    // does the score stay the same?
       testCollisionRegularMode++;
    }
   
    
    // ----------------------------------------------------------------
    // HYPERSPEED MODE & SCORING:
    
    // After shooting 20 meteors in a row correctly, 
    // does the user get a powerUp
     
    // When a laser and meteor collide, does the score increase? 
    // do the lives stay the same?
    
    // When a meteor passes the top of the screen, do your lives and score
    // stay the same?
    
   public static void testPowerUpHyperMode(MeteorShower oG, MeteorShower nG) {
    // After shooting 20 meteors in a row correctly, 
    // does the user get a powerUp?
       testPowerUpHyperMode++;
    }
   
   public static void testCollisionHyperMode(MeteorShower oG, MeteorShower nG) {
    // When a laser and meteor collide, does the score increase? 
    // do the lives stay the same?
    
    // When a meteor passes the top of the screen, do your lives and score
    // stay the same?
       testCollisionHyperMode++;
    }

    
    
    // ----------------------------------------------------------------
    // GAME OVER:
   
    // When a player loses his or her last life, does the game end? 
    // If the player still has lives, is the game still going?

    
   public static void testGameOverLives(MeteorShower oG, MeteorShower nG) {
    // When a player loses his or her last life, does the game end? 
    // If the player still has lives, is the game still going?
       testGameOverLives++;
   }
   
   // ================================================================
   
   // ----------------------------------------------------------------
   // RANDOM KEY GENERATOR
   public static String randomButton() {
       Random rnd = new Random();
       int nextValue = rnd.nextInt(7);
       if (nextValue == 0) {
           return "up";
       } else if (nextValue == 1) {
           return "down";
       } else if (nextValue == 2) {
           return "right";
       } else if (nextValue == 3) {
           return "left";
       } else if (nextValue == 4){
           return "spacebar";
       } else if (nextValue == 5) {
           return "r";
         } else if (nextValue ==6) {
             return "b";
         } else {
          int stringVal = rnd.nextInt(25);
          return Character.toChars(65 + stringVal).toString();
                  }
   }
   
   // ----------------------------------------------------------------
   // TESTING ALL!!!!
   
   public static void verifyInvarients(MeteorShower oG, MeteorShower nG) throws Exception {
       for (int i = 0; i > tests; i++) {
           testConstructor();
           testStartUpOrRestartDown();
       }
       
       testPlaneMoveRightAndLeft(oG, nG);
       testLaserColorsRegularMode(oG, nG);
       testLaserColorsHyperMode(oG, nG);
       testMeteorAppear(oG, nG);
       testMeteorColor(oG);
       testCollisionRegularMode(oG, nG);
       testTriggerHyperSpeedMode(oG, nG);
       testPowerUpHyperMode(oG, nG);
       testPlaneRotate(oG, nG);
       testCollisionHyperMode(oG, nG);
       testGameOverLives(oG, nG);
       
   }
    
    
    
    
    
}
