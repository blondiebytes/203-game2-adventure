package adventure.SetBag;

// REQUIREMENTS: 
import adventure.Collideable;
import adventure.LaserHM;
import adventure.LaserRM;
import adventure.MeteorHM;
import adventure.MeteorRM;
import adventure.Sequence.Sequence;
import adventure.Sequence.Sequence_Cat;
import adventure.Sequence.Sequence_NonEmpty;
import adventure.Sequence.Sequenced;
import adventure.Tickable;

public class SetBag_NonEmpty<D extends Comparable & Tickable & Collideable> implements Bag<D>, Sequenced<D> {

    boolean isBlack;
    int count;
    D root;
    Bag<D> left;
    Bag<D> right;

    public SetBag_NonEmpty(D root, int count, boolean isBlack, Bag<D> left, Bag<D> right) {
        this.count = count;
        this.root = root;
        this.left = left;
        this.right = right;
        this.isBlack = isBlack;
    }

    public SetBag_NonEmpty(D root, int count, Bag<D> left, Bag<D> right) {
        this.count = count;
        this.root = root;
        this.left = left;
        this.right = right;
    }

    public SetBag_NonEmpty(D root, Bag<D> left, Bag<D> right) {
        this.count = 1;
        this.root = root;
        this.left = left;
        this.right = right;
    }

    public SetBag_NonEmpty(D root, int count) {
        // Setting Properties
        this.count = count;
        this.root = root;
        this.left = empty();
        this.right = empty();
    }

    public SetBag_NonEmpty(D root) {
        // Setting Properties
        this.count = 1;
        this.root = root;
        this.left = empty();
        this.right = empty();
    }

    public int getCount(D elt) {
        if (elt.compareTo(this.root) == 0) {
            return count;
        } else {
            if (elt.compareTo(root) < 0) {
                return this.left.getCount(elt);
            } else {
                return this.right.getCount(elt);
            }

        }
    }

    // We need something that puts this in a sequence. 
    public Sequence<D> seq() {
        return new Sequence_NonEmpty(root, count, (new Sequence_Cat(left.seq(), right.seq())));
    }

    // IMPLEMENTATION OF SEQUENCES: 
    public int sumIt() {
        return sumItS(this.seq());
    }

    public int sumItS(Sequence<D> as) {
        int sum = 0;
        while (as.hasNext()) {
            sum = sum + 1;
            as = as.next();
        }
        return sum;
    }

    public String stringIt() {
        return stringItS(this.seq());
    }

