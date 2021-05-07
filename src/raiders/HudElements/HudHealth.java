
package raiders.HudElements;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import raiders.Player;

public class HudHealth extends HudObject{
    
    private Player player;
    private Color color;
    
    public HudHealth(float x, float y, Player player){
        super(x, y);
        this.player = player;
        color = Color.decode("#18FF00");
    }
    @Override
    public void update(long timePassed) {
        color = Color.decode("#18FF00");
        if(player.getHealth() == 150){ 
            
            color = Color.decode("#FFFD37");
        }else if(player.getHealth() == 100){
            color = Color.decode("#FF701A");
        }else if(player.getHealth() == 50){
            color = Color.decode("#ff1f1f");
        }else if(player.getHealth() == 0){
            color = Color.decode("#810000");
        }
    }
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.setFont(new Font("Avenir", Font.BOLD, 48));
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.drawString("Health: " + player.getHealth(), (int)x, (int)y);
    }
    
}
