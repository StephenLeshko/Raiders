
package raiders.HudElements;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.util.*;


public class HudPerks extends HudObject{

    private Image jugSymbol = new ImageIcon("/Users/21sleshko/NetBeansProjects/Raiders/JugSymbol.png").getImage();
    private Image doubleSymbol = new ImageIcon("/Users/21sleshko/NetBeansProjects/Raiders/DoubleTapSymbol.png").getImage();
    private Image quickSymbol = new ImageIcon("/Users/21sleshko/NetBeansProjects/Raiders/QuickSymbol.png").getImage();
    private Image speedSymbol = new ImageIcon("/Users/21sleshko/NetBeansProjects/Raiders/SpeedSymbol.png").getImage();
    private Image stamSymbol = new ImageIcon("/Users/21sleshko/NetBeansProjects/Raiders/StamSymbol.png").getImage();
    private Image superSymbol = new ImageIcon("/Users/21sleshko/NetBeansProjects/Raiders/SuperchargeSymbol.png").getImage();
    private Image tikiSymbol = new ImageIcon("/Users/21sleshko/NetBeansProjects/Raiders/TikiSymbol.png").getImage();
    private Image[] symbols;
    private ArrayList<Image> perkOrder;
    
    public HudPerks(float x, float y) {
        super(x, y);
        symbols = new Image[] {jugSymbol, doubleSymbol, stamSymbol,
        speedSymbol, quickSymbol, tikiSymbol, superSymbol};
        perkOrder = new ArrayList<Image>();
        
        }
    
    
    public void removeAll(){
        for(int i = perkOrder.size() - 1; i >= 0; i--){
            perkOrder.remove(i);
        }
    }
    
    public void addPerk(int index){
        perkOrder.add(symbols[index]);
    }
    

    @Override
    public void update(long timePassed) {
    }

    @Override
    public void draw(Graphics g) {
        g.setFont(new Font("Avenir", Font.BOLD, 48));
        if(perkOrder.size() > 0){
            for(int i = 0; i < perkOrder.size(); i++){
                g.drawImage(perkOrder.get(i), (530 + (50*i)), 615, null);
            }
        }

    }
    
}
