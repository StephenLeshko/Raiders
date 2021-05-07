
package raiders.HudElements;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import raiders.Round;




public class HudRound extends HudObject{
    
    private Round round = null;
    
    private Color color = Color.RED;

    public HudRound(float x, float y) {
        super(x, y);
    }
    
    public void setRound(Round theRound){
        round = theRound;
    }
    
    public void changeColor(){
        if(round != null){
            if(color.equals(Color.RED)){
            color = Color.WHITE;
        }else{
            color = Color.RED;
        }
        }
    }
    
    public void changeColorToRed(){
        if(round != null){
        color = Color.RED;
        }
    }

    @Override
    public void update(long timePassed) {
    }

    @Override
    public void draw(Graphics g) {
        g.setFont(new Font("Avenir", Font.BOLD, 48));
        if(round != null){
        g.setColor(color);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.drawString(Integer.toString(round.getRound()), (int) x, (int) y);
        }
    }
    
}
