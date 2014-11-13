
package adventure;

import javalib.colors.Black;
import javalib.colors.IColor;
import javalib.worldimages.CircleImage;
import javalib.worldimages.Posn;
import javalib.worldimages.WorldImage;


public class PlaneHM {
    int width;
    int deltaWidth;
    int height = 0;
    static int MAXW = 450;
    static int HYPER_MULTIPLE = 5;
    IColor color = new Black();
    
    static int middleOfScreenWidth = MAXW/2;
    int topOfScreen;
        
    public PlaneHM() {
       this(MAXW/2, -1);
    }
    
    public PlaneHM (int width, int deltaWidth) {
            this.width = width;
            this.deltaWidth = deltaWidth;
    }
    
    public boolean isEqualTo(PlaneHM otherPlane) {
        return (this.deltaWidth == otherPlane.deltaWidth) 
                && (this.height == otherPlane.height) 
                && (this.width == otherPlane.width);
    }
    
   public PlaneHM onTick(int multiple){
       int newWidth = width + (deltaWidth * multiple);
        if (newWidth < 0) {
            return new PlaneHM(0, deltaWidth);
        } else if (newWidth >= MAXW) {
            return new PlaneHM(MAXW, -deltaWidth);
        } else {
            return new PlaneHM(newWidth, deltaWidth);
        }
   }
   
   public PlaneHM react(String s) {
     if (s.equals("right")) {
             System.out.println("GOING RIGHT: ");
            return new PlaneHM(width, Math.abs(deltaWidth)).onTick(HYPER_MULTIPLE);            
         } 
        else if (s.equals("left")) {
            System.out.println("GOING LEFT: ");
            return new PlaneHM(width, -Math.abs(deltaWidth)).onTick(HYPER_MULTIPLE);
       }
     System.out.println("STAYING THE SAME");
     return this;
   }
    
    public WorldImage planeImage() {
        return new CircleImage(new Posn(this.width, this.height), 10, color);
//            return new FromFileImage(this.center, "Images/shark.png").
//          overlayImages(new CircleImage(this.center, this.radius, this.col));
    }
    
    
    
}
