package adventure.SetBag;

// REQUIREMENTS: 
import adventure.Collideable;
import adventure.LaserHM;
import adventure.LaserRM;
import adventure.MeteorHM;
import adventure.MeteorRM;
import adventure.PlaneHM;
import adventure.Sequence.Sequence;
import adventure.Sequence.Sequence_Cat;
import adventure.Sequence.Sequence_NonEmpty;
import adventure.Sequence.Sequenced;
import adventure.Tickable;

public class SetBag_NonEmpty<D extends Comparable & Tickable & Collideable> implements Bag<D>, Sequenced<D> {
    D root;
    Bag<D> left;
    Bag<D> right;

   

    // We need something that puts this in a sequence. 
    public Sequence<D> seq() {
        Sequence<D> after = (new Sequence_Cat(left.seq(), right.seq()));
        return new Sequence_NonEmpty(root, after);
    }

   
    public Bag<D> tick(){
        Bag newStuff = empty();
        Sequence<D> seq = this.seq();
        while (seq.hasNext()) {
            newStuff = newStuff.add(seq.here().onTick());
//            System.out.println("tickticktick");
            seq = seq.next();
        }
        return newStuff;
        //return new SetBag_NonEmpty(this.root.onTick(), this.left.tick(), this.right.tick());
    }
    
    public Bag<D> tick(PlaneHM p){
        Bag newStuff = empty();
        Sequence<D> seq = this.seq();
        while (seq.hasNext()) {
                newStuff = newStuff.add(seq.here().onTick(p));
//            System.out.println("tickticktick");
            seq = seq.next();
        }
        
        return newStuff;
        //return new SetBag_NonEmpty(this.root.onTick(), this.left.tick(), this.right.tick());
    }
   
   public D collidesWith(Collideable thing) {
        // Basically we want to say, for all the meteors in the meteor data struct
        // does one pass the plane? if so? which one
        if (this.root.collidesWith(thing) != null) {
            return this.root;
        } else {
            D onleft = this.left.collidesWith(thing);
            if (onleft != null) {
              return onleft;
            } else {
                return this.right.collidesWith(thing);
            }
        }
    }
    
    // Constructors
    public SetBag_NonEmpty(D root, Bag left, Bag right) {
        this.root = root;
        this.left = left;
        this.right = right;
    }

    public SetBag_NonEmpty(D root) {
        // Setting Properties
        this.root = root;
        this.left = empty();
        this.right = empty();
    }

    // (empty) --> FiniteSet 
    // Returns fresh empty set
    public static Bag empty() {
        // Calling the helper
        return new SetBag_Empty();
    }

    // (cardinality t) --> integer
    // Returns the number of elements in t
    public int cardinality() {
	// Return the sum of the number of things in the left and right trees 
        // While counting the root as a thing we have now
        return 1 + left.cardinality() + right.cardinality();

    }

    // (isEmptyHuh t) --> boolean
    // Determines if t is empty
    public boolean isEmptyHuh() {
	// If it's a  FiniteSet (non-empty), then this function
        // is called and it can't be empty
        // So return false.
        return false;
    }

    // (member t elt) --> boolean
    // t : FiniteSet
    // elt : integer
    // Determines if elt is in t
    public boolean member(D elt) {
        // If the root equals the element, then it's a member
        // of the tree and we are done and return true
        if (root.compareTo(elt) == 0) {
            return true;
        } else {
            if (root.compareTo(elt) == 1) {
                return this.left.member(elt);
            } else {
                return this.right.member(elt);
            }
        }
    }

    // (add t elt) --> finite-set
    // t : finite-set
    // elt : integer
    // Returns a set containing elt and everything in t
    public SetBag_NonEmpty add(D elt) {
        if (root.compareTo(elt) == 0) {
            return this;
        } else {
            // If the root is greater than the element, then add it to the left 
            // tree
            if (root.compareTo(elt) == 1) {
                return new SetBag_NonEmpty(this.root, this.left.add(elt), this.right);
            } // If the root is less than the element, then add it to the 
            // right tree
            else {
                return new SetBag_NonEmpty(this.root, this.left, this.right.add(elt));
            }
        }
    }

	// (remove t elt) → finite-set
    // t : finite-set
    // elt : integer
    // Returns a new FiniteSet without the element; we assume FiniteSets 
    // don't contain duplicates
    public Bag remove(D elt) {
        // If this element equals the root; then take out the root by unioning
        // the two children
        if (this.root.compareTo(elt) == 0) {
            // Turning two trees into one tree without the element
            return this.left.union(this.right);
        } else {
            if (this.root.compareTo(elt) == 1) {
                return new SetBag_NonEmpty(this.root, this.left.remove(elt), this.right);
            } else {
                return new SetBag_NonEmpty(this.root, this.left.remove(elt),
                        this.right.remove(elt));
            }
        }
    }

    // (union t u) --> finite-set
    // t : finite-set
    // u : finite-set
    // Returns a set containing everything in t and u
    public Bag union(Bag u) {
           // Union U with the unioned left & right with a copy this object; 
        // then add the root
        return u.union(this.left.union(this.right)).add(this.root);
    }

    // (inter t u) --> finite-set
    // t: finite-set
    // u: finite-set
    // Returns a set containing everything that is in both t and u
    public Bag inter(Bag u) {
        // If the root of this object is a member of u
        if (u.member(this.root)) {
                 // Then create a new tree with that root and the intersection
            // of the left tree and the intersection of the right tree
            return new SetBag_NonEmpty(this.root, this.left.inter(u),
                    this.right.inter(u));
        } else {
            // Otherwise don't add the root, and just return the union of
            // the left tree's intersection with u and the right tree's 
            // intersection with u
            return this.left.inter(u).union(this.right.inter(u));
        }
    }

    // (diff t u) --> finite-set
    // t : finite-set
    // u : finite-set
    // Returns a set containing everything in t that is not in u
    public Bag diff(Bag u) {
            // Remove the root of this tree from our input tree u
        // U - {root}
        Bag removed = u.remove(root);
            // Remove the left and the right of this tree from our input
        // tree u
        // U - {left U right)
        return (left.union(right)).diff(removed);
    }

    // (equal t u) --> boolean
    // t : finite-set
    // u : finite-set
    // Determines if t and u contain the same elements
    public boolean equal(Bag u) {
        return (this.subset(u) && u.subset(this));

//  return this.inter(u) == this.union(u);
    }

    // (subset t u) --> boolean
    // t : finite-set
    // u : finite-set
    // Determines if t is a subset of u
    public boolean subset(Bag u) {
        return (u.member(this.root) && this.left.subset(u)
                && this.right.subset(u));
    }

    public String toString() {
        return "(" + this.root + " " + left.toString()
                + " " + right.toString() + ")";
    }
    

   
    

}
