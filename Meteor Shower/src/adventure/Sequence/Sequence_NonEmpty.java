/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventure.Sequence;

import adventure.Collideable;
import adventure.Tickable;

public class Sequence_NonEmpty<D extends Comparable & Tickable & Collideable> implements Sequence<D>, Sequenced<D> {
    D here;
    Sequence<D> next;
    
    public Sequence_NonEmpty(D here,Sequence<D> next) {
        this.here = here;
        this.next = next;
    }
    
    public boolean hasNext() {
        return true;
    }
    
    public D here() {
        return this.here;
    }
    
    public Sequence<D> next() {
        return next; 
       }
    
    public Sequence<D> seq() {
        return this;
    }
    
    public String toStringSequence() {
        return "" + this.here;
    }
    
    
}
