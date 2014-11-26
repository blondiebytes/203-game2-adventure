package adventure;

import javafx.geometry.Pos;
import javalib.colors.Black;
import javalib.colors.IColor;
import javalib.colors.Yellow;
import javalib.worldimages.CircleImage;
import javalib.worldimages.FromFileImage;
import javalib.worldimages.Posn;
import javalib.worldimages.WorldImage;

// TOADD: Add WorldImage plane property that's static b/c no change in Regular Mode
public class PlaneRM implements Collideable<PlaneRM> {

    int width;
    int deltaWidth;
    int height = MAXH;
    static int MAXH = 430;
    static int MAXW = 430  ;
    static int REGULAR_MULTIPLE = 30;
    static int radius = 30;
    int center = width;
    IColor color = new Black();
    String colorLaser = "red";

    static int middleOfScreenWidth = 250;
    int topOfScreen;

    // ========== CONSTRUCTORS ==========
    PlaneRM() {
        this(middleOfScreenWidth, -1, "red");
    }

    private PlaneRM(int width, int deltaWidth, String color) {
        this.width = width;
        this.deltaWidth = deltaWidth;
        this.colorLaser = color;
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

    // ========== TICK ==========
    public PlaneRM onTick(int multiple) {
        int newWidth = width + deltaWidth * multiple;
        if (newWidth < 50) {
            return new PlaneRM(50, deltaWidth, colorLaser);
        } else if (newWidth >= MAXW) {
            return new PlaneRM(MAXW, -deltaWidth, colorLaser);
        } else {
            return new PlaneRM(newWidth, deltaWidth, colorLaser);
        }
    }

    // ========== REACT ==========
    public PlaneRM react(String s) {
        switch (s) {
            case "right":
//                       System.out.println("GOING RIGHT: ");
                return new PlaneRM(width, Math.abs(deltaWidth), colorLaser).onTick(REGULAR_MULTIPLE);
            case "left":
//                        System.out.println("GOING LEFT: ");
                return new PlaneRM(width, -Math.abs(deltaWidth), colorLaser).onTick(REGULAR_MULTIPLE);
            case "d":
                if (this.colorLaser.equals("red")) {
                    return new PlaneRM(width, deltaWidth, "blue");
                }
                    return new PlaneRM(width, deltaWidth, "red");
        }
        // System.out.println("STAYING THE SAME");
        return this;
    }

    // ========== DRAW ==========
    public WorldImage planeImage() {
       // return new CircleImage(new Posn(this.width, this.height), 10, color);
        if (colorLaser.equals("red")) {
           return new FromFileImage(new Posn(this.width, this.height), "fighter-01-red.png");
        } else {
            return new FromFileImage(new Posn (this.width, this.height), "fighter-01-blue.png");
        }
    }

    // ========== EQUALITY ==========
    public boolean isEqualTo(PlaneRM otherPlane) {
        return (this.deltaWidth == otherPlane.deltaWidth)
                && (this.height == otherPlane.height)
                && (this.width == otherPlane.width);
    }

    public PlaneRM collidesWith(Collideable thing) {
        System.out.println("Plane Height: " + this.getHeight() + "Thing's Height" + thing.getHeight());
     if (this.distance(thing) <= (this.getRadius() + thing.getRadius())) {
            return this;
        } if (this.getHeight() <= thing.getHeight()) {
            return this; 
          }  else {
            return null;
        }
        //    HATE null;
    }
   
    
    public int distance(Collideable thing) {
        return (int) Math.sqrt(
                (this.getWidth() - thing.getWidth()) 
                        * (this.getWidth() - thing.getWidth())
                + (this.getHeight() - thing.getHeight()) 
                        * (this.getHeight() - thing.getHeight()));

    }

}
