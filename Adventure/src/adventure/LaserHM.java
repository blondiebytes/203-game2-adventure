
package adventure;


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
    int deltaWidth;
    int identity;
    int count = 0;
    int leavingWidth = PlaneHM.middleOfScreenWidth;
    String direction = "left";
    
    
    // ========== CONSTRUCTORS ==========
    LaserHM(PlaneHM plane) {
        this.width = plane.width;
        this.height = plane.height;
        // something where if plane is this direction have this delta, otherwise other delta 
        this.identity = count;
        this.direction = plane.direction;
        if (this.direction.equals("left")) {
            deltaWidth = -1;
        } else {
            deltaWidth = 1;
        }
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
    
    // ========== COMPARETO ==========
    public LaserHM collidesWith(Collideable thing) {
      if (this.height == thing.getHeight()) {
            return this;
        } else {
            // I HATE NULL;
            return null;
        }    
    }
    
    public boolean aboutToLeave() {
       return this.width == leavingWidth;
    }
    
    
    
}
