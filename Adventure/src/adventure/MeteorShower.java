
package adventure;

import javalib.funworld.World;
import javalib.worldimages.Posn;
import javalib.worldimages.WorldEnd;
import javalib.worldimages.WorldImage;

/**
 *
 * @author kathrynhodge
 */
public class MeteorShower extends World {
    
    Lives lives;
    Plane plane;
    Meteor meteors;
    Boolean gameOver;
    Score score;
    
    // 0 means regular mode; 1 means hyper-speed mode
    int mode;
    static int REGULARMODE = 0;
    static int HYPERSPEEDMODE = 1;
    
    public MeteorShower() {
        this.plane = new Plane();
        this.meteors = new Meteor();
        this.lives = new Lives();
        this.score = new Score();
        this.gameOver = false;
        this.mode = REGULARMODE;
    }
  
    
    // REQUIRED METHODS BY GAMEWORLDS
    
    // To run the world game:
    public boolean bigBang(int width, int height, double speed) {
        
    }
    
    
    // This method produces a new instance of the world as it should be after one tick of the clock has passed.
    public World onTick() {
        
    }
    
    // This method produces the world in response to the user pressing a key on the keyboard. 
    public World onKeyEvent(String ke) {
        
    }
    
    // Draws the image on screen
     public WorldImage makeImage() {
         
    }
    
    // This method responds to a mouse click anywhere on the game’s canvas. 
    public World onMouseClicked(Posn p) {
     return this;   
    }
    
    
    // This method produces an instance of a class WorldEnd that consists of a boolean value 
    // indicating whether the world is ending (false if the world goes on) and the WorldImage 
    // that represents the last image to be displayed - for example announcing the winner of the game, 
    // or the final score.
    public WorldEnd worldEnds() {
        
    }



    
    
}
