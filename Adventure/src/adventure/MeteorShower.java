
package adventure;

/**
 *
 * @author kathrynhodge
 */
public class MeteorShower extends TestMeteorShower{
    
    Lives lives;
    Score score;
    Plane plane;
    Meteor meteors;
    Boolean gameOver;
    
    // 0 means regular mode; 1 means hyper-speed mode
    int mode; 
    
    public MeteorShower() {
        this.plane = new Plane();
        this.meteors = new Meteor();
        this.lives = new Lives();
        this.score = new Score();
        this.gameOver = false;
    }
  
    
}
