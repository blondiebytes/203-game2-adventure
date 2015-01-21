
package adventure.SetBag;


import adventure.Collideable;
import adventure.MeteorHM;
import adventure.MeteorRM;
import adventure.PlaneHM;
import adventure.Sequence.Sequence;
import adventure.Sequence.Sequence_Empty;
import static adventure.SetBag.SetBag_NonEmpty.empty;
import adventure.Tickable;



public class SetBag_Empty<D extends Comparable & Tickable & Collideable> implements Bag<D> {
    boolean isBlack;
    
     public void SetBag_Empty() {
    }
    
    public int cardinality() {
	return 0;
    }
    
    public boolean isEmptyHuh() {
	// If it's a FiniteSet_Empty, then it's understood to be empty
	// Therefore, when this function is called, it's empty. 
        return true; 
    }
    
    public boolean member(D elt) {
	// If the set is empty, then nothing can be a member of it.
	return false;
    }

    public Bag remove (D elt) {
        // We can't remove anything from an empty tree
        // So return the same object
	return this;
    }

    public Bag add(D elt) {
        // The empty set plus an element is a new tree
        // with just that element
        return new SetBag_NonEmpty(elt);
    }
    
    public Bag union(Bag u) {
        // The empty set plus a new tree is just the new tree
        return u;
    }
    
    public Bag inter(Bag u) {
        // The empty set can't have anything in common
        // So return an empty set
		return empty();  
        }
    
    public boolean equal (Bag u) {
        // If they are both empty -> both have the same size
        // Then they are the same
        return u.cardinality() == this.cardinality();
    }
    
    public Bag diff(Bag u) {
        // Everything in u isn't in an empty set
        return u;
    }
    
    public boolean subset (Bag u) {
        // The empty set is a subset of everything
        return true;
    }
    
     public String toString() {
            return "E";
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
     
     public Bag<D> tick(PlaneHM p) {
         return this;
     }
   
   public D collidesWith(Collideable thing) {
       return null;
   }

  
    

}
