
package raiders;

import java.awt.*;


public abstract class GameObject {
    //This is essentially core2.0... Allows us to create game objects easily
    //I need to find a way to steal some trait from the object that will allow me to easily delete it...
    protected float x;
    protected float y;
    
    protected float vx;
    protected float vy;
    protected ID id;
    public GameObject(float x, float y, ID id){
        this.x = x;
        this.y = y;
        this.id = id;
    }
    
    
    //Abstract methods that are forced to be made
    
    public abstract void update(long timePassed);

    public abstract void draw(Graphics g);
    

    
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getVx() {
        return vx;
    }

    public void setVx(float vx) {
        this.vx = vx;
    }

    public float getVy() {
        return vy;
    }

    public void setVy(float vy) {
        this.vy = vy;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }
    
    
    
    
    
}
