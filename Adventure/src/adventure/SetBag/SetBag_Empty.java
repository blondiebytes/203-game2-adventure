
package adventure.SetBag;


import adventure.Collideable;
import adventure.MeteorHM;
import adventure.MeteorRM;
import adventure.Sequence.Sequence;
import adventure.Sequence.Sequence_Empty;
import static adventure.SetBag.SetBag_NonEmpty.empty;
import adventure.Tickable;



public class SetBag_Empty<D extends Comparable & Tickable & Collideable> implements Bag<D> {
    boolean isBlack;
    
    
    public void SetBag_Empty() {
       this.isBlack = true;
    }
    
    public void SetBag_Empty(Boolean isBlack) {
        this.isBlack = isBlack;
    }
  
   
    
    //Balance Tree methods
    
    public Bag<D> balance() {
        return this;
    }
    
    public boolean isBlackHuh() {
        return isBlack;
    }
    
    public Bag<D> blacken() {
        return new SetBag_Empty();
    }
           
    // Sequence Methods
    public Sequence<D> seq() {
        return new Sequence_Empty();
    }
    
    public int sumIt () {
        return sumItS(this.seq());
    }
    
    public int sumItS(Sequence<D> as) {
        return 0;
    }
    
    public String stringIt() {
        return stringItS(this.seq());
    }
    
    public String stringItS(Sequence<D> as) {
        return "";
    }
    
    // METEOR METHODS
    
     public Bag<D> tick() {
        return this;
    }
   
   public D collidesWith(Collideable thing) {
       return null;
   }
    
    // Finite Set Bag Methods
    
    public int getCount(D elt) {
        return 0;
    }
    
    public int cardinality() {
	return 0;
    }
    
    public boolean isEmptyHuh() {
        return true; 
    }
    
    public boolean member(D elt) {
	return false;
    }

    public Bag remove (D elt) {
	return this;
    }
    
    public Bag removeN(D elt, int n) {
        return this.remove(elt);
    }
    
    public Bag removeAll (D elt) {
        return this.remove(elt);
    }

    public Bag add(D elt) {
        return this.addN(elt,1).blacken();
    }
    
    public Bag addN(D elt, int n) {
        return new SetBag_NonEmpty(elt, n, false, empty(), empty());
    }

    
    public Bag union(Bag u) {
        return u;
    }
    
    public Bag inter(Bag u) {
        return empty();  
    }
    
    public boolean equal (Bag u) {
        return u.cardinality() == this.cardinality();
    }
    
    public Bag diff(Bag u) {
        return u;
    }
    
    public boolean subset (Bag u) {
        return true; 
    }





    
}
