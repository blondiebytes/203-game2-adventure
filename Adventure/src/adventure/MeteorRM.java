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
    int leavingHeight = 0;
    static int radius = 20;
    int center = width;
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
        Random rand = new Random();
        if (height == -20) {
            if (positionChanger % 4 == 0) {
               this.width = width + 60;
           } else if (positionChanger % 4 == 1) {
               this.width = width - 60;
           } else if (positionChanger % 4 == 2) {
               this.width = width - 120;
           } else {
               this.width = width + 120;
           }
            
               int delta = rand.nextInt() % 15;
               this.width = width + (delta * 30);
            
               if (this.width >= 431) {
                   this.width = 430;
               }
               if (this.width <= 0) {
                   this.width = 60;
               }
//           }
            
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
    
    public int getCenter() {
         return this.center;
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
        } else {
            return null;
        }
        //    HATE null;
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
    
    
    
    
   
    
}
