package adventure;

import adventure.Sequence.Sequence;
import adventure.SetBag.Bag;
import java.util.Arrays;
import java.util.Random;
import javalib.funworld.World;

public class TestFunctions {

    // FOCUSING ON THE PLANE & METEORS NOW: IGNORE THE REST
    // ================================================================
    // BITS & PIECES OF GAME TESTING:
    // ================================================================
    // ----------------------------------------------------------------
    // PLANE TESTING:
    // REGULAR MODE: After pressing the RIGHT arrow, does the plane move right?
    // If it is about to go off-screen, does it stay where it is?
    // REGULAR MODE: After pressing the LEFT arrow, does the plane move left? 
    // If it is about to go off-screen, does it stay where it is?
    // HYPER MODE: After pressing the UP arrow, does the plane move up?
    // If it is about to go off-screen, does it stay where it is?
    // HYPER MODE: After pressing the DOWN arrow, does the plane move down? 
    // If it is about to go off-screen, does it stay where it is?
    // If those buttons aren't is pressed, the plane shouldn't move. 
    // ----------------------------------------------------------------
    // METEOR TESTING:
    // Is the meteor either red or blue in REGULAR MODE? orange in HYPER MODE?
    // Does a meteor move down (REGULAR MODE) or across the screen (HYPER MODE) 
    // when it's ticked?
    // Do the meteors react to what the plane's last key was?
    // Does the meteor stay the same after any key is pressed in REGULAR MODE?
    // ----------------------------------------------------------------
    // LASER TESTING:
    // REGUALR MODE: After pressing 'd', do the future lasers switch color?
    // HYPERSPEED MODE: After pressing 'd', do the lasers stay the 
    // same color?
    // REGULAR MODE: Do the lasers move up when ticked?
    // HYPERSPEED MODE: Do the lasers move across when ticked?
    // ================================================================
    // GAME LOGIC TESTING:
    // ================================================================
    // ----------------------------------------------------------------
    // REGULAR MODE & SCORING:
    // When a laser and a meteor collide and they are the same color,
    // does the score increase? do the lives stay the same?
    // When a laser and a meteor collide and they are different colors,
    // does the score decrease? do the lives stay the same?
    // When a meteor passes the top of the screen, do you lose a life?
    // does the score stay the same?
    // After pressing the 's', does the plane shoot an laser?
    // Is the laser removed once off screen?
    // ----------------------------------------------------------------
    // HYPERSPEED MODE & SCORING:
    // When the player has a Hyper-Speed PowerUp, is HyperSpeed mode triggered
    // after pressing '0'?
    // After shooting 10 meteors in a row correctly, does the player get a powerup
    // in REGULAR MODE?
    // is Hyper-Speed PowerUp given to the user?
    // When a laser and meteor collide, does the score increase? 
    // do the lives stay the same?
    // When a meteor passes the top of the screen, do your lives and score
    // stay the same?
    // After pressing the 's', does the plane shoot an laser?
    // Is the laser removed once off screen?
    // ----------------------------------------------------------------
    // GAME OVER:
    // When a player loses his or her last life in the REGULAR MODE, does the game end? 
    // When a player loses his or her last fake life in the HYPER MODE, does 
    // the game go back to REGULAR MODE?
    // If the player still has lives, is the game still going?
    // ----------------------------------------------------------------
    // INITIAL CONDITIONS (OTHER):
    // Does the plane start at the top middle of the screen?
    // Are there no meteors on screen?
    // Does the player start with three lives?
    // Does the player start with a score of 0?
    // When the game starts, is the gameOver false?
    // Does the game start in Regular Mode?
    // ================================================================
    // For all tests oG = old Game; nG = new Game; mS = Meteor Shower; 
    //               p = plane; m = meteor mD = meteor-datastruct
    //               mode = gamemod; l = laser
    // ================================================================
    
