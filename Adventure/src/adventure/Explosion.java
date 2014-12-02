/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventure;

import static adventure.LaserRM.count;
import javalib.worldimages.FromFileImage;
import javalib.worldimages.Posn;
import javalib.worldimages.WorldImage;

class Explosion implements Comparable<Explosion>, Collideable<Explosion>, Tickable<Explosion>{
    int identity;
    int height;
    int width;
    int show;
    static int count;
    
    public Explosion(int widthM, int heightM) {
        height = heightM;
        width = widthM;
        show = 10;
        this.identity = count;
        count++;
    }
    
    public Explosion(int widthM, int heightM, int identityM, int show) {
        height = heightM;
        width = widthM;
        this.identity = identityM;
        this.show = show;
    }
    
    // ========== DRAW ==========
    public WorldImage explosionImage() {
            System.out.println("EXPLOSION " + this.identity + " : This width" + this.width + "This height" + this.height);
            return new FromFileImage(new Posn(this.width, this.height), "explosion.png");
    }
    
    // ========== TICK ==========
    public Explosion onTick() {
        return new Explosion(this.width, this.height, this.identity, this.show - 1);
    }
    
    // ========== EQUALITY ==========
     public boolean isEqualToId(Explosion otherExplosion) {
        return this.identity == otherExplosion.identity;
    }
     
     
    
     
    
     // ========== COMPARETO ==========
    public int compareTo(Explosion otherExplosion) {
        if (this.isEqualToId(otherExplosion)) {
            return 0;
        } else if (this.identity < otherExplosion.identity) {
            return 1;
        } else {
            return -1;
        }
    }

    
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    
    // UNSUPPORTED BY GAME --> EXPLOSIONS CANNOT HAVE COLLISIONS OR TICK
   
    @Override
    public int getRadius() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int distance(Collideable thing) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public Explosion collidesWith(Collideable thing) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
