/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventure;

import java.awt.Color;
import java.util.Random;
import javalib.colors.Black;
import javalib.colors.IColor;
import javalib.worldimages.FromFileImage;
import javalib.worldimages.Posn;
import javalib.worldimages.WorldImage;

public class MeteorRM implements Comparable<MeteorRM>, Collideable<MeteorRM>, Tickable<MeteorRM> {
    String color;
    int height = -10;
    int deltaHeight = 1;
    int multiple =  25;
    int width;
    int identity;
    int leavingHeight = 0;
    static int MAXH = 500;
    static int MAXW = 450;
    static int count = 0;
    static int positionChanger = 0;
    // Plane goes between 50 and 430
    
    // ========== CONSTRUCTORS ==========
// Starting off-screen width, but at the right height position
    MeteorRM() {
        this(100, -20, count, "none");
        count++;
    }
    
    // Starting at a given plane's width, but offscreen
    MeteorRM(PlaneRM p) {
        this(p.width, -20, count, "none");
        count++;
        positionChanger++;
    }
    
    MeteorRM(int width, int height, int count, String color) {
        // Using Sentinal h = -20 --> we know it's starting
        if (height == -20) {
           if (positionChanger % 2 == 1) {
               this.width = width + 50;
           } else {
               this.width = width - 50;
           }
           this.height = 0;
        } else {
            this.height = height;
            this.width = width;
        }
        
        // Set the color
        if (color.equals("none")) {
            Random random = new Random();
            int colorChoice = Math.abs(random.nextInt()) % 2;
            if (colorChoice == 0) {
                this.color = "red";
            } else {
                this.color = "blue";
            }
        } else 
            this.color = color;
        
        this.identity = count;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    
    // ========== REACT ==========
    public MeteorRM react(String se) {
        return this;
    }
    
    
    
    // ========== TICK ==========
    public MeteorRM onTick() {
        // Make it go up 1;
//        System.out.println("Tick.Tick. Meteor." + this.width + " H: " + this.height);
         return new MeteorRM( this.width, this.height + multiple, this.identity, this.color);
    }
   
    
    
    // ========== EQUALITY ==========
    public boolean isEqualToDWHC(MeteorRM otherMeteor) {
        return (this.width == otherMeteor.width) && 
                (this.height == otherMeteor.height) && 
                (this.deltaHeight == otherMeteor.deltaHeight) &&
                (this.color.equals(otherMeteor.color));
    }
    
    public boolean isEqualToId(MeteorRM otherMeteor) {
        return this.identity == otherMeteor.identity;
    }
   
    
    
    // ========== COMPARETO ==========
    public int compareTo(MeteorRM otherMeteor) {
        if (this.isEqualToId(otherMeteor)) {
            return 0;
        } else if (this.identity < otherMeteor.identity) {
            return 1;
        } else {
            return -1;
        }
    }
    
    // ========== COLLISIONS ========== 
    public MeteorRM collidesWith(Collideable thing) {
        System.out.println("printing Colliding " + this.height + " with " + thing.getHeight());
        
        // x and y are centers
        // distance(my x, my y, thing x, thing y) <= (my radius + thing radius)
        
        // Collidable Things Have:
        // getCenter(); distance(); getRadius();
        
        if ( this.height > thing.getHeight() ) {
            return this;
        } else {
            return null;
        }
        
        //for (int i = 0; i > 10; i++) {
        //    if (this.height + i == thing.getHeight() || this.height - i == thing.getHeight()) {
        //        return this;
        //    }
        //}
            // I HATE NULL;
        //    return null;
        }     
   
    
    public WorldImage meteorImage() {
        if (this.color.equals("red")) {
            return new FromFileImage(new Posn(this.width, this.height), "Red-Meteor.png");
        } else {
           return new FromFileImage(new Posn(this.width, this.height), "Blue-Meteor.png");
        }
    }
    
    
    
    
   
    
}