    static int tests = 10;
    static int testsMore = 4999;
    static int testPlaneMoveRightAndLeftRM = 0;
    static int testPlaneMoveRightAndLeftHM = 0;
    static int testMeteorColorRM = 0;
    static int testMeteorColorHM = 0;
    static int testMeteorMoveRM = 0;
    static int testMeteorMoveHM = 0;
    static int reactMeteorRMHM = 0;
    static int testSwitchLaserColorRM = 0;
    static int testSameLaserColorHM = 0;
    static int testLaserMoveRM = 0;
    static int testLaserMoveHM = 0;
    static int testConstructorRM = 0;
    static int testConstructorHM = 0;
    static int testShootLaserHM = 0;
    static int testShootLaserRM = 0;
    static int testLaserMeteorRemovedRM = 0;
    static int testLaserMeteorRemovedHM = 0;
    static int testTriggerHyperSpeedMode = 0;
    static int testCollisionRegularMode = 0;
    static int testPowerUpHyperMode = 0;
    static int testCollisionHyperMode = 0;
    static int testGameOverLives = 0;
    static int testPlaneMeteorMoveHM = 0;
    static int testPlaneLaserDirectionHM = 0;

    // ----------------------------------------------------------------
    // RANDOM KEY GENERATOR
    public static String randomButton() {
        Random rnd = new Random();
        int nextValue = rnd.nextInt(6);
        if (nextValue == 0) {
            return "up";
        } else if (nextValue == 1) {
            return "down";
        } else if (nextValue == 2) {
            return "right";
        } else if (nextValue == 3) {
            return "left";
        } else if (nextValue == 4) {
            return "s";
        } else if (nextValue == 5) {
            return "d";
        } else {
            int stringVal = rnd.nextInt(25);
            return Arrays.toString(Character.toChars(65 + stringVal));
        }
    }
    
    public static void testPlaneMoveRightAndLeftRM(PlaneRM oP, PlaneRM nP, String rnb) throws RuntimeException {
        int dw = 0;
        // After pressing the RIGHT arrow, does the plane move right? If it is
        // about to go off-screen, does it stay where it is?
        if (rnb.equals("right")) {
            dw = 30;
        }
        // After pressing the LEFT arrow, does the plane move left? If it is
        // about to go off-screen, does it stay where it is?
        if (rnb.equals("left")) {
            dw = -30;
        }
        // If those buttons aren't is pressed, the plane shouldn't move. 
        if (oP.isEqualTo(nP)) {
            dw = 0;
        }

        if ((oP.width + dw) != nP.width) {
            throw new RuntimeException("MovePlane doesn't work: Old: "
                    + oP.width + "New:" + nP.width
                    + "dw = " + dw);
        }

        testPlaneMoveRightAndLeftRM++;
    }

    public static void testPlaneMoveRightAndLeftHM(PlaneHM oP, PlaneHM nP, String rnb) throws RuntimeException {
        int dh = 0;
        // After pressing the UP arrow, does the plane move up? If it is
        // about to go off-screen, does it stay where it is?
        if (rnb.equals("up")) {
            dh = -30;
        }
        // After pressing the DOWN arrow, does the plane move down? If it is
        // about to go off-screen, does it stay where it is?
        if (rnb.equals("down")) {
            dh = 30;
        }
        // If those buttons aren't is pressed, the plane shouldn't move. 
        if (oP.isEqualTo(nP)) {
            dh = 0;
        }

        if ((oP.height + dh) != nP.height) {
            throw new RuntimeException("MovePlane doesn't work: Old: "
                    + oP.height + "New:" + nP.height
                    + "dw = " + dh);
        }

        testPlaneMoveRightAndLeftHM++;
    }

 
    public static void testMeteorColorRM(MeteorRM m) throws RuntimeException {
        // Is the meteor either red or blue in REGULAR MODE?
        if (!m.color.equals("red") && !m.color.equals("blue")) {
            throw new RuntimeException("Regular Meteor is not red or blue!");
        }
        testMeteorColorRM++;
    }

    public static void testMeteorColorHM(MeteorHM m) throws RuntimeException {
        // Is the meteor always white in HYPER MODE?
        if (!m.color.equals("white")) {
            // I know it's a class constant, but doing it with instance just to make
            // sure it never changes
            throw new RuntimeException("Hyper Meteor is not white!");
        }
        testMeteorColorHM++;
    }

