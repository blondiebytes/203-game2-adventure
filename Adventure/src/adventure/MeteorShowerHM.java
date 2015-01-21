
package adventure;

import adventure.Sequence.Sequence;
import adventure.SetBag.Bag;
import static adventure.SetBag.SetBag_NonEmpty.empty;
import static adventure.TestFunctions.testCollisionHyperMode;
import static adventure.TestFunctions.testConstructorHM;
import static adventure.TestFunctions.testLaserMeteorRemovedHM;
import static adventure.TestFunctions.testShootLaserHM;
import static adventure.TestFunctions.verifyInvariantsHM;
import java.util.logging.Level;
import java.util.logging.Logger;
import javalib.colors.White;
import javalib.funworld.World;
import javalib.worldimages.FromFileImage;
import javalib.worldimages.OverlayImages;
import javalib.worldimages.Posn;
import javalib.worldimages.TextImage;
import javalib.worldimages.WorldImage;

public final class MeteorShowerHM extends World {
    Lives lives;
    PlaneHM plane;
    Bag<MeteorHM> meteorDataStructHM;
    Bag<LaserHM> lasersHM;
    Bag<Explosion> explosionsHM;
    Score score;
    static int missingMeteorsCounter;
    int powerUps;
    static int background;
    static int releaseMeteor = 0;
    static String key = "a";
    static boolean shouldPrint = false;
    
    // ========== CONSTRUCTORS ==========
    public MeteorShowerHM() {
        super();
        this.plane = new PlaneHM();
        this.meteorDataStructHM = empty();
        this.lasersHM = empty();
        this.explosionsHM = empty();
        this.lives = new Lives();
        this.score = new Score();
        this.missingMeteorsCounter = 0;
        this.powerUps = 0;
    }
    
    public MeteorShowerHM(PlaneHM plane, Bag<MeteorHM> meteors, Bag<LaserHM> lasers, Bag<Explosion> explosions, Lives lives, Score score, int powerUps, int background){
        super();
        this.plane = plane;
        this.meteorDataStructHM = meteors;
        this.lasersHM = lasers;
        this.explosionsHM = explosions;
        this.lives = lives;
        this.score = score;
        this.powerUps = powerUps;
        this.background = background;
    }
    
    public MeteorShowerHM(PlaneHM plane, Bag<MeteorHM> meteors, Bag<LaserHM> lasers, Bag<Explosion> explosions, Lives lives, Score score, int missingMeteors, 
            int powerUps, int background, boolean printTesters){
        super();
        this.plane = plane;
        this.meteorDataStructHM = meteors;
        this.lasersHM = lasers;
        this.explosionsHM = explosions;
        this.lives = lives;
        this.score = score;
        this.powerUps = powerUps;
        this.background = background;
        this.missingMeteorsCounter = 0;
    }
  
    

    
    // ========== CREATE GAME ==========
    public boolean bigBang() {
        return this.bigBang(500, 500, .01);
    }
    
    

    // ========== TICK ==========
    // This method produces a new instance of the world as it should be after one tick of the clock has passed.
    public World onTick() {
        Bag newMeteors = this.meteorDataStructHM;
        newMeteors = newMeteors.tick(this.plane);
        
          if (releaseMeteor % 40 == 0) {
            // Solves the problem of intervals
            newMeteors = (newMeteors.add(new MeteorHM(this.plane).onTick()));
            /* Need to tick the meteors & add a new one */
            
             }
        
          releaseMeteor++;
        
        Bag<LaserHM> newLasers = this.lasersHM.tick();
        Bag newExplosions = this.explosionsHM.tick();
        return new MeteorShowerHM(this.plane, newMeteors, newLasers, newExplosions,
                this.lives, this.score, this.powerUps, this.background).update(); /*check for collision of laser and meteor and update score */
    }
    
    
    
