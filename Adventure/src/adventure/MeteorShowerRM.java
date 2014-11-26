/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventure;

import adventure.Sequence.Sequence;
import adventure.SetBag.Bag;
import static adventure.SetBag.SetBag_NonEmpty.empty;
import javalib.colors.White;
import javalib.funworld.World;
import javalib.worldimages.FromFileImage;
import javalib.worldimages.OverlayImages;
import javalib.worldimages.Posn;
import javalib.worldimages.TextImage;
import javalib.worldimages.WorldImage;

public class MeteorShowerRM extends World {
    
// ============= TO DO STILL ============= 
// 1. Explosions --> they don't show up always
// 2. Translate all of this to the hyper mode so that it works too
// 3. Add powerUp Symbols
// 4. Create a start and end screen
// =======================================

    Lives lives;
    PlaneRM plane;
    Bag<MeteorRM> meteorDataStructRM;
    Bag<LaserRM> lasersRM;
    Bag<Explosion> explosionsRM;
    Boolean gameOver;
    Score score;
    int powerUp;
    int correctShootCounter;
    static int counterMeteor;
    static int changeBackgroundCounter = 0;

    // ========== CONSTRUCTORS ==========
    public MeteorShowerRM() {
        super();
        this.plane = new PlaneRM();
        this.meteorDataStructRM = empty();
        this.lasersRM = empty();
        this.explosionsRM = empty();
        this.lives = new Lives();
        this.score = new Score();
        this.gameOver = false;
        this.powerUp = 0;
        this.correctShootCounter = 0;
    }

    public MeteorShowerRM(PlaneRM plane, Bag<MeteorRM> meteors, Bag<LaserRM> lasers, 
            Bag<Explosion> explosions, Lives lives, Score score, boolean gameOver, 
            int powerUp, int shootCounter) {
        super();
        this.plane = plane;
        this.meteorDataStructRM = meteors;
        this.lasersRM = lasers;
        this.explosionsRM = explosions;
        this.lives = lives;
        this.score = score;
        this.gameOver = gameOver;
        this.powerUp = powerUp;
        this.correctShootCounter = shootCounter;
    }

    public MeteorShowerRM(PlaneRM plane, Bag<MeteorRM> meteors, Bag<LaserRM> lasers, 
            Bag<Explosion> explosions, Lives lives, Score score, boolean gameOver, int powerUp, 
            int shootCounter, int backgroundCounter) {
        super();
        this.plane = plane;
        this.meteorDataStructRM = meteors;
        this.lasersRM = lasers;
        this.explosionsRM = explosions;
        this.lives = lives;
        this.score = score;
        this.gameOver = gameOver;
        this.powerUp = powerUp;
        this.correctShootCounter = shootCounter;
        this.changeBackgroundCounter++;
    }

    // ========== CREATE GAME ==========
    public boolean bigBang() {
        return this.bigBang(500, 500, .01);
    }

    // ========== TICK ==========
    public World onTick() {
        Bag newMeteors = this.meteorDataStructRM;
        newMeteors = newMeteors.tick();

        // CREATING DIFFERENT LEVELS:
        
        if (counterMeteor < 5000) {
          if (counterMeteor % 85 == 0) {
            // Solves the problem of intervals
            newMeteors = (newMeteors.add(new MeteorRM(this.plane).onTick())); /* Need to tick the meteors & add a new one */

             }
        }

        if (counterMeteor < 100000 && counterMeteor >= 5000) {
           if (counterMeteor % 80 == 0) {
            // Solves the problem of intervals
            newMeteors = (newMeteors.add(new MeteorRM(this.plane).onTick())); /* Need to tick the meteors & add a new one */
            }
        }
       
        if (counterMeteor >= 10000) {
            if (counterMeteor % 75 == 0) {
            // Solves the problem of intervals
            newMeteors = (newMeteors.add(new MeteorRM(this.plane).onTick())); /* Need to tick the meteors & add a new one */
            } 
        }
        counterMeteor++;
        Bag newLasers = this.lasersRM.tick(); /* Need to tick the lasers */

        return new MeteorShowerRM(this.plane, newMeteors, newLasers, empty(), this.lives, this.score, this.gameOver, this.powerUp,
                this.correctShootCounter).update(); /* Need to see if their was collision & need to update lives, score, gameover */

    }