    // Does a meteor move down (REGULAR MODE) or across the screen (HYPER MODE) 
    // when it's ticked? Also making sure movement doesn't change anything 
    // else except height
    public static void testMeteorMoveRM(MeteorRM m, MeteorRM tickedM) throws RuntimeException {
        if (tickedM.height - 1 != m.height) {
            throw new RuntimeException("Doesn't Tick!");
        }
        if (tickedM.width != m.width || tickedM.deltaHeight != m.deltaHeight
                || !tickedM.color.equals(m.color) || tickedM.identity != m.identity) {
            throw new RuntimeException("RM: Something other than height changes when ticked!");
        }
        testMeteorMoveRM++;
    }

    // Does a meteor move across the screen when it's ticked in Hyper Mode?
    // Also making sure movement doesn't change anything 
    // else except width
    public static void testMeteorMoveHM(MeteorHM m, MeteorHM tickedM) throws RuntimeException {
        if (tickedM.width != m.width + m.deltaWidth) {
            // Is it sentienal?
            if (m.width == -550) {
                throw new RuntimeException("Doesn't Tick! W:" + m.width + " + DW: " + m.deltaWidth + " = ticked Width" + tickedM.width);
            }
        }
         if (tickedM.height != m.height + m.deltaWidth || tickedM.height != m.height - m.deltaWidth) {
         if (m.width == -19) {
                throw new RuntimeException("Doesn't Tick! W:" + m.height + " + DW: " + m.deltaWidth + " = ticked Height" + tickedM.height);
            }
        }
        
        if (!tickedM.color.equals(m.color) || tickedM.identity != m.identity) {
            // Is it sentienal?
                throw new RuntimeException("HM: Something other than width changes when ticked!");
            
        }
        testMeteorMoveHM++;
    }

    // Does the meteor stay the same after any key is pressed? (True in both modes)
    public static void reactMeteorRM(MeteorRM mR, MeteorRM mRreacted) throws RuntimeException {
        if (!mR.isEqualToDWHC(mRreacted)) {
            throw new RuntimeException("React changed meteor components");
        }
        if (!mR.isEqualToId(mRreacted)) {
            throw new RuntimeException("React changed ID's!");
        }
        reactMeteorRMHM++;
    }


    public static void testSwitchLaserColorRM(LaserRM laser, LaserRM reactedLaser, String key) throws RuntimeException {
        if (laser.color.equals(reactedLaser.color) && key.equals("d")) {
            throw new RuntimeException("Colors equal, but should have changed");
        }
        if (!laser.color.equals(reactedLaser.color) && !key.equals("d")) {
            throw new RuntimeException("Colors not equal, but shouldn't have changed" + laser.color + reactedLaser.color + key);
        }
        testSwitchLaserColorRM++;
    }

    // HYPERSPEED MODE: After pressing enter, do the lasers stay the 
    // same color?
    public static void testSameLaserColorHM(LaserHM laser, LaserHM reactedLaser) throws RuntimeException {
        if (!laser.color.equals(reactedLaser.color)) {
            throw new RuntimeException("Colors Should NEVER CHANGE");
        }
        testSameLaserColorHM++;
    }

    // REGULAR MODE: Do the lasers move up when ticked?
    public static void testLaserMoveRM(LaserRM laser, LaserRM tickedLaser) throws RuntimeException {
        if (laser.height != tickedLaser.height + 5) {
            throw new RuntimeException("Laser didn't tick!" + laser.height + " " + tickedLaser.height);
        }
        if (laser.width != laser.width) {
            throw new RuntimeException("Width changed in regular mode!");
        }
        testLaserMoveRM++;
    }

    public static void testLaserMoveHM(LaserHM laser, LaserHM tickedLaser) throws RuntimeException {
        if (laser.width != tickedLaser.width - 5 && laser.width != tickedLaser.width + 5) {
            throw new RuntimeException("Laser didn't tick!" + laser.width + " " + tickedLaser.width);
        }
        if (laser.height != laser.height) {
            throw new RuntimeException("Height changed in hyper mode!");
        }
        testLaserMoveHM++;
    }
    
