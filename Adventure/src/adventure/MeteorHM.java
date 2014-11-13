
package adventure;

import java.util.Random;

public class MeteorHM {
    int width;
    int deltaWidth = -1;
    int height;
    int identity;
    static int planeIntervals = 20;
    static int MAXH = 750;
    static int MAXW = 450;
    static int count = 0;
    static String color = "white";
    
    
    // Starting off-screen height, but at the right width position
    MeteorHM() {
        this(-500, 0, count);
        count++;
    }
    
    MeteorHM(int width, int height, int count) {
         this.width = width;
        // Using Sentinal h = -500
        if (height == -20) {
            Random random = new Random();
            this.height = planeIntervals * (Math.abs(random.nextInt()) + 1) % (400 / planeIntervals);
        } else 
            this.height = height;
        
        this.identity = count;
    }
    
    MeteorHM react() {
        return this;
    }
    
    MeteorHM onTick() {
        // Make it go up 1;
         return new MeteorHM(this.width, this.height - 1, this.identity);
    
    }
   
    
}


