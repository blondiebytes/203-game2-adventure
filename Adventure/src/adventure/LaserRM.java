
package adventure;


public class LaserRM {
    // ----------------------------------------------------------------
    // LASER TESTING:
    // REGUALR MODE: After pressing R, do the lasers turn red?
    // REGUALR MODE: After pressing B, do the lasers turn blue?
    // After pressing the SPACEBAR, does the plane shoot an laser?
    
    String color;
    int width;
    int height;
    int deltaHeight = 1;
    
    LaserRM(PlaneRM plane) {
        this.width = plane.width;
        this.height = plane.height;
    }
    
    
    
    
}
