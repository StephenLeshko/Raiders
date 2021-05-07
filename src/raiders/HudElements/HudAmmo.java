
package raiders.HudElements;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import raiders.Weapons;

public class HudAmmo extends HudObject{

    
    public Weapons weapons;
    
    public HudAmmo(float x, float y, Weapons weapons){
        super(x, y);
        this.weapons = weapons;
    }
    
    @Override
    public void update(long timePassed) {
        int g = weapons.reserveAmmo[weapons.getWeapon()];
    }
    
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("Avenir", Font.BOLD, 48));
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        if(weapons.currentMagAmmo[weapons.getWeapon()] == 0 && weapons.reserveAmmo[weapons.getWeapon()] == 0){
            g2.setColor(Color.RED);
        }
        g2.drawString(weapons.currentMagAmmo[weapons.getWeapon()] +  " / " +  weapons.reserveAmmo[weapons.getWeapon()], (int)x, (int)y);
    }
    
}
