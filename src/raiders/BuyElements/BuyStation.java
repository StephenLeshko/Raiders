
package raiders.BuyElements;

import java.awt.Graphics;
import raiders.Player;


public abstract class BuyStation {
    protected float x;
    protected float y;
    protected Player player;
    protected int price;
    protected String name;
    
    public BuyStation(float x, float y, Player player, int price, String name){
        this.x = x;
        this.y = y;
        this.player = player;
        this.price = price;
        this.name = name;
    }
    
    public abstract void update(long timePassed);

    public abstract void draw(Graphics g);
    
    public abstract void buy();
    
}
