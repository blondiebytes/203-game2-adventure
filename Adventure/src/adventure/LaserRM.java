
package adventure;

import java.util.Random;
import javalib.worldimages.FromFileImage;
import javalib.worldimages.Posn;
import javalib.worldimages.WorldImage;


public class LaserRM implements Comparable<LaserRM>, Collideable<LaserRM>, Tickable<LaserRM> {
    // ----------------------------------------------------------------
    // LASER TESTING:
    // REGUALR MODE: After pressing ENTER, do the future lasers switch color?
    // After pressing the SPACEBAR, does the plane shoot an laser?
    // Is the laser removed once off screen?
    
    // PROBLEM: How to store these? In a bag? So more than one laser can 
    // be on screen? Also, how to track once off-screen? --> check if height too high
    
    
    String color;
    int width;
    int height;
    int deltaHeight = -10;
    int identity;
    int leavingHeight = 200;
    static int count = 0;
    
    // ========== CONSTRUCTORS ==========
    
    
    // Two ways lasers 
    LaserRM(PlaneRM plane) {
        this.width = plane.width-3;
        this.height = plane.height - 120;
        Random rnd = new Random();
        this.color = plane.colorLaser;
        this.identity = count;
        count++;
    }
    
    LaserRM(int width, int height, String color) {
        this.width = width;
        this.height = height;
        this.color = color;
    }
    
    LaserRM(int width, int height, String color, int identity) {
        this.width = width;
        this.height = height;
        this.color = color;
    }
    
     public int getWidth() {
         return this.width;
     }
     
     public int getHeight() {
         return this.height;
     }
     
    
    // ========== REACT ==========
    public LaserRM react(String se) {
        if (se.equals("d")) {
            if (color.equals("red")) {
                return new LaserRM(this.width, this.height, "blue", this.identity);
            } else {
                return new LaserRM(this.width, this.height, "red", this.identity);
            }
        } else {
            return this;
        }
    }
    
    
    // ========== TICK ==========
    public LaserRM onTick() {
        System.out.println("tickinglaser");
        return new LaserRM(this.width, this.height + deltaHeight, this.color, this.identity);
    }
    
    // ========== EQUALITY ==========
     public boolean isEqualToId(LaserRM otherLaser) {
        return this.identity == otherLaser.identity;
    }
    
     
    
     // ========== COMPARETO ==========
    public int compareTo(LaserRM otherLaser) {
        if (this.isEqualToId(otherLaser)) {
            return 0;
        } else if (this.identity < otherLaser.identity) {
            return 1;
        } else {
            return -1;
        }
    }

    // ========== COLLISION ==========
    public LaserRM collidesWith(Collideable thing) {
      if (this.height == thing.getHeight()) {
            return this;
        } else {
            // I HATE NULL;
            return null;
        }    
    }
    
    public boolean aboutToLeave() {
       return this.height == leavingHeight;
    }
    
    // ========== DRAW ==========
    public WorldImage laserImage() {
        if (color.equals("red")) {
            return new FromFileImage(new Posn(this.width, this.height), "Red_Laser.png");
        } else 
           return new FromFileImage(new Posn(this.width, this.height), "Blue_Laser.png");
    }
    
    
}