    public String stringItS(Sequence<D> as) {
        StringBuffer all = new StringBuffer("");
        while (as.hasNext()) {
            all.append(as.next().toStringSequence());
            all.append(" ");
            as = as.next();
        }
        return all.toString();
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
    
    

    public static Bag empty() {
        return new SetBag_Empty();
    }

    public int cardinality() {
        return count + left.cardinality() + right.cardinality();

    }

    public boolean isEmptyHuh() {
        return this.getCount(root) == 0 && left.isEmptyHuh() && right.isEmptyHuh();

    }

    public boolean member(D elt) {
        return this.getCount(elt) != 0;
    }

    public Bag remove(D elt) {
        return removeN(elt, 1);
    }

    public Bag removeN(D elt, int n) {
        if (elt.compareTo(this.root) == 0) {
            int max = Math.max(0, this.count - n);
            return new SetBag_NonEmpty(this.root, max, this.left, this.right);
        } else {
            if (elt.compareTo(this.root) < 0) {
                return new SetBag_NonEmpty(this.root, this.count, this.left.removeN(elt, n), this.right);
            } else {
                return new SetBag_NonEmpty(this.root, this.count, this.left, this.right.removeN(elt, n));
            }
        }
    }

    public Bag removeAll(D elt) {
        return removeN(elt, this.getCount(elt));
    }

    public Bag add(D elt) {
        return addN(elt, 1).blacken();
    }

    public Bag addN(D elt, int n) {
        if (elt.compareTo(this.root) == 0) {
            int max = Math.max(0, this.count + n);
            return new SetBag_NonEmpty(this.root, max, this.isBlack, this.left, this.right);
        } else {
            if (elt.compareTo(this.root) < 0) {
                return new SetBag_NonEmpty(this.root, this.count, this.isBlack, this.left.addN(elt, n), this.right);
            } else {
                return new SetBag_NonEmpty(this.root, this.count, this.isBlack, this.left, this.right.addN(elt, n));
            }
        }
    }
    
    public Bag union(Bag u) {
        return u.union(left.union(right)).addN(root, this.getCount(root));
    }

    public Bag inter(Bag u) {
        if (u.member(this.root)) {
            if (u.getCount(root) > this.getCount(root)) {
                return new SetBag_NonEmpty(this.root, this.getCount(root),
                        this.left.inter(u), this.right.inter(u));
            } else {
                return new SetBag_NonEmpty(this.root, u.getCount(root),
                        this.left.inter(u), this.right.inter(u));
            }
        } else {
            return this.left.inter(u).union(this.right.inter(u));
        }
    }

    public Bag diff(Bag u) {
        // U - this
        // 6 - 5 --> subtract how many in this
        Bag removed = u.removeN(root, this.getCount(root));
        return (left.union(right)).diff(removed);
    }

    public boolean equal(Bag u) {
        return (this.subset(u) && u.subset(this));
    }

    public boolean subset(Bag u) {
        return (u.getCount(root) >= this.getCount(root)) && this.left.subset(u)
                && this.right.subset(u);
    }

    // Balancing Methods

    public boolean isBlackHuh() {
        return isBlack;
     }

    public Bag<D> blacken() {
        return new SetBag_NonEmpty(this.root, this.count, true, this.left, this.right);
    }
    
    public SetBag_NonEmpty balance() {
        SetBag_NonEmpty lefty;
        SetBag_NonEmpty leftyleft;
        SetBag_NonEmpty leftyright;
        SetBag_NonEmpty righty;
        SetBag_NonEmpty rightyleft;
        SetBag_NonEmpty rightyright;
        
        if ((this.isBlackHuh()
                && (this.left instanceof SetBag_NonEmpty)
                && (((SetBag_NonEmpty) this.left).left instanceof SetBag_NonEmpty)
                && !((SetBag_NonEmpty) this.left).isBlackHuh()
                && !((SetBag_NonEmpty) this.left).left.isBlackHuh())) {
            //cast it so compiler knows it's SetBag_NonEmpty
            //error root because i can't get the left of left. 

            lefty = ((SetBag_NonEmpty) this.left);
            leftyleft = ((SetBag_NonEmpty) lefty.left);
            //hahahahaha wowow this is complicated. 
            return new SetBag_NonEmpty(
                    /* root */ /* root */ lefty.root,
                    /* count */ lefty.count,
                    /* isBlack */ false,
                    /* left */new SetBag_NonEmpty(leftyleft.root, leftyleft.count, true, leftyleft.left, leftyleft.right),
                    /* right */ new SetBag_NonEmpty(this.root, this.count, true, leftyleft.right,this.right));
        } else if ((this.isBlackHuh() 
                && (this.left instanceof SetBag_NonEmpty) 
                && (((SetBag_NonEmpty) this.left).right instanceof SetBag_NonEmpty)
                && !((SetBag_NonEmpty) this.left).isBlackHuh()
                && !((SetBag_NonEmpty) this.left).right.isBlackHuh())) {

            lefty = ((SetBag_NonEmpty) this.left);
            leftyleft = ((SetBag_NonEmpty) lefty.left);
            leftyright = ((SetBag_NonEmpty) lefty.right);
            //hahahahaha wowow this is complicated. 
            return new SetBag_NonEmpty(
                    /* root */ /* root */ leftyright.root,
                    /* count */ leftyright.count,
                    /* isBlack */ false,
                    /* left */new SetBag_NonEmpty(lefty.root, lefty.count, true, leftyleft, leftyright.left),
                    /* right */ new SetBag_NonEmpty(this.root, this.count, true, leftyright.right, this.right));
        } else if ((this.isBlackHuh() 
                && (this.right instanceof SetBag_NonEmpty) 
                && (((SetBag_NonEmpty) this.right).left instanceof SetBag_NonEmpty)
                && !((SetBag_NonEmpty) this.right).isBlackHuh()
                && !((SetBag_NonEmpty) this.right).left.isBlackHuh())) {

            righty = ((SetBag_NonEmpty) this.right);
            rightyleft = ((SetBag_NonEmpty) righty.left);

            return new SetBag_NonEmpty(
                    /* root */ /* root */ rightyleft.root,
                    /* count */ rightyleft.count,
                    /* isBlack */ false,
                    /* left */new SetBag_NonEmpty(this.root, this.count, true, this.left, rightyleft.left),
                    /* right */ new SetBag_NonEmpty(righty.root, righty.count, true, rightyleft.right,righty.right));
        } else if ((this.isBlackHuh() 
                && (this.right instanceof SetBag_NonEmpty) 
                && (((SetBag_NonEmpty) this.right).right instanceof SetBag_NonEmpty)
                && !((SetBag_NonEmpty) this.right).isBlackHuh()
                && !((SetBag_NonEmpty) this.right).right.isBlackHuh())) {

            righty = ((SetBag_NonEmpty) this.right);
            rightyright = ((SetBag_NonEmpty) righty.right);
            rightyleft = ((SetBag_NonEmpty) righty.left);

            return new SetBag_NonEmpty(
                    /* root */ /* root */ righty.root,
                    /* count */ righty.count,
                    /* isBlack */ false,
                    /* left */new SetBag_NonEmpty(this.root, this.count, true, this.left, rightyleft),
                    /* right */ new SetBag_NonEmpty(rightyright.root, rightyright.count, true, rightyright.left, rightyright.right));
        } else {
            return this;
        }
    }

   
    

}