    public static void testPlaneLaserDirectionHM(PlaneHM plane) throws RuntimeException {
        LaserHM newLaser = new LaserHM(plane);
        if (plane.direction.equals("left")) {
            if (newLaser.deltaWidth != -1) {
                throw new RuntimeException("Plane/Laser Problems");
            }
        } else {
            if (newLaser.deltaWidth != 1) {
                throw new RuntimeException("Plane/Laser Problems");
            }
        }
        testPlaneLaserDirectionHM++;
    }

    public static void testingIndividualComponents() throws RuntimeException {
        // ========================================================
        // TESTING PLANE INDIVIDUALLY:
        // ========================================================
        PlaneRM planeR = new PlaneRM();
        PlaneHM planeH = new PlaneHM();
        for (int i = 0; i <= testsMore; i++) {
            String k = randomButton();
            // ---------REACT & TICK---------
            PlaneRM planeRegularReacted = planeR.react(k);
            PlaneHM planeHyperReacted = planeH.react(k);
            testPlaneMoveRightAndLeftRM(planeR, planeRegularReacted, k);
            testPlaneMoveRightAndLeftHM(planeH, planeHyperReacted, k);
            
            testPlaneLaserDirectionHM(planeHyperReacted);
            planeH = planeHyperReacted;
            planeR = planeRegularReacted;
        }

        System.out.println("testPlaneMoveRightAndLeftRM success: " + testPlaneMoveRightAndLeftRM + " times");
        System.out.println("testPlaneMoveRightAndLeftHM success:" + testPlaneMoveRightAndLeftHM + " times");

        // ========================================================
        // TESTING METEOR INDIVIDUALLY:
        // ========================================================
        MeteorHM mH = new MeteorHM(new PlaneHM());
        MeteorRM mR = new MeteorRM(new PlaneRM());
        for (int i = 0; i <= testsMore; i++) {
            // --------REACT-----------
            String rnd = randomButton();
            MeteorRM reactmR = mR.react(rnd);
            MeteorHM reactmH = mH.react(rnd);
            reactMeteorRM(mR, reactmR);
            // --------COLOR--------------
            testMeteorColorRM(mR);
            testMeteorColorHM(mH);
            // ---------TICK-------------
            MeteorRM tickedmR = mR.onTick();
            testMeteorMoveRM(mR, tickedmR);
            mR = tickedmR;
            MeteorHM tickedmH = mH.onTick();
            testMeteorMoveHM(mH, tickedmH);
            mH = tickedmH;
            PlaneHM planeHyperReacted = planeH.react(rnd);
            testPlaneMeteorMoveHM(planeHyperReacted, mH);
            
            
        }
        System.out.println("testMeteorColorRM success: " + testMeteorColorRM + " times");
        System.out.println("testMeteorColorHM success: " + testMeteorColorHM + " times");
        System.out.println("testMeteorMoveRM success: " + testMeteorMoveRM + " times");
        System.out.println("testMeteorMoveHM success: " + testMeteorMoveHM + " times");
        System.out.println("testMeteorMoveHM success: " + testPlaneMeteorMoveHM + " times");
        // ========================================================
        // TESTING LASERS: INDIVIDUALLY:
        // ========================================================
        LaserRM LR = new LaserRM(planeR);
        LaserHM LH = new LaserHM(planeH);
        for (int i = 0; i <= testsMore; i++) {
            // --------REACT & COLOR-----------
            String rnd = randomButton();
            LaserRM reactedLR = LR.react(rnd);
            testSwitchLaserColorRM(LR, reactedLR, rnd);
            LR = reactedLR;
            LaserHM reactedLH = LH.react(rnd);
            testSameLaserColorHM(LH, reactedLH);
            LH = reactedLH;
            // ---------TICK-------------
            LaserRM tickedLR = LR.onTick();
            LaserHM tickedLH = LH.onTick();
            testLaserMoveRM(LR, tickedLR);
            testLaserMoveHM(LH, tickedLH);
            LR = tickedLR;
            LH = tickedLH;
            PlaneHM planeHyperReacted = planeH.react(rnd);
            
        }
        System.out.println("testSwitchLaserColorRM success: " + testSwitchLaserColorRM + " times");
        System.out.println("testSameLaserColorHM success: " + testSameLaserColorHM + " times");

    }

