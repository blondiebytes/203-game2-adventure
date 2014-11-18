
package adventure;

import adventure.SetBag.Bag;

public interface Tickable<D extends Comparable> {
    public D onTick();
}         
