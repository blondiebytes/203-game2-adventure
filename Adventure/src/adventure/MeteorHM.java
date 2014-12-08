package adventure;

import java.util.Random;
import javalib.worldimages.FromFileImage;
import javalib.worldimages.Posn;
import javalib.worldimages.WorldImage;

public class MeteorHM implements Comparable<MeteorHM>, Collideable<MeteorHM>, Tickable<MeteorHM> {

    int width;
    int deltaWidth = 1;
    int height;
    int identity;
    static int radius = 20;
    int leavingWidth = MAXWgeneration / 2;
    static int MAXH = 450;
    static int MAXWgeneration = 550;
    static int count = 0;
    static String color = "white";

    // ========== CONSTRUCTORS ==========
    // Starting from off screen height
    MeteorHM(PlaneHM p) {
        this(-20, p.height, count, p.direction, 1);
        count++;
    }

    // Haave them start from each side -> make starting width randomized (from each end)
    MeteorHM(int width, int height, int count, String direction, int deltaWidth) {
        // Using Sentinal w = -20
        if (width == -20) {
            Random random = new Random();
            int delta = random.nextInt();
            if (delta % 2 == 0 ) {
            this.height = delta % 500;
            } else {
                this.height = delta % 50 * 5 - 100;
            }
            // We want to go the opposite way the plane is going
            if (direction.equals("right")) {
                // If you start from the left, you want to go right (so add 1 each time)
                this.width = 0;
                this.deltaWidth = 1;
            } else {
                // If you start from the right, you want to go left (so subtract 1 each time)
                this.width = MAXWgeneration;
                this.deltaWidth = -1;
            }
        } else {
            this.height = height;
            this.width = width;
            this.deltaWidth = deltaWidth;
        }
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
    public MeteorHM react(String se) {
        return this;
    }

    // ========== TICK ==========
    // Height is ranomly 
    public MeteorHM onTick() {
        return this;
    }

    public MeteorHM onTick(PlaneHM p) {
        // Different Versions of the AI in Comments
        // ====================================================================
        // If we want the meteors to "chase" the plane...all go down when the plane goes up; all go up when the plane goes down
//        if (p.height > this.height) {
//            if (this.deltaWidth > 0) {
//                return new MeteorHM(this.width + this.deltaWidth, this.height + this.deltaWidth, this.identity, "already-decided", this.deltaWidth);
//            } else {
//                return new MeteorHM(this.width + this.deltaWidth, this.height - this.deltaWidth, this.identity, "already-decided", this.deltaWidth);
//            }
//        } else {
//            if (this.deltaWidth > 0) {
//                return new MeteorHM(this.width + this.deltaWidth, this.height - this.deltaWidth, this.identity, "already-decided", this.deltaWidth);
//            } else {
//                return new MeteorHM(this.width + this.deltaWidth, this.height + this.deltaWidth, this.identity, "already-decided", this.deltaWidth);
//            }
//        }

        // ====================================================================
        // If when the plane goes up, all the meteors go up and when the plane
        // goes down, all the meteors go down
        // if (p.upOrDown.equals("up")) {
//            if (this.deltaWidth > 0) {
//                return new MeteorHM(this.width + this.deltaWidth, this.height + this.deltaWidth, this.identity, "already-decided", this.deltaWidth);
//            } else {
//                return new MeteorHM(this.width + this.deltaWidth, this.height - this.deltaWidth, this.identity, "already-decided", this.deltaWidth);
//            }
//        } else {
//            if (this.deltaWidth > 0) {
//                return new MeteorHM(this.width + this.deltaWidth, this.height - this.deltaWidth, this.identity, "already-decided", this.deltaWidth);
//            } else {
//                return new MeteorHM(this.width + this.deltaWidth, this.height + this.deltaWidth, this.identity, "already-decided", this.deltaWidth);
//            }
//        }
        // ====================================================================
        // If when the plane goes up, we want the meteors going left to go down and the meteors going right to go up 
        // and when the plane goes down, we want the meteors going right to go down and the meteors going left to go up
        // Basically interchanging which side is chasing the plane --> LOOKS THE COOOOOOLEST
        if (p.upOrDown.equals("up")) {
            return new MeteorHM(this.width + this.deltaWidth, this.height + this.deltaWidth, this.identity, "already-decided", this.deltaWidth);
        } else {
            return new MeteorHM(this.width + this.deltaWidth, this.height - this.deltaWidth, this.identity, "already-decided", this.deltaWidth);
        }

    }

    // ========== EQUALITY ==========
    public boolean isEqualToDWHC(MeteorHM otherMeteor) {
        return (this.width == otherMeteor.width)
                && (this.height == otherMeteor.height)
                && (this.color.equals(otherMeteor.color));
    }

    public boolean isEqualToId(MeteorHM otherMeteor) {
        return this.identity == otherMeteor.identity;
    }

    // ========== COMPARETO ==========
    public int compareTo(MeteorHM otherMeteor) {
        if (this.isEqualToId(otherMeteor)) {
            return 0;
        } else if (this.identity < otherMeteor.identity) {
            return 1;
        } else {
            return -1;
        }
    }

    // ========== COLLISIONS ========== 
    public MeteorHM collidesWith(Collideable thing) {
        if (this.distance(thing) <= (this.getRadius() + thing.getRadius())) {
            return this;
        } //        } else if (thing instanceof PlaneHM) {
        //            if ((this.deltaWidth == 1 && this.getWidth() >= thing.getWidth())
        //                    || (this.deltaWidth == -1 && this.getWidth() <= thing.getWidth())) {
        //                return this;
        //            }
        //            return null;
        //        } 
        else // HATE null....
        {
            return null;
        }
    }

    public WorldImage meteorImage() {
        return new FromFileImage(new Posn(this.width, this.height), "White-Meteor.png");
    }

    public int distance(Collideable thing) {
        return (int) Math.sqrt(
                (this.getWidth() - thing.getWidth())
                * (this.getWidth() - thing.getWidth())
                + (this.getHeight() - thing.getHeight())
                * (this.getHeight() - thing.getHeight()));

    }

}
