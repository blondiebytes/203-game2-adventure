package adventure;

import javalib.colors.Black;
import javalib.colors.IColor;
import javalib.worldimages.CircleImage;
import javalib.worldimages.Posn;
import javalib.worldimages.WorldImage;

public class PlaneHM implements Collideable<PlaneHM> {

    int height;
    int deltaHeight;
    int width = MAXW / 2;
    String direction;
    static int MAXH = 750;
    static int MAXW = 450;
    static int HYPER_MULTIPLE = 25;
    IColor color = new Black();

    static int middleOfScreenWidth = MAXW / 2;
    int topOfScreen;

    // ========== CONSTRUCTORS ==========
    public PlaneHM() {
        this(MAXH / 2, -1, "left");
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

    // ========== REACT ==========
    public PlaneHM onTick(int multiple) {
        int newHeight = height + (deltaHeight * multiple);
        if (newHeight < 0) {
            return new PlaneHM(0, deltaHeight, direction);
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
                return new PlaneHM(height, Math.abs(deltaHeight), this.direction).onTick(HYPER_MULTIPLE);
            case "down":
                //    System.out.println("GOING DOWN: ");
                return new PlaneHM(height, -Math.abs(deltaHeight), this.direction).onTick(HYPER_MULTIPLE);
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
        return new CircleImage(new Posn(this.width, this.height), 10, color);
//            return new FromFileImage(this.center, "Images/shark.png").
//          overlayImages(new CircleImage(this.center, this.radius, this.col));
    }

    // ========== EQUALITY ==========
    public boolean isEqualTo(PlaneHM otherPlane) {
        return (this.deltaHeight == otherPlane.deltaHeight)
                && (this.height == otherPlane.height)
                && (this.width == otherPlane.width)
                && (this.direction == otherPlane.direction);
    }
    
    
    // ========== COLLISIONS ==========
    public PlaneHM collidesWith(Collideable thing) {
        if (this.height == thing.getHeight()) {
            return this;
        } else {
            // I HATE NULL;
            return null;
        }
    }

}
