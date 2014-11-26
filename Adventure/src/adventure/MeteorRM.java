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
    int multiple =  1;
    int width;
    int identity;
    int leavingHeight = 430;
    static int radius = 20;
    static int MAXH = 430;
    static int MAXW = 450;
    static int count = 0;
    // Plane goes between 60 and 450
    
    // ========== CONSTRUCTORS ==========
    // Starting at a given plane's width, but offscreen
    MeteorRM(PlaneRM p) {
        this(p.width, -20, count, "none");
        count++;
    }
    
    MeteorRM(int width, int height, int count, String color) {
        // Using Sentinal h = -20 --> we know it's starting
        Random rand = new Random();
        if (height == -20) {
               int delta = rand.nextInt() % 10;
               this.width = width + (delta * 30);
               if (this.width >= 451) {
                   this.width = 450;
               }
               if (this.width <= 60) {
                   this.width = 60;
               }
            
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
    
    
     
    public int getRadius() {
        return this.radius;
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
         if (this.distance(thing) <= (this.getRadius() + thing.getRadius())) {
            return this;
        } else if (thing instanceof PlaneRM && this.getHeight() >= thing.getHeight()) {
            return this;
        } else 
            // HATE null....
            return null;
        }
   
    
    public WorldImage meteorImage() {
        if (this.color.equals("red")) {
            return new FromFileImage(new Posn(this.width, this.height), "Red-Meteor.png");
        } else {
           return new FromFileImage(new Posn(this.width, this.height), "Blue-Meteor.png");
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
        return this.getHeight() >= leavingHeight;
    }
    
    
    
    
   
    
}
