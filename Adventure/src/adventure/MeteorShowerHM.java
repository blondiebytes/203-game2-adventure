
package adventure;

import adventure.Sequence.Sequence;
import adventure.SetBag.Bag;
import static adventure.SetBag.SetBag_NonEmpty.empty;
import javalib.colors.Blue;
import javalib.funworld.World;
import javalib.worldimages.OverlayImages;
import javalib.worldimages.Posn;
import javalib.worldimages.RectangleImage;
import javalib.worldimages.WorldImage;

public class MeteorShowerHM extends World {
    Lives lives;
    PlaneHM plane;
    Bag<MeteorHM> meteorDataStructHM;
    Bag<LaserHM> lasersHM;
    Score score;
    int missingMeteorsCounter;
    int powerUps;
// NEEDTO: Add WorldImage plane property !!!!!!!!!!!<<<<===========
    
    
    // ========== CONSTRUCTORS ==========
    public MeteorShowerHM() {
        super();
        this.plane = new PlaneHM();
        this.meteorDataStructHM = empty();
        this.lasersHM = empty();
        this.lives = new Lives();
        this.score = new Score();
        this.missingMeteorsCounter = 0;
        this.powerUps = 0;
    }
    
    public MeteorShowerHM(PlaneHM plane, Bag<MeteorHM> meteors, Bag<LaserHM> lasers, Lives lives, Score score, int missingMeteors, int powerUps){
        super();
        this.plane = plane;
        this.meteorDataStructHM = meteors;
        this.lasersHM = lasers;
        this.lives = lives;
        this.score = score;
        this.missingMeteorsCounter = missingMeteors;
        this.powerUps = powerUps;
    }
  

    
    // ========== CREATE GAME ==========
    public boolean bigBang() {
        return this.bigBang(500, 500, 1);
    }
    
    

    // ========== TICK ==========
    // This method produces a new instance of the world as it should be after one tick of the clock has passed.
    public World onTick() {
        Bag<MeteorHM> newMeteors = this.meteorDataStructHM.add(new MeteorHM()).tick();
        Bag<LaserHM> newLasers = this.lasersHM.tick();
        return new MeteorShowerHM(this.plane, newMeteors, newLasers, 
                this.lives, this.score, this.missingMeteorsCounter, this.powerUps).update(); /*check for collision of laser and meteor and update score */
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
        Lives newLives = this.lives;
        Score newScore = this.score;
        int newCounter = this.missingMeteorsCounter;
        // 1. Meteor passes the plane --> score same, 
        // add 1 to missingMeteorCounter --> check if end game; if so, back to RM
        MeteorHM collider = newMeteors.collidesWith(newPlane);
        if (collider != null /* if a meteor passes the plane... */) {
            newMeteors = newMeteors.remove(collider); /* // PICK OUT THAT METEOR AND REMOVE IT !!!!!!!!!!! */
            newCounter = newCounter + 1;
            if (this.backToRegularMode()) {
                return new MeteorShowerRM(new PlaneRM(), empty(), empty(), newLives, newScore, false, this.powerUps, 0, 1);
            }
        }
        // 2. Laser hits Meteor --> plane same, lives same, score + 10, missingMeteor same
        Sequence<LaserHM> seqLaser = newLasers.seq();
        while (seqLaser.hasNext()) {
            LaserHM collidingLaser = seqLaser.here();
            MeteorHM collidingMeteor = newMeteors.collidesWith(collidingLaser);
            if (collidingMeteor != null) {
                /* remove that colliding meteor! */ newMeteors = newMeteors.remove(collidingMeteor);
                /*remove that colliding laser! */ newLasers = newLasers.remove(collidingLaser); 
            newScore = newScore.addScore();
            }
        }
        return new MeteorShowerHM(newPlane, newMeteors, newLasers, newLives, newScore, newCounter, this.powerUps);
    }
    
    public boolean backToRegularMode() {
        return this.missingMeteorsCounter >= 5;
    }
    
    
    
    // ========== REACT ==========
    // This method produces the world in response to the user pressing a key on the keyboard. 
    public World onKeyEvent(String ke) {
         Bag<LaserHM> newLasersHM = this.lasersHM;
        // Also have the plane switching sides -> 
        if (ke.equals("right")) {
            // switch plane image
        }
        if (ke.equals("left")) {
            // switch plane image
        }
        if (ke.equals("spacebar")) {
           newLasersHM = this.lasersHM.add(new LaserHM(this.plane));
        }
        PlaneHM newPlane = plane.react(ke);
        return new MeteorShowerHM(newPlane, this.meteorDataStructHM, newLasersHM, this.lives, this.score, this.missingMeteorsCounter, this.powerUps);
    }
    
    
    // ========== DRAW ==========
    // Draws the image on screen
     public WorldImage makeImage() {
//         WorldImage background = new OverlayImages(new RectangleImage(new Posn(0,0),this.width,this.height, new Blue()))
         return new OverlayImages(new RectangleImage(new Posn(0,0),2000,2000, new Blue()), this.plane.planeImage());
    }
    
    
    // This method produces an instance of a class WorldEnd that consists of a boolean value 
    // indicating whether the world is ending (false if the world goes on) and the WorldImage 
    // that represents the last image to be displayed - for example announcing the winner of the game, 
    // or the final score.
//    public WorldEnd worldEnds() {
//        
//    }

}
