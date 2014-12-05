package adventure;

import javalib.colors.Black;
import javalib.colors.IColor;
import javalib.worldimages.CircleImage;
import javalib.worldimages.FromFileImage;
import javalib.worldimages.Posn;
import javalib.worldimages.WorldImage;

public class PlaneHM implements Collideable<PlaneHM> {

    int height;
    int deltaHeight;
    int width = MAXW / 2;
    String direction;
    static int radius = 30;
    static int MAXH = 510;
    static int MAXW = 500;
    static int LEASTH = 60;
    static int HYPER_MULTIPLE = 30;
    IColor color = new Black();

    static int middleOfScreenWidth = 240;
    int topOfScreen;

    // ========== CONSTRUCTORS ==========
    public PlaneHM() {
        this(240, -1, "left");
    }

    public PlaneHM(int height, int deltaHeight, String direction) {
        this.height = height;
        this.deltaHeight = deltaHeight;
        this.direction = direction;
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
    public PlaneHM onTick(int multiple) {
        int newHeight = height + (deltaHeight * multiple);
        if (newHeight < LEASTH) {
            return new PlaneHM(LEASTH, deltaHeight, direction);
        } else if (newHeight >= MAXH) {
            return new PlaneHM(MAXH, -deltaHeight, direction);
        } else {
            return new PlaneHM(newHeight, deltaHeight, direction);
        }
    }

    // ========== REACT ==========
    public PlaneHM react(String s) {
        switch (s) {
            case "up":
                //     System.out.println("GOING UP: ");
                return new PlaneHM(height, -Math.abs(deltaHeight), this.direction).onTick(HYPER_MULTIPLE);
            case "down":
                //    System.out.println("GOING DOWN: ");
                return new PlaneHM(height, Math.abs(deltaHeight), this.direction).onTick(HYPER_MULTIPLE);
            case "left":
                    return new PlaneHM(height, deltaHeight, "left");
            case "right":
                return new PlaneHM(height, deltaHeight, "right");
            default:
                //    System.out.println("STAYING THE SAME");
                return this;
        }
    }

    // ========== DRAW ==========
    public WorldImage planeImage() {
        if (this.direction.equals("left")) {
            return new FromFileImage(new Posn(this.width, this.height), "fighter-01-white-left.png");
        } else {
            return new FromFileImage(new Posn(this.width, this.height), "fighter-01-white-right.png");
        }
    }

    // ========== EQUALITY ==========
    public boolean isEqualTo(PlaneHM otherPlane) {
        return (this.deltaHeight == otherPlane.deltaHeight)
                && (this.height == otherPlane.height)
                && (this.width == otherPlane.width)
                && (this.direction.equals(otherPlane.direction));
    }
    
    
    // ========== COLLISIONS ==========
    public PlaneHM collidesWith(Collideable thing) {
        if (this.distance(thing) <= (this.getRadius() + thing.getRadius())) {
            return this;
        } else {
            return null;
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
