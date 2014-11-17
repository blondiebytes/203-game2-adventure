/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author kathrynhodge
 */
public class MeteorShowerHM extends World {
    Lives lives;
    PlaneHM plane;
    Bag meteorDataStructHM;
    Bag lasersHM;
    Boolean gameOver;
    Score score;
// TOADD: Add WorldImage plane property
    
    public MeteorShowerHM() {
        super();
        this.plane = new PlaneHM();
        this.meteorDataStructHM = empty();
        this.lasersHM = empty();
        this.lives = new Lives();
        this.score = new Score();
        this.gameOver = false;
    }
    
    public MeteorShowerHM(PlaneHM plane, Bag meteors, Bag lasers, Lives lives, Score score, boolean gameOver){
        super();
        this.plane = plane;
        this.meteorDataStructHM = meteors;
        this.lasersHM = lasers;
        this.lives = lives;
        this.score = score;
        this.gameOver = gameOver;
    }
  
    
    // REQUIRED METHODS BY GAMEWORLDS
    
    // To run the world game:
    public boolean bigBang() {
        return this.bigBang(500, 500, 1);
    }
    
    
    // This method produces a new instance of the world as it should be after one tick of the clock has passed.
    public World onTick() {
        // Plane updated in its react
        // Do some type of collision function to figure out if a meteor is off screen
        // Also check if lasers are off-screen
        MeteorShowerHM updateLSGM = this.collisionLSGM();
        Bag newMeteorStruct = this.meteorDataStructHM.add(new MeteorHM());
        /*check for collision of laser and meteor and update score */
        return new MeteorShowerHM(this.plane, newMeteorStruct, updateLSGM.lasersHM, updateLSGM.lives, updateLSGM.score, updateLSGM.gameOver);
    }
    
    public MeteorShowerHM collisionLSGM() {
        PlaneHM newPlane = this.plane;
        Bag newMeteors = this.meteorDataStructHM;
        Bag newLasers = this.lasersHM;
        Lives newLives = this.lives;
        Score newScore = this.score;
        Boolean newGameOver = this.gameOver;
        Sequence<MeteorHM> seqMeteors = this.meteorDataStructHM.seq();
//        newMeteors = seqMeteors.moveAcross(); /* onTick all meteors */
//        if ( /*check if any meteors are at the same width as spike */ ) {
//            /* if so, then lose a life */
//            /* check if all lives are lost */
//       }
        return new MeteorShowerHM(newPlane, newMeteors, newLasers, newLives, newScore, newGameOver);
    }
    
    
    // This method produces the world in response to the user pressing a key on the keyboard. 
    public World onKeyEvent(String ke) {
        // Also have the plane switching sides -> 
        if (ke.equals("right")) {
            //switch plane image
        }
        if (ke.equals("left")) {
            // switch plane image
        }
        PlaneHM newPlane = plane.react(ke);
        /* if spacebar pressed then, shoot laser */
        Bag newLasersRM = /* something that moves all the lasers laser.react(ke); */ null;
        return new MeteorShowerHM(newPlane, this.meteorDataStructHM, newLasersRM, this.lives, this.score, this.gameOver);
    }
    
    // Draws the image on screen
     public WorldImage makeImage() {
//         WorldImage background = new OverlayImages(new RectangleImage(new Posn(0,0),this.width,this.height, new Blue()))
         return new OverlayImages(new RectangleImage(new Posn(0,0),2000,2000, new Blue()), this.plane.planeImage());
    }
    
    // This method responds to a mouse click anywhere on the game’s canvas. 
    public World onMouseClicked(Posn p) {
     return this;   
    }
    
    
    // This method produces an instance of a class WorldEnd that consists of a boolean value 
    // indicating whether the world is ending (false if the world goes on) and the WorldImage 
    // that represents the last image to be displayed - for example announcing the winner of the game, 
    // or the final score.
//    public WorldEnd worldEnds() {
//        
//    }

}