    // ----------------------------------------------------------------
    // INITIAL CONDITIONS:
    // ----------------------------------------------------------------
    public static void testConstructorRM() throws RuntimeException {
        MeteorShowerRM mS = new MeteorShowerRM();
        // Does the plane start at the top middle of the screen?
        if (mS.plane.height != mS.plane.MAXH || mS.plane.width != PlaneRM.middleOfScreenWidth) {
            throw new RuntimeException("The player does not start at the top middle of "
                    + "screen");
        }

        // Are there no meteors on screen?
        if (!mS.meteorDataStructRM.isEmptyHuh()) {
            throw new RuntimeException("There is a meteor on-screen when the game "
                    + "starts");
        }

        // Are there no lasers on screen?
        if (!mS.lasersRM.isEmptyHuh()) {
            throw new RuntimeException("There is a meteor on-screen when the game "
                    + "starts");
        }

        // Does the player start with three lives?
        if (mS.lives.life != 3) {
            throw new RuntimeException("Starting lives doesn't equal 3");
        }

        // Does the player start with a score of 0?
        if (mS.score.score != 0) {
            throw new RuntimeException("Starting score isn't 0");
        }

        // Does the game start?
        if (mS.gameOver) {
            throw new RuntimeException("The game is starting, but the game is over");
        }

        // Does the game start without a powerup
        if (mS.powerUp != 0) {
            throw new RuntimeException("Starting with a powerup!");
        }

        // Does the game start with a 0 counter?
        if (mS.correctShootCounter != 0) {
            throw new RuntimeException("Starting with correct shoot counter > 0");
        }
        testConstructorRM++;
    }

    public static void testConstructorHM() throws RuntimeException {
        MeteorShowerHM mS = new MeteorShowerHM();
        // Does the plane start at the top middle of the screen?
        if (mS.plane.width != PlaneHM.middleOfScreenWidth) {
            throw new RuntimeException("The player does not start at the middle of "
                    + "screen");
        }

        // Are there no meteors on screen?
        if (!mS.meteorDataStructHM.isEmptyHuh()) {
            throw new RuntimeException("There is a meteor on-screen when the game "
                    + "starts");
        }

        // Are there no lasers on screen?
        if (!mS.lasersHM.isEmptyHuh()) {
            throw new RuntimeException("There is a meteor on-screen when the game "
                    + "starts");
        }

        // Does the game start with a zero counter?
        if (mS.missingMeteorsCounter != 0) {
            throw new RuntimeException("Starting without a zero counter");
        }

        testConstructorHM++;
    }

    public static void testConstructors() throws RuntimeException {
        for (int i = 0; i > tests; i++) {
            testConstructorRM();
            testConstructorHM();
        }
    }

    // ----------------------------------------------------------------
    // GENERAL CONDITIONS TO TEST:
    // ----------------------------------------------------------------
    public static void testShootLaser(MeteorShowerRM old, MeteorShowerRM newG, String key) throws RuntimeException {

        // Is a laser shot when the spacebar is pressed?
        int lasersAboutToLeave = 0;

        // Going through all lasers; checking if going to disappear or colliding
        Sequence<LaserRM> laserSeq = old.lasersRM.seq();
        Bag<MeteorRM> theMS = old.meteorDataStructRM.tick();
        while (laserSeq.hasNext()) {
            LaserRM l = laserSeq.here().onTick();
            if (l.aboutToLeave() || theMS.collidesWith(l) != null) {
                lasersAboutToLeave++;
            }
            laserSeq = laserSeq.next();
        }

        if (key.equals("s")) {
            if (old.lasersRM.cardinality() + 1 - lasersAboutToLeave != newG.lasersRM.cardinality()) {
                throw new RuntimeException("S pressed, but laser isn't added Old:" 
                        + old.lasersRM.cardinality() + " New:" + newG.lasersRM.cardinality() + "(should be: " + (old.lasersRM.cardinality() + 1 - lasersAboutToLeave) + ")" + "Left:" + lasersAboutToLeave);
            }
        } else {
            //if for each laser in RM, none of them are about to leave
            if (old.lasersRM.cardinality() - lasersAboutToLeave != newG.lasersRM.cardinality()) {
                throw new RuntimeException("S isn't pressed, but laser cardinality changed Old:" 
                        + old.lasersRM.cardinality() + " New:" + newG.lasersRM.cardinality() + "Left:" + lasersAboutToLeave);
            }
        }
        testShootLaserRM++;
    }

