
package raiders;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.util.Random;
import javax.swing.ImageIcon;

public class Zombie extends GameObject{

    //Zombie Images
    private Image zR1 = new ImageIcon("/Users/21sleshko/NetBeansProjects/Raiders/ZombieR1.png").getImage();
    private Image zR2 = new ImageIcon("/Users/21sleshko/NetBeansProjects/Raiders/ZombieR2.png").getImage();
    private Image zR3 = new ImageIcon("/Users/21sleshko/NetBeansProjects/Raiders/ZombieR3.png").getImage();
       
    //Important Values
    private int health;
    private Round round;
    private float speed;
        
    private final Player player;
    
    private Animations a;
    
    private final int zDamage = 50;

    private AffineTransform position;
    private AffineTransform rotation;
    
    private Cycler cycler;
    
    private boolean dead = false;
    
    private double attackSpeed = 1;
    
    private final int totalAttackLength = 800;
    
    private int attackTimer;

    private PCreator pCreator;
    private long animationTime = 0;

    public Zombie(float x, float y, ID id, Player player, Cycler cycler, Round round, PCreator pCreator) {
        super(x, y, id);
        this.player = player;
        this.cycler = cycler;
        this.round = round;
        this.pCreator = pCreator;
        speed = 2;
        speedIncrease();
        setAnimationTime();
        a = new Animations();
        a.addScene(zR1, animationTime);
        a.addScene(zR2, animationTime);
        a.addScene(zR3, animationTime);
        position = AffineTransform.getTranslateInstance(x, y);
        rotation = AffineTransform.getRotateInstance(getAngle(), 37.5, 37.5);
        attackTimer = totalAttackLength;
        health = 100;
    }
    
    public void setAnimationTime(){
        animationTime = (long) (500 * (1.0 / speed));
    }
    
    public void speedIncrease(){
        if(round.getRound() > 3){
            speed = (float) 2.5;
        } if(round.getRound() > 4){
            speed = (float) 3.2;
        } if(round.getRound() > 10){
            speed = (float) 4;
        } if(round.getRound() > 18){
            speed = (float) 4.5;
        } if(round.getRound() > 35){
            speed = (float) 5.1;
        }
    }
    
    public void healthIncrease(){
        health = 100 * round.getRound();
    }
    
    public void instaKillable(){
        health = 10;
    }
    
    public void getDamaged(int damage){
        health += (damage * -1);
//        System.out.println("Zombie take damage");
        player.addPoints(10);
    }
            
    
    public double getDistance(){
        return Math.sqrt(Math.pow(((double) (player.getX() + 8) - (double) getX()), 2) + Math.pow(((double) (player.getY() + 8) - (double) getY()), 2));
    }
    
    private void attack(long timePassed){
        //Change up the attack distance
        if(getDistance() < 35){
            //Also might want to quick adjust speed; or do some other stuff w/ animations
            attackTimer += timePassed;
            vx = (float) (attackSpeed * ((player.getX() - getX()) / getDistance()));
            vy = (float) (attackSpeed * ((player.getY() - getY()) / getDistance()));
            if(attackTimer >= totalAttackLength){
                player.getHit();
                attackTimer = 0;
                Random r = new Random();
                int number = r.nextInt(5);
                Audio a;
                if(number == 0){
//                    Audio.playSound("zHit1.wav");
                    a = new Audio("zHit1.wav");
                }else if(number == 1){
//                    Audio.playSound("zHit2.wav");
                    a = new Audio("zHit2.wav");
                }else if(number == 2){
//                    Audio.playSound("zHit3.wav");
                    a = new Audio("zHit3.wav");

                }else if(number == 3){
//                    Audio.playSound("zHit4.wav");
                    a = new Audio("zHit4.wav");

                }else if(number == 4){
//                    Audio.playSound("zHit5.wav");
                    a = new Audio("zHit5.wav");

                }else if(number == 5){
//                    Audio.playSound("zHit6.wav");
                    a = new Audio("zHit6.wav");    
                }
            }
        }else{
//            attackTimer = 0;
        }
    }
    
    private void death(){
        if(health < 0){
            //Possibly have something similar to animations that starts a timer that quickly causes an animation; for now no
            //Spawns a powerup in this location; gives it a random number; powerups have array of "effects"
            pCreator.spawnP(x, y);
            cycler.removeObject(this);
            round.zombieDied();
            dead = true;
            player.addPoints(60);
            Random r = new Random();
            int number = r.nextInt(3);
            Audio b;
            if(number == 0){
                b = new Audio("zDeath.wav");
//                Audio.playSound("zDeath.wav");
            }else if(number == 1){
//                Audio.playSound("zDeath2.wav");
                b = new Audio("zDeath2.wav");
 
            }else if(number == 2){
//                Audio.playSound("zDeath3.wav");
                b = new Audio("zDeath3.wav");

            }else if(number == 3){
//                Audio.playSound("zDeath4.wav");
                b = new Audio("zDeath4.wav");

            }else if(number == 4){
//                Audio.playSound("zDeath5.wav");
                b = new Audio("zDeath5.wav");

            }
            
            
        }
    }
    @Override
    public void update(long timePassed) {
        //Speed is a float
        if(!dead){
        Random r = new Random();
        int number = r.nextInt(2000);
        Audio c;
        if(number == 1){
//            Audio.playSound("zSound1.wav");
            c = new Audio("zSound1.wav");
        }else if(number == 2){
//            Audio.playSound("zSound2.wav");
            c = new Audio("zSound2.wav");

        }else if(number == 3){
//            Audio.playSound("zSound3.wav");
            c = new Audio("zSound3.wav");

        }else if(number == 4){
//            Audio.playSound("zSound4.wav");
            c = new Audio("zSound4.wav");

        }else if(number == 5){
//            Audio.playSound("zSound5.wav");
            c = new Audio("zSound5.wav");

        }else if(number == 6){
//            Audio.playSound("zSound6.wav");
            c = new Audio("zSound6.wav");

        }
        vx = (float) (speed * ((player.getX() - getX()) / getDistance()));
        vy = (float) (speed * ((player.getY() - getY()) / getDistance()));
        
        attack(timePassed);
        
        x += vx;
        y += vy;
        
        //Always do these at the end
        position.setToTranslation(x, y);
        rotation.setToRotation(getAngle(), 37.5, 37.5);
        position.concatenate(rotation);
        a.update(timePassed);
        death();
        }
    }
    public double getAngle(){
        return (double) Math.atan2(player.getY() - this.y, player.getX() - this.x);
    }
    
    @Override
    public void draw(Graphics g) {
        if(!dead){
        Graphics2D g3 = (Graphics2D) g;
        g3.drawImage(a.getImage(), position, null);
        }
    }
}
