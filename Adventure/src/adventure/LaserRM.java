
package adventure;

import java.util.Random;


public class LaserRM {
    // ----------------------------------------------------------------
    // LASER TESTING:
    // REGUALR MODE: After pressing ENTER, do the future lasers switch color?
    // After pressing the SPACEBAR, does the plane shoot an laser?
    
    // PROBLEM: How to store these? In a bag? So more than one laser can 
    // be on screen? Also, how to track once off-screen
    
    String color;
    int width;
    int height;
    int deltaHeight = 1;
    
    LaserRM(PlaneRM plane) {
        this.width = plane.width;
        this.height = plane.height;
        Random rnd = new Random();
        if (rnd.nextInt() % 2 == 1) {
            this.color = "red";
        } else {
            this.color = "blue";
        }
    }
    
    LaserRM(int width, int height) {
        this.width = width;
        this.height = height;
    }
    
    LaserRM(int width, int height, String color) {
        this.width = width;
        this.height = height;
        this.color = color;
    }
    
    public void react(String se) {
        if (se.equals("spacebar")) {
            //shoot laser
        }
        if (se.equals("enter")) {
            if (color.equals("red")) {
                color = "blue";
            } else {
                color = "red";
            }
        }
    }
    
    public LaserRM onTick() {
        return new LaserRM(this.width, this.height + deltaHeight, this.color);
    }
    
    
    
}
