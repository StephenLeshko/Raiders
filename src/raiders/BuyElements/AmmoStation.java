
package raiders.BuyElements;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;
import raiders.Audio;
import raiders.Player;
import raiders.Proximity;
import raiders.Weapons;


public class AmmoStation extends BuyStation{
    
    private final Image ammoBox = new ImageIcon("/Users/21sleshko/NetBeansProjects/Raiders/AmmoBox.png").getImage();

    private boolean showMessage = false;
    
    private Weapons weapons;
    
    public AmmoStation(float x, float y, Player player, int price, String name, Weapons weapons) {
        super(x, y, player, price, name);
        this.weapons = weapons;
    }
    
    public void priceSet(){
        if(weapons.getWeapon() == 0){
            price = 500;
        }
        if(weapons.getWeapon() == 1){
            price = 1000;
        }
        if(weapons.getWeapon() == 2){
            price = 1500;
        }
        if(weapons.getWeapon() == 3){
            price = 5000;
        }
    }

    @Override
    public void update(long timePassed){
        //Will set showMessage to true
        priceSet();
        if(Proximity.checkProx(player.getX(), player.getY(), this.x, this.y)){
            showMessage = true;
        }else{
            showMessage = false;
        }
            
        //Buy should only be called if it wants to be
    }

    @Override
    public void draw(Graphics g){
        //Will also handle the show message; and show the message
        g.drawImage(ammoBox, (int) x, (int) y, null);
        if(showMessage && !weapons.atMax()){
            g.setFont(new Font("Avenir", Font.BOLD, 30));
            g.setColor(Color.WHITE);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2.drawString("Buy " + name + " for $" + price, 500, 500);
        }
    }

    @Override
    public void buy(){
        //Needs an "at full ammo" condition
        if(showMessage && player.getPoints() >= price && !weapons.atMax()){
            //Audio.playSound("juggBuy.wav");
            Audio a = new Audio("buySound.wav");
            weapons.resuply();
            player.subtractPoints(price);
        }
    }

}
