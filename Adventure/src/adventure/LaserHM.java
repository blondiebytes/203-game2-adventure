
package adventure;

import javalib.worldimages.FromFileImage;
import javalib.worldimages.Posn;
import javalib.worldimages.WorldImage;


public class LaserHM implements Comparable<LaserHM>, Collideable<LaserHM>, Tickable<LaserHM>{
    // ----------------------------------------------------------------
    // LASER TESTING:
    // After pressing the SPACEBAR, does the plane shoot an laser?
    // HYPERSPEED MODE: After pressing ENTER, do the lasers stay the 
    // same color?
    // Is the laser removed once off screen?
    
    String color = "white";
    int width;
    int height;
    int deltaWidth = 1;
    int identity;
    int count = 0;
    int leavingWidth = PlaneHM.middleOfScreenWidth;
    static int radius = 5;
    
    
    // ========== CONSTRUCTORS ==========
    LaserHM(PlaneHM plane) {
        this.height = plane.height;
        // something where if plane is this direction have this delta, otherwise other delta 
        this.identity = count;
        // Oposition of meteors -- same direction as plane
        if (plane.direction.equals("left")) {
            deltaWidth = -1;
        } else {
            deltaWidth = 1;
        }
        this.width = plane.width + (deltaWidth * 5);
        count++;
    }
    
    LaserHM(int width, int deltaWidth, int height, int identity) {
        this.width = width;
        this.deltaWidth = deltaWidth;
        this.height = height;
        this.identity = identity;
    }
    
    public int getWidth() {
         return this.width;
     }
     
     public int getHeight() {
         return this.height;
     }
    
     public int getRadius() {
        return this.radius;
    }
     
    
    // ========== REACT ==========
    public LaserHM react(String se) {
        //done in game logic
        return this;
    }
    
    
    
    // ========== TICK ==========
     public LaserHM onTick() {
        return new LaserHM(this.width + deltaWidth, this.deltaWidth, this.height, this.identity);
    }
    
     // ========== EQUALITY ==========
     public boolean isEqualToId(LaserHM otherLaser) {
        return this.identity == otherLaser.identity;
    }
    
     
    
     // ========== COMPARETO ==========
    public int compareTo(LaserHM otherLaser) {
        if (this.isEqualToId(otherLaser)) {
            return 0;
        } else if (this.identity < otherLaser.identity) {
            return 1;
        } else {
            return -1;
        }
    }
    
     // ========== COLLISION ==========
    public LaserHM collidesWith(Collideable thing) {
        if (this.distance(thing) <= (this.getRadius() + thing.getRadius())) {
            return this;
        } else {
            return null;
        }
    }
    
    public int distance(Collideable thing) {
        return (int) Math.sqrt(
                (this.getWidth() - thing.getWidth()) 
                        * (this.getWidth() - thing.getWidth())
                + (this.getHeight() - thing.getHeight()) 
                        * (this.getHeight() - thing.getHeight()));

    }
    
    public boolean aboutToLeave() {
       return this.width == leavingWidth;
    }
    
    // ========== DRAW ==========
    
    public WorldImage laserImage() {
        return new FromFileImage(new Posn(this.width, this.height), "White_Laser.png");
    }
    
    
    
}
