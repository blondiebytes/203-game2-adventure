package adventure;

import javalib.colors.Black;
import javalib.colors.IColor;
import javalib.worldimages.CircleImage;
import javalib.worldimages.Posn;
import javalib.worldimages.WorldImage;

public class PlaneHM {

    int height = MAXH/2;
    int deltaHeight = -1;
    int width = MAXW / 2;
    static int MAXH = 750;
    static int MAXW = 450;
    static int HYPER_MULTIPLE = 5;
    IColor color = new Black();

    static int middleOfScreenWidth = MAXW / 2;
    int topOfScreen;

    public PlaneHM() {
        this(MAXW / 2, -1);
    }

    public PlaneHM(int height, int deltaHeight) {
        this.height = height;
        this.deltaHeight = deltaHeight;
    }

    public boolean isEqualTo(PlaneHM otherPlane) {
        return (this.deltaHeight == otherPlane.deltaHeight)
                && (this.height == otherPlane.height)
                && (this.width == otherPlane.width);
    }

    public PlaneHM onTick(int multiple) {
        int newHeight = height + (deltaHeight * multiple);
        if (newHeight < 0) {
            return new PlaneHM(0, deltaHeight);
        } else if (newHeight >= MAXH) {
            return new PlaneHM(MAXH, -deltaHeight);
        } else {
            return new PlaneHM(newHeight, deltaHeight);
        }
    }

    public PlaneHM react(String s) {
        // Also have the plane switching sides -> 
        if (s.equals("right")) {
            //switch plane image
        }
        if (s.equals("left")) {
            // switch plane image
        }
        
        if (s.equals("up")) {
                    System.out.println("GOING UP: ");
            return new PlaneHM(height, Math.abs(deltaHeight)).onTick(HYPER_MULTIPLE);
        } else if (s.equals("down")) {
              System.out.println("GOING DOWN: ");
            return new PlaneHM(height, -Math.abs(deltaHeight)).onTick(HYPER_MULTIPLE);
        } else {
               System.out.println("STAYING THE SAME");
            return this;
        }
    }

    public WorldImage planeImage() {
        return new CircleImage(new Posn(this.width, this.height), 10, color);
//            return new FromFileImage(this.center, "Images/shark.png").
//          overlayImages(new CircleImage(this.center, this.radius, this.col));
    }

}