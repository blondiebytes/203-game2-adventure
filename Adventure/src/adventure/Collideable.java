
package adventure;


public interface Collideable<D> {
    public int getWidth();
    public int getHeight();
    public int getCenter();
    public int getRadius();
    public D collidesWith(Collideable thing);
    // x and y are centers
    // distance(my x, my y, thing x, thing y) <= (my radius + thing radius)
    public int distance(Collideable thing);
    
}

