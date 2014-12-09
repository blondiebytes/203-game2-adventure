
package adventure;

import adventure.Sequence.Sequence;
import adventure.SetBag.Bag;
import static adventure.SetBag.SetBag_NonEmpty.empty;
import javalib.colors.Black;
import javalib.colors.Blue;
import javalib.colors.White;
import javalib.funworld.World;
import javalib.worldimages.FromFileImage;
import javalib.worldimages.OverlayImages;
import javalib.worldimages.Posn;
import javalib.worldimages.RectangleImage;
import javalib.worldimages.TextImage;
import javalib.worldimages.WorldImage;

public class MeteorShowerHM extends World {
    Lives lives;
    PlaneHM plane;
    Bag<MeteorHM> meteorDataStructHM;
    Bag<LaserHM> lasersHM;
    Bag<Explosion> explosionsHM;
    Score score;
    int missingMeteorsCounter;
    int powerUps;
    static int background;
    static int releaseMeteor = 0;
    String key = "a";
    
    
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
    
    public MeteorShowerHM(PlaneHM plane, Bag<MeteorHM> meteors, Bag<LaserHM> lasers, Bag<Explosion> explosions, Lives lives, Score score, int missingMeteors, int powerUps, int background){
        super();
        this.plane = plane;
        this.meteorDataStructHM = meteors;
        this.lasersHM = lasers;
        this.explosionsHM = explosions;
        this.lives = lives;
        this.score = score;
        this.missingMeteorsCounter = missingMeteors;
        this.powerUps = powerUps;
        this.background = background;
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
        
          if (releaseMeteor % 30 == 0) {
            // Solves the problem of intervals
            newMeteors = (newMeteors.add(new MeteorHM(this.plane).onTick()));
            /* Need to tick the meteors & add a new one */
            
             }
        
          releaseMeteor++;
        
        Bag<LaserHM> newLasers = this.lasersHM.tick();
        Bag newExplosions = this.explosionsHM.tick();
        return new MeteorShowerHM(this.plane, newMeteors, newLasers, newExplosions,
                this.lives, this.score, this.missingMeteorsCounter, this.powerUps, this.background).update(); /*check for collision of laser and meteor and update score */
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
        int newCounter = this.missingMeteorsCounter;
        
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
            newCounter = newCounter + 1;
            if (this.backToRegularMode()) {
                return new MeteorShowerRM(new PlaneRM(), empty(), empty(), empty(), newLives, newScore, false, this.powerUps - 1, 0, 1);
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
        
        return new MeteorShowerHM(newPlane, newMeteors, newLasers, newExplosions, newLives, newScore, newCounter, this.powerUps, this.background);
    }
    
    public boolean backToRegularMode() {
        return this.missingMeteorsCounter >= 5;
    }
    
    
    
    // ========== REACT ==========
    // This method produces the world in response to the user pressing a key on the keyboard. 
    public World onKeyEvent(String ke) {
        this.key = ke;
         Bag<LaserHM> newLasersHM = this.lasersHM;
        if (ke.equals("s")) {
           newLasersHM = newLasersHM.add(new LaserHM(this.plane));
        }
        PlaneHM newPlane = plane.react(ke);
        return new MeteorShowerHM(newPlane, this.meteorDataStructHM, newLasersHM, this.explosionsHM, this.lives, this.score, this.missingMeteorsCounter, this.powerUps, this.background);
    }
    
    
    // ========== DRAW ==========
    // Draws the image on screen
     public WorldImage makeImage() {
         WorldImage background;
        if (this.background % 2 == 1) {
            background = new FromFileImage(new Posn(0, 0), "background-fire.jpg");
        } else {
            background = new FromFileImage(new Posn(0, 0), "background-stars.jpg");
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
        switch (lives.life) {
            case 1: finalImage = new OverlayImages(finalImage,lives.livesImage(30,60));
                break;
            case 2: finalImage = new OverlayImages(finalImage,lives.livesImage(30,60));
                    finalImage = new OverlayImages(finalImage,lives.livesImage(55,60));
                break;
            case 3: finalImage = new OverlayImages(finalImage,lives.livesImage(30,60));
                     finalImage = new OverlayImages(finalImage,lives.livesImage(55,60));
                      finalImage = new OverlayImages(finalImage,lives.livesImage(80,60));
        }
        
        return finalImage;
    }
    
    
    // This method produces an instance of a class WorldEnd that consists of a boolean value 
    // indicating whether the world is ending (false if the world goes on) and the WorldImage 
    // that represents the last image to be displayed - for example announcing the winner of the game, 
    // or the final score.
//    public WorldEnd worldEnds() {
//        
//    }

}
