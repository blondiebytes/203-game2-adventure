
package adventure;

import javalib.worldimages.FromFileImage;
import javalib.worldimages.Posn;
import javalib.worldimages.WorldImage;

/**
 *
 * @author kathrynhodge
 */
public class Lives {
        
    int life;
    
    public Lives() {
        life = 3;
    }
    
    public Lives(int life) {
        this.life = life;
    }
    
    public Lives subtractLife() {
        return new Lives(this.life - 1);
    }
    
    public boolean gameOver() {
        return this.life <= 0;
    }
   
    // NEED TO IMPLEMENT
    public void draw () {
    }
    
    public WorldImage livesImage(int width, int height) {
            return new FromFileImage(new Posn(width, height), "heart.png");
    }
}