    // ========== COLLISION ==========
    // This checks for a collision and updates the lives, score, gameOver, powerUp, and correctShootCounter
    // Multiple Types of Collisions:
    // 1. Meteor passes the plane -->  plane same, lose life, score same, check for gameOver, powerUp same, correct shoot counter to 0,
    // 2. Laser hits Meteor --> plane same, lives same, ------, gameOver same, -------, --------
    //      A. Red laser hits Blue Meteor //  Blue laser hits Red Meteor --> score - 10, powerUp same, shoot counter to 0
    //      B. Red laser hits Red meteor //  Blue laser hits Blue Meteor --> score + 10, check if powerUp, shootcounter++
    // NEED TO ADD -> If off-screen, take it out!
    public MeteorShowerRM update() {
        PlaneRM newPlane = this.plane;
        Bag<MeteorRM> newMeteors = this.meteorDataStructRM;
        Bag<LaserRM> newLasers = this.lasersRM;
        Bag<Explosion> newExplosions = empty();
        Lives newLives = this.lives;
        Score newScore = this.score;
        boolean newGameOver = this.gameOver;
        int newPowerUp = this.powerUp;
        int newShootCounter = this.correctShootCounter;
        // 1. Meteor passes the plane -->  plane same, takes out colliding meteor, lose life, 
        // score same, check for gameOver, powerUp same, correct shoot counter to 0,
        MeteorRM collider = newMeteors.collidesWith(newPlane);
        if (collider != null  /* if a meteor passes the plane... */) {
            newMeteors = newMeteors.remove(collider); /* // PICK OUT THAT METEOR AND REMOVE IT !!!!!!!!!!! */
            newExplosions = newExplosions.add(new Explosion(collider.width, collider.height));
            newLives = newLives.subtractLife();
            if (newLives.gameOver()) {
                newGameOver = true;
            }
            newShootCounter = 0;
        }
        

        // 2. Laser hits Meteor --> 
        // plane same, takes out colliding meteor, takes out colliding laser, 
        // lives same, ------, gameOver same, -------, --------
        Sequence<LaserRM> seqLaser = newLasers.seq();
        while (seqLaser.hasNext()) {
            LaserRM collidingLaser = seqLaser.here();
            if (seqLaser.here().aboutToLeave()) {
                newLasers = newLasers.remove(seqLaser.here());
            } else {
                MeteorRM collidingMeteor = newMeteors.collidesWith(collidingLaser);
                if (collidingMeteor != null) {
                    System.out.println("hit meteor with laser!");
                    newMeteors = newMeteors.remove(collidingMeteor); /* remove that colliding meteor! */

                    newLasers = newLasers.remove(collidingLaser); /*remove that colliding laser! */
                    
                    newExplosions = newExplosions.add(new Explosion(collidingMeteor.width, collidingMeteor.height));
                    
                    //A. Red laser hits Red meteor //  Blue laser hits Blue Meteor --> score + 10, check if powerUp, shootcounter+1

                    if (collidingLaser.color.equals(collidingMeteor.color)) {
                        newScore = newScore.addScore();
                        if (this.getPowerUp()) {
                            newPowerUp = newPowerUp + 1;
                        }
                        newShootCounter = newShootCounter + 1;
                        System.out.println("hit same color");
                    } //B. Red laser hits Blue Meteor //  Blue laser hits Red Meteor --> score - 10, powerUp same, shoot counter to 0
                    else {
                        newScore = newScore.subtractScore();
                        newShootCounter = 0;
                        System.out.println("hit diff color");
                    }

                }
            }
            seqLaser = seqLaser.next();
        }
        return new MeteorShowerRM(newPlane, newMeteors, newLasers,
                newExplosions, newLives, newScore, newGameOver, newPowerUp, newShootCounter);
    }

    // ========== REACT ==========
    public World onKeyEvent(String ke) {
        if (ke.equals("0")) {
            if (this.hasPowerUp()) {
                return this.goHyper();
            } else {
                return this;
            }
        } else {
            PlaneRM newPlane = plane.react(ke);
            Bag<LaserRM> newLasersRM = this.lasersRM;
            /* no need for meteors to react b/c independent of user */
            if (ke.equals("s")) {
                newLasersRM = newLasersRM.add(new LaserRM(newPlane));  /* something that adds a laser */

            }
            return new MeteorShowerRM(newPlane, this.meteorDataStructRM, newLasersRM, this.explosionsRM, this.lives, this.score,
                    this.gameOver, this.powerUp, this.correctShootCounter);
        }
    }

    // ========== GOING HYPER ==========
    public MeteorShowerHM goHyper() {
        return new MeteorShowerHM(new PlaneHM(), empty(), empty(), empty(), this.lives, this.score, 0, this.powerUp);
    }

    public boolean hasPowerUp() {
        return this.powerUp > 0;
    }

    public boolean getPowerUp() {
        return this.correctShootCounter == 20;
    }

    // ========== DRAWING IMAGE ==========
    public WorldImage makeImage() {
        
        // Drawing Background
        WorldImage background;
        if (changeBackgroundCounter % 2 == 1) {
            background = new FromFileImage(new Posn(0, 0), "background-fire.jpg");
        } else {
            background = new FromFileImage(new Posn(0, 0), "background-stars.jpg");
        }
        
        // Drawing Plane
        WorldImage finalImage = new OverlayImages(background, this.plane.planeImage());

        
        // Drawing Meteors
        Sequence<MeteorRM> seqMeteor = this.meteorDataStructRM.seq();
        while (seqMeteor.hasNext()) {
            finalImage = new OverlayImages(finalImage, seqMeteor.here().meteorImage());
            seqMeteor = seqMeteor.next();
        }
        

        // Drawing Lasers
        Sequence<LaserRM> seqLaser = this.lasersRM.seq();
        while (seqLaser.hasNext()) {
            finalImage = new OverlayImages(finalImage, seqLaser.here().laserImage());
            seqLaser = seqLaser.next();
        }

        // Drawing Explosions
        Sequence<Explosion> seqExplosion = this.explosionsRM.seq();
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
}

    // This method produces an instance of a class WorldEnd that consists of a boolean value
// indicating whether the world is ending (false if the world goes on) and the WorldImage
// that represents the last image to be displayed - for example announcing the winner of the game,
// or the final score.
//    public WorldEnd worldEnds() {
//
//    }
//    public WorldEnd worldEnds(){
//      return new WorldEnd(true,
//        this.makeImage());
//  }

