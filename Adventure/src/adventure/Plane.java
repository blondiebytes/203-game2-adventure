
package adventure;
        
        
public class Plane {
    
    int width;
    int deltaWidth;
    double angle;
    double deltaAngle;
    int height;
    static int MAXW;
    static int MAXANGLE = 180;
    
    static int middleOfScreenWidth = MAXW/2;
    int topOfScreen;
    
    // 0 means regular mode; 1 means hyper-speed mode
    static int REGULARMODE = MeteorShower.REGULARMODE;
    static int HYPERSPEEDMODE = MeteorShower.HYPERSPEEDMODE;
    int mode = REGULARMODE;
    
    Plane() throws Exception {
        this(MAXW/2, -1, REGULARMODE);
    }
    
    public Plane(int mode) throws Exception {
            this(0, -30, mode);
    }
    
    private Plane (int width, int deltaWidth, int mode) throws Exception{
        if (mode == REGULARMODE) {
            this.width = width;
            this.deltaWidth = deltaWidth;
            this.mode = mode;
        } else {
            throw new Exception("Problem with constructor RM");
        }
    }
    
    private Plane (double angle, double deltaAngle, int mode) throws Exception {
        if (mode == HYPERSPEEDMODE) {
            this.angle = angle;
            this.deltaAngle = deltaAngle;
            this.mode = mode;
        } else {
            throw new Exception("Problem with constructor HM");
        }
    }
   
   
  // REGULAR: MOVE RIGHT AND LEFT
   public Plane onTickRM() throws Exception{
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
   public Plane onTickHM() throws Exception{
        double newAngle = angle + deltaAngle;
        if (newAngle < 0) {
            return new Plane(0, deltaAngle, HYPERSPEEDMODE);
        } else if (newAngle >= MAXANGLE) {
            return new Plane(MAXANGLE, -deltaAngle, HYPERSPEEDMODE);
        } else {
            return new Plane(newAngle, deltaAngle, HYPERSPEEDMODE);
        }
   }

   public Plane react(String s) throws Exception{
     if (s.matches("right")) {
         if (this.mode == REGULARMODE) {
            return new Plane (width, Math.abs(deltaWidth),REGULARMODE).onTickRM();            
         } else if (this.mode == HYPERSPEEDMODE) {
             return new Plane(angle, Math.abs(deltaAngle), HYPERSPEEDMODE).onTickHM();
         } else {
             throw new Exception("PROBLEMS WITH REACT RIGHT");
         }
       } else if (s.matches("left")) {
           if (this.mode == REGULARMODE) {
            return new Plane (width, -Math.abs(deltaWidth), REGULARMODE).onTickRM();
            } else if (this.mode == HYPERSPEEDMODE) {
              return new Plane(angle, -Math.abs(deltaAngle), HYPERSPEEDMODE).onTickHM();
           } else {
                throw new Exception("PROBLEMS WITH REACT LEFT");
            }
       }
     return this;
   }
 
   // NEED TO LOOK AT GAMEWORLDS DOCUMENTATION
    public void draw() {
        
    }
    
    
    
    
          
}
