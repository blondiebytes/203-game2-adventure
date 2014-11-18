
package adventure;


public interface Collideable<D> {
    public int getWidth();
    public int getHeight();
    public D collidesWith(Collideable thing);
}
