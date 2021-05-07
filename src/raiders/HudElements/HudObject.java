
package raiders.HudElements;

import java.awt.Graphics;


public abstract class HudObject {
    protected final float x;
    protected final float y;
    
    public HudObject(float x, float y){
        this.x = x;
        this.y = y;
    }
    
    //Abstract methods that are forced to be made
    public abstract void update(long timePassed);
    public abstract void draw(Graphics g);
    
    
    
}
