
package adventure.SetBag;

import adventure.Collideable;
import adventure.Sequence.Sequence;
import adventure.Sequence.Sequenced;
import adventure.Tickable;

public interface Bag<D extends Comparable & Tickable & Collideable> extends Sequenced<D>{
    public int cardinality();
    public  boolean isEmptyHuh();
    public  boolean member(D elt); 
    public Bag remove (D elt);
    public Bag add(D elt);
    public Bag union(Bag u);
    public Bag inter(Bag u);
    public Bag diff(Bag u);
    public boolean equal (Bag u);
    public boolean subset (Bag u);
    public Sequence<D> seq();
    public Bag<D> tick();
    public D collidesWith(Collideable thing);
    
}

