
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
    int deltaHeight = -5;
    int identity;
    static int radius = 5;
    int leavingHeight = 0;
    static int count = 0;
    
    // ========== CONSTRUCTORS ==========
    
    
    // Two ways lasers 
    LaserRM(PlaneRM plane) {
        this.width = plane.width + 1;
        this.height = plane.height - 50;
        Random rnd = new Random();
        this.color = plane.colorLaser;
        this.identity = count;
        count++;
    }
    
    LaserRM(int width, int height, String color, int identity) {
        this.width = width;
        this.height = height;
        this.color = color;
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
        if (this.distance(thing) <= (this.getRadius() + thing.getRadius())) {
            return this;
        } else {
            return null;
        }
    }
    
    public boolean aboutToLeave() {
       return this.height < leavingHeight;
    }
    
    // ========== DRAW ==========
    public WorldImage laserImage() {
        if (color.equals("red")) {
            return new FromFileImage(new Posn(this.width, this.height), "Red_Laser.png");
        } else 
           return new FromFileImage(new Posn(this.width, this.height), "Blue_Laser.png");
    }
    
    public int distance(Collideable thing) {
        return (int) Math.sqrt(
                (this.getWidth() - thing.getWidth()) 
                        * (this.getWidth() - thing.getWidth())
                + (this.getHeight() - thing.getHeight()) 
                        * (this.getHeight() - thing.getHeight()));

    }

    @Override
    public LaserRM onTick(PlaneHM p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
