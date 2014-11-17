
package adventure;


public class LaserHM {
    // ----------------------------------------------------------------
    // LASER TESTING:
    // After pressing the SPACEBAR, does the plane shoot an laser?
    // HYPERSPEED MODE: After pressing ENTER, do the lasers stay the 
    // same color?
    // Is the laser removed once off screen?
    
    String color = "white";
    int width;
    int height;
    int deltaWidth;
    
    LaserHM(PlaneHM plane) {
        this.width = plane.width;
        this.height = plane.height;
    }
    
    public void react(String se) {
        if (se.equals("spacebar")) {
            //shoot laser
        }
    }
    
     public LaserRM onTick() {
        return new LaserRM(this.width + deltaWidth, this.height, this.color);
    }
    
    
    
    
    
    
}
