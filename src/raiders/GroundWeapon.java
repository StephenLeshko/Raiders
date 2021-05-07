


package raiders;

import raiders.BuyElements.BuyStation;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;


public class GroundWeapon extends BuyStation {

    private Weapons weapons;
    
    private int weapon;
    
    private boolean showMessage = false;
    
    //Will create 3 different instances of this class in the Main Class
    private final Image AK47 = new ImageIcon("/Users/21sleshko/NetBeansProjects/Raiders/AKBuy.png").getImage();

    private final Image M16 = new ImageIcon("/Users/21sleshko/NetBeansProjects/Raiders/M16Buy.png").getImage();

    private final Image rayK = new ImageIcon("/Users/21sleshko/NetBeansProjects/Raiders/RayBuy.png").getImage();
    
    private final Image pistol = new ImageIcon("/Users/21sleshko/NetBeansProjects/Raiders/RayBuy.png").getImage();

    //Has all the possible weapons on the ground...
    
    private final Image weaponOfChoice;
    
    private final Image[] choices = {pistol, AK47, M16, rayK};
    
    public GroundWeapon(float x, float y, Player player, int price, String name, Weapons weapons, int weapon) {
        super(x, y, player, price, name);
        this.weapons = weapons;
        this.weapon = weapon;
        weaponOfChoice = choices[weapon];
    }
    
    

    @Override
    public void update(long timePassed) {
        if(Proximity.checkProx(player.getX(), player.getY(), this.x, this.y)){
            showMessage = true;
        }else{
            showMessage = false;
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(weaponOfChoice, (int) x, (int) y, null);
        if(showMessage && weapons.getWeapon() != weapon){
            g.setFont(new Font("Avenir", Font.BOLD, 30));
            g.setColor(Color.WHITE);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2.drawString("Buy " + name + " for $" + price, 500, 500);
        }
    }

    @Override
    public void buy(){
        if(showMessage && player.getPoints() >= price && weapons.getWeapon() != weapon){
            //Audio.playSound("buySound.wav");
            player.subtractPoints(price);
            Audio a = new Audio("buySound.wav");
            weapons.setWeapon(weapon);
            weapons.resuply();
        }
        
    }
    
    
}