    public static void testShootLaser(MeteorShowerHM old, MeteorShowerHM newG, String key) throws RuntimeException {
        // Is a laser shot when the spacebar is pressed?
        int lasersAboutToLeave = 0;

        //Going through all lasers
        Sequence<LaserHM> laserSeq = old.lasersHM.seq();
        Bag<MeteorHM> theMS = old.meteorDataStructHM.tick();
        while (laserSeq.hasNext()) {
            LaserHM l = laserSeq.here().onTick();
            if (l.aboutToLeave() || theMS.collidesWith(l) != null) {
                lasersAboutToLeave++;
            }
            laserSeq = laserSeq.next();
        }

        if (key.equals("s")) {
             if (old.lasersHM.cardinality() + 1 - lasersAboutToLeave != newG.lasersHM.cardinality()) {
                throw new RuntimeException("Spacebar pressed, but laser isn't added");
                  }
        } else {
            //if for each laser in RM, none of them are about to leave
            if (old.lasersHM.cardinality() - lasersAboutToLeave != newG.lasersHM.cardinality()) {
                throw new RuntimeException("Spacebar isn't pressed, but laser cardinality changed");
            }
        }
        testShootLaserHM++;
    }

    public static void testLaserMeteorRemoved(MeteorShowerRM old, MeteorShowerRM newG) throws RuntimeException {
        // Is the laser/meteor removed once off screen?

        // Going through each meteor, if one is about to leave the screen
        Sequence<MeteorRM> meteorSeq = old.meteorDataStructRM.seq();
        while (meteorSeq.hasNext()) {
            if (meteorSeq.here().collidesWith(old.plane) != null) {
                if (newG.meteorDataStructRM.member(meteorSeq.here()) || old.lasersRM.collidesWith(meteorSeq.here()) != null) {
                    throw new RuntimeException("A meteor was about to leave, but it's still here");
                }
            }
             meteorSeq = meteorSeq.next();
        }

        // / Going through each laser, if one is about to leave the screen
        Sequence<LaserRM> laserSeq = old.lasersRM.seq();
        while (laserSeq.hasNext()) {
            if (laserSeq.here().aboutToLeave()) {
                if (newG.lasersRM.member(laserSeq.here()) || old.meteorDataStructRM.collidesWith(laserSeq.here()) != null) {
                    throw new RuntimeException("A laser was about to leave, but still here");
                }
                
            }
            laserSeq = laserSeq.next();
        }

        testLaserMeteorRemovedRM++;
    }

    public static void testLaserMeteorRemoved(MeteorShowerHM old, MeteorShowerHM newG) throws RuntimeException {
        // Is the laser/meteor removed once off screen?

        // Going through each meteor, if one is about to leave the screen && checking collisions
        Sequence<MeteorHM> meteorSeq = old.meteorDataStructHM.seq();
        while (meteorSeq.hasNext()) {
            if (meteorSeq.here().collidesWith(old.plane) != null || old.lasersHM.collidesWith(meteorSeq.here()) != null) {
                if (newG.meteorDataStructHM.member(meteorSeq.here())) {
                    throw new RuntimeException("A meteor was about to leave, but it's still here");
                }
                
            }
            meteorSeq = meteorSeq.next();
        }

        // / Going through each laser, if one is about to leave the screen
        Sequence<LaserHM> laserSeq = old.lasersHM.seq();
        while (laserSeq.hasNext()) {
            if (laserSeq.here().aboutToLeave() || old.meteorDataStructHM.collidesWith(laserSeq.here()) != null) {
                if (newG.lasersHM.member(laserSeq.here())) {
                    throw new RuntimeException("A laser was about to leave, but still here");
                }
                
            }
            laserSeq = laserSeq.next();
        }

        testLaserMeteorRemovedHM++;
    }

