
package raiders.BuyElements;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;
import raiders.Audio;
import raiders.Perks;
import raiders.Player;
import raiders.Proximity;


public class SpeedStation extends BuyStation{
    
    private final Image speed = new ImageIcon("/Users/21sleshko/NetBeansProjects/Raiders/SpeedBuy.png").getImage();

    private Perks perks;
    private boolean showMessage = false;
    
    public SpeedStation(float x, float y, Player player, int price, String name, Perks perks) {
        super(x, y, player, price, name);
        this.perks = perks;
    }

    @Override
    public void update(long timePassed){
        //Will set showMessage to true
        if(Proximity.checkProx(player.getX(), player.getY(), this.x, this.y)){
            showMessage = true;
        }else{
            showMessage = false;
        }
    }

    @Override
    public void draw(Graphics g){
        //Will also handle the show message; and show the message
        g.drawImage(speed, (int) x, (int) y, null);
        if(showMessage && !perks.hasSpeed){
            g.setFont(new Font("Avenir", Font.BOLD, 30));
            g.setColor(Color.WHITE);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2.drawString("Buy " + name + " for $" + price, 500, 500);
        }
    }

    @Override
    public void buy(){
        if(showMessage && player.getPoints() >= price && !perks.hasSpeed){
            //Audio.playSound("juggBuy.wav");
            Audio a = new Audio("buySound.wav");
            player.subtractPoints(price);
            perks.giveSpeed();
        }
    }

}