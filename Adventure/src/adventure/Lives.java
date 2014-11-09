
package adventure;

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
}
