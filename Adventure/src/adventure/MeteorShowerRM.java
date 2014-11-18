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
import javalib.worldimages.WorldEnd;
import javalib.worldimages.WorldImage;


public class MeteorShowerRM extends World{
    Lives lives;
    PlaneRM plane;
    Bag meteorDataStructRM;
    Bag lasersRM;
    Boolean gameOver;
    Score score;
    boolean powerUp;
    int correctShootCounter;
    
    
    // ========== CONSTRUCTORS ==========
    public MeteorShowerRM() {
        super();
        this.plane = new PlaneRM();
        this.meteorDataStructRM = empty();
        this.lasersRM = empty();
        this.lives = new Lives();
        this.score = new Score();
        this.gameOver = false;
        this.powerUp = false;
        this.correctShootCounter = 0;
    }
    
    public MeteorShowerRM(PlaneRM plane, Bag<MeteorRM>meteors, Bag lasers, Lives lives, Score score, boolean gameOver, boolean powerUp, int shootCounter){
        super();
        this.plane = plane;
        this.meteorDataStructRM = meteors;
        this.lasersRM = lasers;
        this.lives = lives;
        this.score = score;
        this.gameOver = gameOver;
        this.powerUp = powerUp;
        this.correctShootCounter = shootCounter;
    }

   // ========== CREATE GAME ==========
    public boolean bigBang() {
        return this.bigBang(500, 500, 1);
    }
    
    
   // ========== TICK ==========
    public World onTick() {
        // Need to tick the meteors
        Bag newMeteors = this.meteorDataStructRM.tickMeteors();
        // Need to tick the lasers
        Bag newLasers = this.lasersRM.tickLasers();
        // Need to see if their was collision & need to update lives, score, gameover
        return this;
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
            // Do we need to call onTick to make this all update? or will that happen automatically?
            PlaneRM newPlane = plane.react(ke);
            /* no need for meteors to react b/c independent of user */
            /* something that adds a laser */
            Bag newLasersRM = this.lasersRM.add(new LaserRM(this.plane));
            return new MeteorShowerRM(newPlane, this.meteorDataStructRM, newLasersRM, this.lives, this.score, 
                    this.gameOver, this.powerUp, this.correctShootCounter);
        }
    }
    
    
    // ========== GOING HYPER ==========
    public MeteorShowerHM goHyper() {
       return new MeteorShowerHM(new PlaneHM(), empty(), empty(), this.lives, this.score, this.gameOver);
    }
    
    public boolean hasPowerUp() {
        return this.powerUp;
    }
    
  
    
    
    // ========== DRAWING IMAGE ==========
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
//    public WorldEnd worldEnds(){
//      return new WorldEnd(true, 
//        this.makeImage());
//  }
  


    
    
}
