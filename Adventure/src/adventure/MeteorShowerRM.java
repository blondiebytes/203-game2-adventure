/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventure;

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
    // TOADD: Add WorldImage plane property that's static b/c no change in Regular Mode
    
    // 0 means regular mode; 1 means hyper-speed mode
    int mode;
    static int REGULARMODE = 0;
    
    public MeteorShowerRM() {
        super();
        this.plane = new PlaneRM();
        this.meteorDataStructRM = empty();
        this.lasersRM = empty();
        this.lives = new Lives();
        this.score = new Score();
        this.gameOver = false;
        this.mode = REGULARMODE;
    }
    
    public MeteorShowerRM(PlaneRM plane, Bag<MeteorRM>meteors, Bag lasers, Lives lives, Score score, boolean gameOver){
        super();
        this.plane = plane;
        this.meteorDataStructRM = meteors;
        this.lasersRM = lasers;
        this.lives = lives;
        this.score = score;
        this.gameOver = gameOver;
        this.mode = REGULARMODE;
    }
  
    
    // REQUIRED METHODS BY GAMEWORLDS
    
    // To run the world game:
    public boolean bigBang() {
        return this.bigBang(500, 500, 1);
    }
    
    
//    // This method produces a new instance of the world as it should be after one tick of the clock has passed.
//    public World onTick() {
//        return this;
//    }
//    
    // This method produces the world in response to the user pressing a key on the keyboard. 
    public World onKeyEvent(String ke) {
        if (ke.equals("0")) {
          //  PlaneHM newPlane = plane.goHyper(); //new PlaneHM(plane.w);
            return new MeteorShowerHM(new PlaneHM(), empty(), empty(), this.lives, this.score, this.gameOver);
        } else {
            PlaneRM newPlane = plane.react(ke);
            /* no need for meteors to react b/c independent of user */
            Bag newLasersRM = /* something that moves all the lasers laser.react(ke); */ null;
            return new MeteorShowerRM(newPlane, this.meteorDataStructRM, this.lasersRM, this.lives, this.score, this.gameOver);
        }
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
//    public WorldEnd worldEnds(){
//      return new WorldEnd(true, 
//        this.makeImage());
//  }
  


    
    
}