     // ========== COLLISION ==========
    // This checks for a collision and updates the lives, score, and missingMeteorCounter
    // Multiple Types of Collisions:
    // 1. Meteor passes the plane --> score same, add 1 to missingMeteorCounter --> check if end game; if so, back to RM
    // 2. Laser hits Meteor --> plane same, lives same, score + 10, missingMeteor same
    public World update() {
        PlaneHM newPlane = this.plane;
        Bag<MeteorHM> newMeteors = this.meteorDataStructHM;
        Bag<LaserHM> newLasers = this.lasersHM;
        Bag<Explosion> newExplosions = this.explosionsHM;
        Lives newLives = this.lives;
        Score newScore = this.score;
        
        // Update Explosions
        Sequence<Explosion> seqExp = newExplosions.seq();
        if (seqExp.hasNext()) {
            if (seqExp.here().show <= 0) {
                newExplosions = newExplosions.remove(seqExp.here());
            }
            seqExp = seqExp.next();
        }
        
        // 1. Meteor passes the plane --> score same, 
        // add 1 to missingMeteorCounter --> check if end game; if so, back to RM
        MeteorHM collider = newMeteors.collidesWith(newPlane);
        if (collider != null /* if a meteor passes the plane... */) {
            newMeteors = newMeteors.remove(collider); /* // PICK OUT THAT METEOR AND REMOVE IT !!!!!!!!!!! */
            newExplosions = newExplosions.add(new Explosion(collider.width, collider.height, true));
            MeteorShowerHM.missingMeteorsCounter++;
            if (this.backToRegularMode()) {
                try {
                    return new MeteorShowerRM(new PlaneRM(), empty(), empty(), empty(), newLives, newScore, false, this.powerUps - 1, 0, 1);
                } catch (Exception ex) {
                    Logger.getLogger(MeteorShowerHM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        // 2. Laser hits Meteor --> plane same, lives same, score + 10, missingMeteor same
        Sequence<LaserHM> seqLaser = newLasers.seq();
        while (seqLaser.hasNext()) {
            LaserHM collidingLaser = seqLaser.here();
            MeteorHM collidingMeteor = newMeteors.collidesWith(collidingLaser);
            if (collidingMeteor != null) {
                newExplosions = newExplosions.add(new Explosion(collidingMeteor.width, collidingMeteor.height, true));
                /* remove that colliding meteor! */ newMeteors = newMeteors.remove(collidingMeteor);
                /*remove that colliding laser! */ newLasers = newLasers.remove(collidingLaser);
            newScore = newScore.addScore();
            }
            seqLaser = seqLaser.next();
        }
        
        return new MeteorShowerHM(newPlane, newMeteors, newLasers, newExplosions, newLives, newScore, this.powerUps, this.background);
    }
    
    public boolean backToRegularMode() {
        return this.missingMeteorsCounter == 5;
    }
    
    
    
    // ========== REACT ==========
    // This method produces the world in response to the user pressing a key on the keyboard. 
    public World onKeyEvent(String ke){
        this.checkInvariantsLive(ke);
        return REALonKeyEvent(ke);
    }
    
    public World REALonKeyEvent(String ke) {
        this.key = ke;
         Bag<LaserHM> newLasersHM = this.lasersHM;
        if (ke.equals("s")) {
           newLasersHM = newLasersHM.add(new LaserHM(this.plane));
        }
        PlaneHM newPlane = plane.react(ke);
        return new MeteorShowerHM(newPlane, this.meteorDataStructHM, newLasersHM, this.explosionsHM, this.lives, this.score, this.powerUps, this.background);
    }
    
     public void checkInvariantsLive(String key) throws RuntimeException{
//                MeteorShowerHM nG = (MeteorShowerHM) this.REALonKeyEvent(key).onTick();
//                verifyInvariantsHM(this, nG, key);
//                if (shouldPrint) {
//                System.out.println();
//                System.out.println("testConstructorHM " + testConstructorHM + " times");
//                System.out.println("testShootLaserHM " + testShootLaserHM + " times");
//                System.out.println("testLaserMeteorRemovedHM " + testLaserMeteorRemovedHM + " times");
//                System.out.println("testCollisionHyperMode " + testCollisionHyperMode + " times");
//                System.out.println("ran HM tests");
//                System.out.println();
//                System.out.println();
            //   }
        }
    
    
    // ========== DRAW ==========
    // Draws the image on screen
     public WorldImage makeImage() {
         WorldImage background;
        if (this.background % 2 == 1) {
            background = new FromFileImage(new Posn(0, 0), "art/background-fire.jpg");
        } else {
            background = new FromFileImage(new Posn(0, 0), "art/background-stars.jpg");
        }
         
         // Drawing Plane
        WorldImage finalImage = new OverlayImages(background, this.plane.planeImage());
        
         // Drawing Meteors
        Sequence<MeteorHM> seqMeteor = this.meteorDataStructHM.seq();
        while (seqMeteor.hasNext()) {
            finalImage = new OverlayImages(finalImage, seqMeteor.here().meteorImage());
            seqMeteor = seqMeteor.next();
        }
        

        // Drawing Lasers
        Sequence<LaserHM> seqLaser = this.lasersHM.seq();
        while (seqLaser.hasNext()) {
            finalImage = new OverlayImages(finalImage, seqLaser.here().laserImage());
            seqLaser = seqLaser.next();
        }
        
        // Drawing Explosions
        Sequence<Explosion> seqExplosion = this.explosionsHM.seq();
        while (seqExplosion.hasNext()) {
            finalImage = new OverlayImages(finalImage, seqExplosion.here().explosionImage());
            seqExplosion = seqExplosion.next();
        }
        
        // Drawing Score
        TextImage score2 = new TextImage(new Posn(55, 40), "Score: " + this.score.score, 18, new White());
        finalImage = new OverlayImages(finalImage, score2);
        
        // Drawing Lives
//        switch (lives.life) {
//            case 1: finalImage = new OverlayImages(finalImage,lives.livesImage(30,60));
//                break;
//            case 2: finalImage = new OverlayImages(finalImage,lives.livesImage(30,60));
//                    finalImage = new OverlayImages(finalImage,lives.livesImage(55,60));
//                break;
//            case 3: finalImage = new OverlayImages(finalImage,lives.livesImage(30,60));
//                     finalImage = new OverlayImages(finalImage,lives.livesImage(55,60));
//                      finalImage = new OverlayImages(finalImage,lives.livesImage(80,60));
//        }
        
        switch(5 - this.missingMeteorsCounter) {
            case 1: finalImage = new OverlayImages(finalImage, missingMeteorImage(20,60));
                break;
            case 2: finalImage = new OverlayImages(finalImage,missingMeteorImage(20,60));
                    finalImage = new OverlayImages(finalImage,missingMeteorImage(45,60));
                break;
            case 3: finalImage = new OverlayImages(finalImage,missingMeteorImage(20,60));
                     finalImage = new OverlayImages(finalImage,missingMeteorImage(45,60));
                      finalImage = new OverlayImages(finalImage,missingMeteorImage(70,60));
                break;
            case 4: finalImage = new OverlayImages(finalImage,missingMeteorImage(20,60));
                     finalImage = new OverlayImages(finalImage,missingMeteorImage(45,60));
                      finalImage = new OverlayImages(finalImage,missingMeteorImage(70,60));
                        finalImage = new OverlayImages(finalImage,missingMeteorImage(95,60));
                break;
            case 5: finalImage = new OverlayImages(finalImage,missingMeteorImage(20,60));
                     finalImage = new OverlayImages(finalImage,missingMeteorImage(45,60));
                      finalImage = new OverlayImages(finalImage,missingMeteorImage(70,60));
                        finalImage = new OverlayImages(finalImage,missingMeteorImage(95,60));
                           finalImage = new OverlayImages(finalImage,missingMeteorImage(120,60));
                break;
            default: 
        }
              
        return finalImage;
    }
    
     WorldImage missingMeteorImage(int width, int height) {
         return new FromFileImage(new Posn(width, height), "art/darkheart.png");
     }
    
    // This method produces an instance of a class WorldEnd that consists of a boolean value 
    // indicating whether the world is ending (false if the world goes on) and the WorldImage 
    // that represents the last image to be displayed - for example announcing the winner of the game, 
    // or the final score.
//    public WorldEnd worldEnds() {
//        
//    }

}