    public static void testTriggerHyperSpeedMode(MeteorShowerRM oG, String rnb) throws RuntimeException {
//    // When the player has a Hyper-Speed PowerUp, is HyperSpeed mode triggered
//    // after pressing 0?
        World nG = oG.REALonKeyEvent("0");
            if (oG.powerUp > 0 && (rnb.equals("0"))) {
                if (nG instanceof MeteorShowerHM) {
                    MeteorShowerHM nHG = (MeteorShowerHM) nG;
                    if (oG.powerUp - 1 != nHG.powerUps) {
                        throw new RuntimeException("PowerUp not lost");
                    }
                } else {
                    throw new RuntimeException("Hyper mode not triggered");
                }
            } else {

            }
            testTriggerHyperSpeedMode++;
        }

    //   
    //    // ----------------------------------------------------------------
//    // REGULAR MODE & SCORING:
//    // ----------------------------------------------------------------
    public static void testCollisionRegularMode(MeteorShowerRM oG, MeteorShowerRM nG) throws RuntimeException {
        Sequence<LaserRM> laserSeq = oG.lasersRM.seq();
        int collisionScore = 0;
        int collisionLives = 0;
        Bag<MeteorRM> theMS = oG.meteorDataStructRM.tick();
        // Checking Meteors & Lasers Colliding, Score, and Lives
        while (laserSeq.hasNext()) {
            MeteorRM collidingMeteor = theMS.collidesWith(laserSeq.here().onTick());
            // If there is a collidingMeteor... then check all of this stuff
            if (collidingMeteor != null) {
                // When a laser and a meteor collide and they are the same color,
                // does the score increase? do the lives stay the same?
                if (collidingMeteor.color.equals(laserSeq.here().color)) {
                    collisionScore++;
                } // When a laser and a meteor collide and they are different colors,
                // does the score decrease? do the lives stay the same?
                else {
                    collisionScore--;
                }
            }
            laserSeq = laserSeq.next();
        }

        // Checking Meteors Leaving & Score & Lives
        Sequence<MeteorRM> meteorSeq = theMS.seq();
        while (meteorSeq.hasNext()) {
             // When a meteor passes the top of the screen, do you lose a life?
            // does the score stay the same?
            if (meteorSeq.here().collidesWith(oG.plane) != null) {
                collisionLives--;
            }
            meteorSeq = meteorSeq.next();
        }

        if (oG.score.score + (collisionScore * 10) != nG.score.score) {
            throw new RuntimeException("Score didn't increase" + oG.score.score + " " + nG.score.score);
        }
        if (oG.lives.life + collisionLives != nG.lives.life) {
            throw new RuntimeException("Lives changed!" + oG.lives.life + " + " + collisionLives + " == " + nG.lives.life);
        }
        testCollisionRegularMode++;
    }

    // ----------------------------------------------------------------
    // HYPERSPEED MODE & SCORING:
    // ----------------------------------------------------------------
    public static void testPowerUpHyperMode(MeteorShowerRM oG, MeteorShowerRM nG) throws RuntimeException {
        // After shooting 20 meteors in a row correctly, does the user get a powerUp?
        if (oG.correctShootCounter == 19) {
            if (oG.powerUp + 1 != nG.powerUp) {
                throw new RuntimeException("PowerUp not Collected");
            }
        }
        testPowerUpHyperMode++;
    }
    

