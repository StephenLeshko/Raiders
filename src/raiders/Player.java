
package raiders;

import java.awt.*;
import java.awt.geom.AffineTransform;
import javax.swing.*;


public class Player extends GameObject {
    
    private Image p19111 = new ImageIcon("/Users/21sleshko/NetBeansProjects/Raiders/Pistol1.png").getImage();
    private Image p19112 = new ImageIcon("/Users/21sleshko/NetBeansProjects/Raiders/Pistol2.png").getImage();
    private Image p19113 = new ImageIcon("/Users/21sleshko/NetBeansProjects/Raiders/Pistol3.png").getImage();
    
    private Image pAK471 = new ImageIcon("/Users/21sleshko/NetBeansProjects/Raiders/AK471.png").getImage();
    private Image pAK472 = new ImageIcon("/Users/21sleshko/NetBeansProjects/Raiders/AK472.png").getImage();
    private Image pAK473 = new ImageIcon("/Users/21sleshko/NetBeansProjects/Raiders/AK473.png").getImage();
    
    private Image pM161 = new ImageIcon("/Users/21sleshko/NetBeansProjects/Raiders/M161.png").getImage();
    private Image pM162 = new ImageIcon("/Users/21sleshko/NetBeansProjects/Raiders/M162.png").getImage();
    private Image pM163 = new ImageIcon("/Users/21sleshko/NetBeansProjects/Raiders/M163.png").getImage();
    
    private Image pRay1 = new ImageIcon("/Users/21sleshko/NetBeansProjects/Raiders/Ray1.png").getImage();
    private Image pRay2 = new ImageIcon("/Users/21sleshko/NetBeansProjects/Raiders/Ray2.png").getImage();
    private Image pRay3 = new ImageIcon("/Users/21sleshko/NetBeansProjects/Raiders/Ray3.png").getImage();
    
        
    private final KeyInput input;
    
    private final Weapons weapons;
    
    public Animations a1;
    public Animations a2;
    public Animations a3;
    public Animations a4;
    
    public Animations p;

    private float speed;
    
    private int maxHealth = 150;
    
    private int health;
    private int points;
    
    private AffineTransform position = AffineTransform.getTranslateInstance(x, y);
    
    private AffineTransform rotation;
    
    boolean regeneration = false;
    
    private int regenTimer;
    
    private int maxRegenTimer;
    
    private Cycler cycler;
    private boolean dead = false;
    
    public int pointMult = 1;
    
    private JPanel panel;
    private float mouseX;
    private float mouseY;
    
    
    private Animations[] animations;

    public Player(float x, float y, ID id, KeyInput input, Cycler cycler, Weapons weapons){
        super(x, y, id);
        this.input = input;
        this.cycler = cycler;
        this.weapons = weapons;
        a1 = new Animations();
        a1.addScene(p19113, 125l);
        a1.addScene(p19111, 125l);
        a1.addScene(p19112, 125l);
        
        a2 = new Animations();
        a2.addScene(pAK473, 125l);
        a2.addScene(pAK471, 125l);
        a2.addScene(pAK472, 125l);
        
        a3 = new Animations();
        a3.addScene(pM163, 125l);
        a3.addScene(pM161, 125l);
        a3.addScene(pM162, 125l);
        a4 = new Animations();
        a4.addScene(pRay3, 125l);
        a4.addScene(pRay1, 125l);
        a4.addScene(pRay2, 125l);
        
        animations = new Animations[] {a1, a2, a3, a4};
        
        
        rotation = AffineTransform.getRotateInstance(getAngle(), 46, 46);
        speed = 4;
        health = maxHealth;
        maxRegenTimer = 5000;
        points = 500;
        
    }
    public void setMaxHealth(){
        health = maxHealth;
    }
    public void setPanel(JPanel panel){
        this.panel = panel;
    }
    
    
    public void updateRegen(){
        maxRegenTimer = 3500;
    }
    
