
package raiders;

import java.awt.*;
import javax.swing.*;


public class Bullet extends GameObject{
    private Image pBullet = new ImageIcon("/Users/21sleshko/NetBeansProjects/Raiders/PistolBullet.png").getImage();

    private Cycler cycler;
        
    private int damage;
    
    public Bullet(float x, float y, ID id, Cycler cycler, int damage){
        super(x, y, id);
        vx = 30;
        vy = 0;
        this.cycler = cycler;
        this.damage = damage;
    }

    public synchronized void doDamage(){
        //Use the cycler to scan through all of the objects; see if its location is within limits; if so, brings down health
        for(int i = 0; i < cycler.object.size(); i++){
            if(cycler.object.get(i).id == ID.Zombie){
                Zombie z = (Zombie) cycler.object.get(i);
                if(this.x < cycler.object.get(i).x + 75 && this.x >= cycler.object.get(i).x && this.y < cycler.object.get(i).y + 75 && this.y >= cycler.object.get(i).y){
                    cycler.removeObject(this);
                    z.getDamaged(damage);
                }
            }
        }
    }
    @Override
    public void update(long timePassed) {
        doDamage();
        x += vx;
        y += vy;
    
    }

    @Override
    public void draw(Graphics g) {
        //This doesn't change
        g.drawImage(pBullet, (int)x, (int)y, null);
    }
    
}
