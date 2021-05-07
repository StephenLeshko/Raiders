
package raiders.HudElements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import raiders.Player;


public class HudPoints extends HudObject{

    private Player player;
    
    public HudPoints(float x, float y, Player player) {
        super(x, y);
        this.player = player;
    }

    @Override
    public void update(long timePassed) {
        
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.drawString("$" + player.getPoints(), (int)x, (int)y);
    }
    
}
