
package adventure;

import java.util.Random;

public class MeteorHM implements Comparable<MeteorHM> {
    int width;
    int deltaWidth;
    int height;
    int identity;
    static int planeIntervals = 20;
    static int MAXH = 750;
    static int MAXW = 450;
    static int count = 0;
    static String color = "white";
    
    // ========== CONSTRUCTORS ==========
    // Starting from off screen height
    MeteorHM() {
        this(0, -20, count);
        count++;
    }
    
    // Haave them start from each side -> make starting width randomized (from each end)
    MeteorHM(int width, int height, int count) {
        // Using Sentinal h = -20
        if (height == -20) {
            Random random = new Random();
            this.height = planeIntervals * (Math.abs(random.nextInt()) + 1) % (400 / planeIntervals);
            if (random.nextInt() % 2 == 0) {
                // If you start from the left, you want to go right (so add 1 each time)
                this.width = 0;
                this.deltaWidth = 1;
            } else {
                // If you start from the right, you want to go left (so subtract 1 each time)
                this.width = MAXW;
                this.deltaWidth = -1;
            }
        } else {
            this.height = height;
            this.width = width;
        }
        this.identity = count;
    }
    
    // ========== REACT ==========
    public MeteorHM react(String se) {
        return this;
    }
    
    // ========== TICK ==========
    // Height is ranomly 
    public MeteorHM onTick() {
        // Make it across one
         return new MeteorHM(this.width + deltaWidth, this.height, this.identity);
    
    }
    
    // ========== EQUALITY ==========
    public boolean isEqualToDWHC(MeteorHM otherMeteor) {
        return (this.width == otherMeteor.width) && 
                (this.height == otherMeteor.height) &&
                (this.color.equals(otherMeteor.color));
    }
    
    public boolean isEqualToId(MeteorHM otherMeteor) {
        return this.identity == otherMeteor.identity;
    }
    
    
    // ========== COMPARETO ==========
    public int compareTo(MeteorHM otherMeteor) {
        if (this.isEqualToId(otherMeteor)) {
            return 0;
        } else if (this.identity < otherMeteor.identity) {
            return 1;
        } else {
            return -1;
        }
    }
   
    
}