    public static void testCollisionHyperMode(MeteorShowerHM oG, MeteorShowerHM nG) throws RuntimeException {
        Sequence<LaserHM> laserSeq = oG.lasersHM.seq();
        int collisionScore = 0;
        int collisionLives = 0;

        // Checking Meteors & Lasers Colliding, Score, and Lives
        Bag<MeteorHM> theMS = oG.meteorDataStructHM.tick();
        while (laserSeq.hasNext()) {
            MeteorHM collidingMeteor = theMS.collidesWith(laserSeq.here().onTick());
            // If there is a collidingMeteor... then check all of this stuff
            if (collidingMeteor != null) {
                // When a laser and meteor collide, does the score increase? 
                // do the lives stay the same?
                collisionScore++;
            }
            laserSeq = laserSeq.next(); 
        }
        // When a meteor passes the top of the screen, do your lives and score
        // stay the same? --> no need to change collisionScore or collisionLives

        if (oG.score.score + (collisionScore * 10) != nG.score.score) {
            throw new RuntimeException("Score didn't increase");
        }
        if (oG.lives.life + collisionLives != nG.lives.life) {
            throw new RuntimeException("Lives changed!");
        }
        testCollisionHyperMode++;
    }
//
//    
//    
//    // ----------------------------------------------------------------
//    // GAME OVER:
    // ----------------------------------------------------------------

    public static void testGameOverLives(MeteorShowerRM oG, MeteorShowerRM nG) throws RuntimeException {
        // When a player loses his or her last life, does the game end? 
        // If the player still has lives, is the game still going?
        if (oG.gameOver) {
            if (oG.lives.life != 0) {
                throw new RuntimeException("Still have lives & gameOver");
            }
        } else {
            if (oG.lives.life == 0) {
                throw new RuntimeException("0 Lives & gameNotOver");
            }
            if (nG.gameOver) {
                if (nG.lives.life != 0 || nG.lives.life != 1 || nG.lives.life != 2) {
                    throw new RuntimeException("Still have lives & gameOver");
                }
            } else {
                if (nG.lives.life == 0) {
                    throw new RuntimeException("0 Lives & gameNotOver");
                }
            }
        }
        testGameOverLives++;
    }
    
    public static void testPlaneMeteorMoveHM(PlaneHM p, MeteorHM meteor) throws RuntimeException {
        MeteorHM ticked = meteor.onTick(p);
        if (p.upOrDown.equals("up")) {
            if (meteor.deltaWidth == 1) {
                if (ticked.height != meteor.height + 1) {
                    throw new RuntimeException("Plane, Meteor, FAIL" + meteor.identity);
                }
            } else {
                if (ticked.height != meteor.height - 1) {
                    throw new RuntimeException("Plane, Meteor, FAIL" + meteor.identity);
                }
            }
        } else {
            if (meteor.deltaWidth == 1) {
                if (ticked.height != meteor.height - 1) {
                    throw new RuntimeException("Plane, Meteor, FAIL" + meteor.identity);
                }
            } else {
                if (ticked.height != meteor.height + 1) {
                    throw new RuntimeException("Plane, Meteor, FAIL" + meteor.identity + meteor.height + " " + ticked.height);
                }
            }
        }
        testPlaneMeteorMoveHM++;
    }
//   
//   // ================================================================
//   // TESTING ALL!!!!
//   // ================================================================

    public static void verifyInvariantsRM(MeteorShowerRM oG, MeteorShowerRM nG, String key) throws RuntimeException {
        for (int i = 0; i < tests; i++) {
            testConstructorRM();
            testShootLaser(oG, nG, key);
            testLaserMeteorRemoved(oG, nG);
            testTriggerHyperSpeedMode(oG, key);
            testCollisionRegularMode(oG, nG);
            testPowerUpHyperMode(oG, nG);
            testGameOverLives(oG, nG);
        }
        
    }

    public static void verifyInvariantsHM(MeteorShowerHM oG, MeteorShowerHM nG, String key) throws RuntimeException {
        for (int i = 0; i < tests; i++) {
            testConstructorHM();
            testShootLaser(oG, nG, key);
            testLaserMeteorRemoved(oG, nG);
            testCollisionHyperMode(oG, nG);

        }
       

    }
}
