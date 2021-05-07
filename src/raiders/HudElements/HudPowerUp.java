
package raiders.HudElements;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.util.*;


public class HudPowerUp extends HudObject{

    private Image instaSymbol = new ImageIcon("/Users/21sleshko/NetBeansProjects/Raiders/InstaKillSymbol.png").getImage();
    private Image doubleSymbol = new ImageIcon("/Users/21sleshko/NetBeansProjects/Raiders/DoublePointsSymbol.png").getImage();
    
    
    private Image[] symbols;
    private ArrayList<Image> powOrder;
    public boolean instaOn = false;
    public boolean doubleOn = false;
    
    
    public HudPowerUp(float x, float y) {
        super(x, y);
        symbols = new Image[] {instaSymbol, doubleSymbol};
        powOrder = new ArrayList<Image>();
        System.out.println("Hud object created");
        }
    
    public void clearPow(){
        for(int i = 0; i < powOrder.size(); i++){
            powOrder.remove(i);
        }
        doubleOn = false;
        instaOn = false;
    }
    
    public void addPow(int index){
        if(index == 0 && !instaOn){
            powOrder.add(symbols[index]);
            instaOn = true;
        }
        if(index == 1 && !doubleOn){
            powOrder.add(symbols[index]);
            doubleOn = true;
        }
        System.out.println("add");
    }
    
    public void removePow(int index){
        if(index == 1 && !doubleOn){
        powOrder.remove(symbols[index]);
        doubleOn = false;
        }
        if(index == 0 && !instaOn){
            powOrder.remove(symbols[index]);
            instaOn = false;
        }
        
    }
    

    @Override
    public void update(long timePassed) {
    }

    @Override
    public void draw(Graphics g) {
        g.setFont(new Font("Avenir", Font.BOLD, 48));
        if(powOrder.size() > 0){
            for(int i = 0; i < powOrder.size(); i++){
                g.drawImage(powOrder.get(i), (510 + (60*i)), 550, null);

            }
        }

    }
    
}