    public void updateSpeed(){
        speed = 6;
        for(Animations a : animations){
            a.changeASpeed(83l);
        }
    }
    public void setPoints(int points){
        this.points = points;
    }
    
    public void updateSuper(boolean hasJug, boolean hasQuick, boolean hasStamin, boolean hasSuper){
        if(hasSuper){
        if(hasJug){
            maxHealth = 300;
            health = 300;
        }
        if(hasQuick){
            maxRegenTimer = 2750;
        }
        if(hasStamin){
            speed = 7;
            for(Animations a : animations){
                a.changeASpeed(71l);
            }
        }}
    }
    
    public void updateHealth(){
        maxHealth = 250;
        health = 250;
    }
    
    
    public void addPoints(int points){
        this.points += (points * pointMult);
    }
    
    public void subtractPoints(int points){
        this.points += -1*points;
    }
    
    public int getPoints(){
        return this.points;
    }
    
    
    public void getHit(){
        regeneration = false;
        regenTimer = 0;
        if(health != 0){
            health = health - 50;
        }
    }
    
    private void death(){
        if(health <= 0){
            health = 0;
            this.setX(1000);
            this.setY(2000);
            cycler.removeObject(this);
            dead = true;
        }
    }
    
    private void healthRegeneration(long timePassed){
        if(health < maxHealth){
            regenTimer += timePassed;
            regeneration = true;
            if(regeneration && regenTimer >= maxRegenTimer){
                health = maxHealth;
                regenTimer = 0;
                regeneration = false;
            }
        }
    }
    public void makeAlive(){
        dead = false;
        x = 400;
        y = 300;
    }
    
    
    @Override
    public void update(long timePassed) {
        //Velocity Changer
        healthRegeneration(timePassed);
        death();
        if(!dead){
        if(input.keys[0] && input.keys[2]){
            vx = speed / (float) Math.sqrt(2);
            vy = -speed / (float) Math.sqrt(2);
        }else if(input.keys[0] && input.keys[3]){
            vx = speed / (float) Math.sqrt(2);
            vy = speed / (float) Math.sqrt(2);
        }else if(input.keys[1] && input.keys[2]){
            vx = -speed / (float) Math.sqrt(2);
            vy = -speed / (float) Math.sqrt(2);
        }else if(input.keys[1] && input.keys[3]){
            vx = -speed / (float) Math.sqrt(2);
            vy = speed / (float) Math.sqrt(2);
        }else{
        if(input.keys[0]){ vx = speed;}
        else if(input.keys[1]) {vx = -speed;}
        else if(!input.keys[0] && !input.keys[1]) {vx = 0;}
        if(input.keys[3]) {vy = speed;}
        else if(input.keys[2]) {vy = -speed;}
        else if(!input.keys[2] && !input.keys[3]) {vy = 0;}
        }
        x += vx;
        y += vy;
        weapons.shootFullAuto(timePassed);
        //Position setter
        position = AffineTransform.getTranslateInstance(x, y);
        rotation.setToRotation(getAngle(), 46, 46);
        position.concatenate(rotation);
        
//        p.update(timePassed);
        //Animates the object
        if(input.keys[0] || input.keys[1] || input.keys[2] || input.keys[3]){
            animations[weapons.getWeapon()].update(timePassed);
        }
        }
    }
    //Returns an angle in radians
    public double getAngle(){
        //Center based
//        cDistancey = centery - MouseInfo.getPointerInfo().getLocation().y;
//        cDistancex = MouseInfo.getPointerInfo().getLocation().x - centerx;
            
        return (double) -1 * Math.atan2((y + 46) - MouseInfo.getPointerInfo().getLocation().y, 
            MouseInfo.getPointerInfo().getLocation().x - (x + 46));
    }
    
    public int getHealth(){
        return health;
    }
    
    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(animations[weapons.getWeapon()].getImage(), position, null);
        
    }

}
