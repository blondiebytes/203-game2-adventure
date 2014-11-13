
package adventure;
        
import javafx.geometry.Pos;
import javalib.colors.IColor;
import javalib.colors.Yellow;
import javalib.worldimages.CircleImage;
import javalib.worldimages.Posn;
import javalib.worldimages.WorldImage;

        
public class Plane {
    
    int width;
    int deltaWidth;
    double angle;
    double deltaAngle;
    int height;
    static int MAXW;
    static int MAXANGLE = 180;
    IColor color = new Yellow();
    
    static int middleOfScreenWidth = MAXW/2;
    int topOfScreen;
    
    // 0 means regular mode; 1 means hyper-speed mode
    static int REGULARMODE = MeteorShower.REGULARMODE;
    static int HYPERSPEEDMODE = MeteorShower.HYPERSPEEDMODE;
    int mode = REGULARMODE;
    
    // =============================
    
    Plane() {
        this(MAXW/2, -1, REGULARMODE);
    }
    
    public Plane(int mode) {
            this(0, -30, mode);
    }
    
    private Plane (int width, int deltaWidth, int mode) {
        if (mode == REGULARMODE) {
            this.width = width;
            this.deltaWidth = deltaWidth;
            this.mode = mode;
        } 
    }
    
    private Plane (double angle, double deltaAngle, int mode)  {
        if (mode == HYPERSPEEDMODE) {
            this.angle = angle;
            this.deltaAngle = deltaAngle;
            this.mode = mode;
        } 
    }
   
   
  // REGULAR: MOVE RIGHT AND LEFT
   public Plane onTickRM(){
       int newWidth = width + deltaWidth;
        if (newWidth < 0) {
            return new Plane(0, deltaWidth, REGULARMODE);
        } else if (newWidth >= MAXW) {
            return new Plane(MAXW, -deltaWidth, REGULARMODE);
        } else {
            return new Plane(newWidth, deltaWidth, REGULARMODE);
        }
   }
   
   // ANGLED: ROTATE 30 DEGREES THAT THE WAY -> each rotation represented
   // by 1, 2, 3, 4, 5, 6,7 -> multiple by 30 in draw to get angle
   public Plane onTickHM(){
        double newAngle = angle + deltaAngle;
        if (newAngle < 0) {
            return new Plane(0, deltaAngle, HYPERSPEEDMODE);
        } else if (newAngle >= MAXANGLE) {
            return new Plane(MAXANGLE, -deltaAngle, HYPERSPEEDMODE);
        } else {
            return new Plane(newAngle, deltaAngle, HYPERSPEEDMODE);
        }
   }

   public Plane react(String s) {
     if (s.matches("right")) {
         if (this.mode == REGULARMODE) {
            return new Plane (width, Math.abs(deltaWidth),REGULARMODE).onTickRM();            
         } else if (this.mode == HYPERSPEEDMODE) {
             return new Plane(angle, Math.abs(deltaAngle), HYPERSPEEDMODE).onTickHM();
         } 
       } else if (s.matches("left")) {
           if (this.mode == REGULARMODE) {
            System.out.println("ok");
            return new Plane (width, -Math.abs(deltaWidth), REGULARMODE).onTickRM();
            } else if (this.mode == HYPERSPEEDMODE) {
              return new Plane(angle, -Math.abs(deltaAngle), HYPERSPEEDMODE).onTickHM();
           } 
       }
     return this;
   }
 
   // NEED TO LOOK AT GAMEWORLDS DOCUMENTATION
    public WorldImage planeImage() {
        return new CircleImage(new Posn(this.width, this.height), 50, color);
//            return new FromFileImage(this.center, "Images/shark.png").
//          overlayImages(new CircleImage(this.center, this.radius, this.col));
    }
    
    
    
    
          
}
