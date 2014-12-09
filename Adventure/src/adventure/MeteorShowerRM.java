/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventure;

import adventure.Sequence.Sequence;
import adventure.SetBag.Bag;
import static adventure.SetBag.SetBag_NonEmpty.empty;
import static adventure.TestFunctions.testCollisionRegularMode;
import static adventure.TestFunctions.testConstructorRM;
import static adventure.TestFunctions.testGameOverLives;
import static adventure.TestFunctions.testLaserMeteorRemovedRM;
import static adventure.TestFunctions.testPowerUpHyperMode;
import static adventure.TestFunctions.testShootLaserRM;
import static adventure.TestFunctions.testTriggerHyperSpeedMode;
import static adventure.TestFunctions.verifyInvarientsRM;
import java.util.logging.Level;
import java.util.logging.Logger;
import javalib.colors.White;
import javalib.funworld.World;
import javalib.worldcanvas.WorldCanvas;
import javalib.worldimages.FromFileImage;
import javalib.worldimages.OverlayImages;
import javalib.worldimages.Posn;
import javalib.worldimages.TextImage;
import javalib.worldimages.WorldEnd;
import javalib.worldimages.WorldImage;

public final class MeteorShowerRM extends World {

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
    static int highScore;
    static String key = "a";
    static boolean shouldPrint = false;

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
            int shootCounter, int backgroundCounter) throws Exception {
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
        MeteorShowerRM.changeBackgroundCounter++;
    }

    // ========== CREATE GAME ==========
    public boolean bigBang() {
        
            //this.theCanvas = new WorldCanvas(500, 500, "Meteor Shower");
            //this.theCanvas.show();
        return this.bigBang(500, 500, .01);
    }

    // ========== TICK ==========
    public World onTick() {
        Bag newMeteors = this.meteorDataStructRM;
        newMeteors = newMeteors.tick();

        // CREATING DIFFERENT LEVELS:
        if (counterMeteor < 5000) {
            if (counterMeteor % 90 == 0) {
                // Solves the problem of intervals
                newMeteors = (newMeteors.add(new MeteorRM(this.plane).onTick())); /* Need to tick the meteors & add a new one */
            }
        }

        if (counterMeteor < 100000 && counterMeteor >= 5000) {
            if (counterMeteor % 80 == 0) {
                // Solves the problem of intervals
                newMeteors = (newMeteors.add(new MeteorRM(this.plane).onTick()));/* Need to tick the meteors & add a new one */
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

        Bag newExplosions = this.explosionsRM.tick();

        return new MeteorShowerRM(this.plane, newMeteors, newLasers, newExplosions, this.lives, this.score, this.gameOver, this.powerUp,
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
        Bag<Explosion> newExplosions = this.explosionsRM;
        Lives newLives = this.lives;
        Score newScore = this.score;
        boolean newGameOver = this.gameOver;
        int newPowerUp = this.powerUp;
        int newShootCounter = this.correctShootCounter;

        // Update Explosions
        Sequence<Explosion> seqExp = newExplosions.seq();
        if (seqExp.hasNext()) {
            if (seqExp.here().show <= 0) {
                newExplosions = newExplosions.remove(seqExp.here());
            }
            seqExp = seqExp.next();
        }

        // 1. Meteor passes the plane -->  plane same, takes out colliding meteor, lose life, 
        // score same, check for gameOver, powerUp same, correct shoot counter to 0,
        MeteorRM collider = newMeteors.collidesWith(newPlane);
        if (collider != null /* if a meteor passes the plane... */) {
            newMeteors = newMeteors.remove(collider); /* // PICK OUT THAT METEOR AND REMOVE IT !!!!!!!!!!! */

            newExplosions = newExplosions.add(new Explosion(collider.width, collider.height, true));
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
                    newMeteors = newMeteors.remove(collidingMeteor); /* remove that colliding meteor! */

                    newLasers = newLasers.remove(collidingLaser); /*remove that colliding laser! */

                    //A. Red laser hits Red meteor //  Blue laser hits Blue Meteor --> score + 10, check if powerUp, shootcounter+1

                    if (collidingLaser.color.equals(collidingMeteor.color)) {
                        newExplosions = newExplosions.add(new Explosion(collidingMeteor.width, collidingMeteor.height, true));
                        newScore = newScore.addScore();
                        if (this.getPowerUp()) {
                            newPowerUp = newPowerUp + 1;
                        }
                        newShootCounter = newShootCounter + 1;
                    } //B. Red laser hits Blue Meteor //  Blue laser hits Red Meteor --> score - 10, powerUp same, shoot counter to 0
                    else {
                        newExplosions = newExplosions.add(new Explosion(collidingMeteor.width, collidingMeteor.height, false));
                        newScore = newScore.subtractScore();
                        newShootCounter = 0;
                    }

                }
            }
            seqLaser = seqLaser.next();
        }
        return new MeteorShowerRM(newPlane, newMeteors, newLasers,
                newExplosions, newLives, newScore, newGameOver, newPowerUp, newShootCounter);
    }

    // ========== REACT ==
    public World onKeyEvent(String ke){
        this.checkInvarientsLive(ke);
        return REALonKeyEvent(ke);
    }
    
    public World REALonKeyEvent(String ke){
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
        return new MeteorShowerHM(new PlaneHM(), empty(), empty(), empty(), this.lives, this.score, 0, this.powerUp, changeBackgroundCounter, true);
    }
    
    public void checkInvarientsLive(String key) throws RuntimeException{
            if (!key.equals("0")) {
                MeteorShowerRM nG = (MeteorShowerRM) this.REALonKeyEvent(key).onTick();
                verifyInvarientsRM(this, nG, key);
                if (shouldPrint) {
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
                System.out.println();
                }
        }
    }

    public boolean hasPowerUp() {
        return this.powerUp > 0;
    }

    public boolean getPowerUp() {
        return this.correctShootCounter % 9 == 0 && correctShootCounter != 0;
    }

    public int showPowerUp() {
        return this.powerUp;
    }

    public WorldImage drawPowerUp(int width, int height) {
        return new FromFileImage(new Posn(width, height), "powerUp.png");
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
        finalImage = this.lives.draw(finalImage);

        // Drawing powerUP
        switch (showPowerUp()) {
            case 0:
                break;
            case 1:
                finalImage = new OverlayImages(finalImage, drawPowerUp(30, 85));
                break;
            case 2:
                finalImage = new OverlayImages(finalImage, drawPowerUp(30, 85));
                finalImage = new OverlayImages(finalImage, drawPowerUp(55, 85));
                break;
            case 3:
                finalImage = new OverlayImages(finalImage, drawPowerUp(30, 85));
                finalImage = new OverlayImages(finalImage, drawPowerUp(55, 85));
                finalImage = new OverlayImages(finalImage, drawPowerUp(80, 85));
        }
        
        return finalImage;
    }

    // This method produces an instance of a class WorldEnd that consists of a boolean value
// indicating whether the world is ending (false if the world goes on) and the WorldImage
// that represents the last image to be displayed - for example announcing the winner of the game,
// or the final score.
    public WorldEnd worldEnds() {
        if (gameOver) {
            WorldImage background;
            if (changeBackgroundCounter % 2 == 1) {
                background = new FromFileImage(new Posn(0, 0), "background-fire.jpg");
            } else {
                background = new FromFileImage(new Posn(0, 0), "background-stars.jpg");
            }

            WorldImage gameOverText = new OverlayImages(new TextImage(new Posn(235, 225), "Game Over!", 40, new White()),
                    new TextImage(new Posn(235, 275), "Score: " + this.score.score, 40, new White()));
            return new WorldEnd(true, new OverlayImages(background, gameOverText));

        } else {
            return new WorldEnd(false, this.makeImage());
        }
    }

}
