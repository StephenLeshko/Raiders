
package raiders;

import raiders.HudElements.HudPowerUp;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.*;
import javax.swing.ImageIcon;
public class PowerUp extends GameObject{
    
    //Just going to use every single powerup in this class
    
    private final Image ins1 = new ImageIcon("/Users/21sleshko/NetBeansProjects/Raiders/InstaKill1.png").getImage();
    private final Image ins2 = new ImageIcon("/Users/21sleshko/NetBeansProjects/Raiders/InstaKill2.png").getImage();
    private final Image ins3 = new ImageIcon("/Users/21sleshko/NetBeansProjects/Raiders/InstaKill3.png").getImage();
    
    private final Image nuk1 = new ImageIcon("/Users/21sleshko/NetBeansProjects/Raiders/Nuke1.png").getImage();
    private final Image nuk2 = new ImageIcon("/Users/21sleshko/NetBeansProjects/Raiders/Nuke2.png").getImage();
    private final Image nuk3 = new ImageIcon("/Users/21sleshko/NetBeansProjects/Raiders/Nuke3.png").getImage();
    
    private final Image max1 = new ImageIcon("/Users/21sleshko/NetBeansProjects/Raiders/MaxAmmo1.png").getImage();
    private final Image max2 = new ImageIcon("/Users/21sleshko/NetBeansProjects/Raiders/MaxAmmo2.png").getImage();
    private final Image max3 = new ImageIcon("/Users/21sleshko/NetBeansProjects/Raiders/MaxAmmo3.png").getImage();

    private final Image pac1 = new ImageIcon("/Users/21sleshko/NetBeansProjects/Raiders/FreePack1.png").getImage();
    private final Image pac2 = new ImageIcon("/Users/21sleshko/NetBeansProjects/Raiders/FreePack2.png").getImage();
    private final Image pac3 = new ImageIcon("/Users/21sleshko/NetBeansProjects/Raiders/FreePack3.png").getImage();

    private final Image dou1 = new ImageIcon("/Users/21sleshko/NetBeansProjects/Raiders/DoublePoints1.png").getImage();
    private final Image dou2 = new ImageIcon("/Users/21sleshko/NetBeansProjects/Raiders/DoublePoints2.png").getImage();
    private final Image dou3 = new ImageIcon("/Users/21sleshko/NetBeansProjects/Raiders/DoublePoints3.png").getImage();

    private final Image per1 = new ImageIcon("/Users/21sleshko/NetBeansProjects/Raiders/FreePerk1.png").getImage();
    private final Image per2 = new ImageIcon("/Users/21sleshko/NetBeansProjects/Raiders/FreePerk2.png").getImage();
    private final Image per3 = new ImageIcon("/Users/21sleshko/NetBeansProjects/Raiders/FreePerk3.png").getImage();

    private final Image gun1 = new ImageIcon("/Users/21sleshko/NetBeansProjects/Raiders/FreeGun1.png").getImage();
    private final Image gun2 = new ImageIcon("/Users/21sleshko/NetBeansProjects/Raiders/FreeGun2.png").getImage();
    private final Image gun3 = new ImageIcon("/Users/21sleshko/NetBeansProjects/Raiders/FreeGun3.png").getImage();
    
    private final Image[] powerUps = {ins1, ins2, ins3, nuk1, nuk2, nuk3, max1, max2, max3, dou1, 
        dou2, dou3, pac1, pac2, pac3, per1, per2, per3, gun1, gun2, gun3};


    //PowerUp spawner class... Possibly
    //Or just the zombie class calls create powerUp; potentially creates a power
    
    private int index;
    
    //For just lasting around
    private long totalLifeTime;
    private long age;
    
    private final long duration = 30000;
    private long aliveAge = 0;
    
    private boolean pickedUp = false;
    private Player player;
    
    private boolean dead = false;
    
    
    private Cycler cycler;
    
    private Animations a;
    
    private Weapons weapons;
    private Round round;
    
    private Perks perks;
    
    private boolean effectOver = false;
    
    private int zombiesKilled = 0;
    
    private HudPowerUp hudPowerUp;
    
    private boolean alive = false;
    
    public Audio sound;
    
    private PCreator pCreator;
    private boolean exit = true;
            
            
    public PowerUp(float x, float y, ID id, int index, Cycler cycler, Player player, Weapons weapons, Perks perks, Round round, HudPowerUp hudPowerUp, PCreator pCreator){
        super(x, y, id);
        this.index = index;
        this.cycler = cycler;
        this.player = player;
        this.weapons = weapons;
        this.perks = perks;
        this.round = round;
        this.hudPowerUp = hudPowerUp;
        this.pCreator = pCreator;
        sound = new Audio("alivePow.wav");

        age = 0l;
        totalLifeTime = 30000;
        a = new Animations();
        a.addScene(powerUps[(3 * index)], 125l);
        a.addScene(powerUps[1 + (3 * index)], 125l);
        a.addScene(powerUps[2 + (3 * index)], 125l);
    }
    
    private void ins(long timePassed){
        if(pCreator.hasInsta && exit){
            pCreator.instaMax += 30000;
            effectOver = true;
            cycler.removeObject(this);
            exit = false;
//            System.out.println("Another insta collected");
            return;
        }
        
        if(!alive){
            alive = true;
            hudPowerUp.addPow(0);
            for(int i = 0; i < cycler.object.size(); i++){
                if(cycler.object.get(i).id == ID.Zombie){
                    Zombie z = (Zombie) cycler.object.get(i);
                    z.instaKillable();
                }
            }
        }
        exit = false;
//        System.out.println(pCreator.instaTimer);
        pCreator.hasInsta = true;
        hudPowerUp.instaOn = true;
        round.instaKill = true;
        pCreator.instaTimer += timePassed;
        if(pCreator.instaTimer >= pCreator.instaMax){
            Audio iOver = new Audio("instaOver.wav");
            for(int i = 0; i < cycler.object.size(); i++){
                if(cycler.object.get(i).id == ID.Zombie){
                    Zombie z = (Zombie) cycler.object.get(i);
                    z.healthIncrease();
                }
            }
            pCreator.instaMax = 30000;
            pCreator.instaTimer = 0l;
            round.instaKill = false;
            pCreator.hasInsta = false;
            hudPowerUp.instaOn = false;
            hudPowerUp.removePow(0);
            effectOver = true;
//            System.out.println("Instakill over");
            cycler.removeObject(this);
        }
    }
    
    private void dou(long timePassed){
        if(pCreator.hasDouble && exit){
            pCreator.doubleMax += 30000;
            effectOver = true;
            exit = false;
            cycler.removeObject(this);
//            System.out.println("Another double collected");
            return;
        }
        if(!alive){
//            System.out.println("Alive");
            alive = true;
            hudPowerUp.addPow(1);
        }
        exit = false;
        hudPowerUp.doubleOn = true;
        player.pointMult = 2; 
//        System.out.println(pCreator.doubleTimer);
        pCreator.hasDouble = true;
        
        pCreator.doubleTimer += timePassed;
        if(pCreator.doubleTimer >= pCreator.doubleMax){
//            System.out.println("Double points over");
            Audio dOver = new Audio("doubleOver.wav");
            pCreator.doubleMax = 30000;
            pCreator.doubleTimer = 0l;
            hudPowerUp.doubleOn = false;
            pCreator.hasDouble = false;
            hudPowerUp.removePow(1);
            player.pointMult = 1;
            effectOver = true;
            cycler.removeObject(this);
        }
    }
    
    private void nuk(){
        Audio nuk = new Audio("nukeCollect.wav");
        if(!round.roundChanging){
        for(int i = 0; i < cycler.object.size(); i++){
            if(cycler.object.get(i).id == ID.Zombie){
                cycler.removeObject(cycler.object.get(i));
                round.zombiesOnMap--;
                round.zombiesRemaining--;
                zombiesKilled++;
                i = i-1;
            }
        }
        for(int j = zombiesKilled; j < 24; j++){
            round.zombiesRemaining--;
            round.roundZombies++;
        }
        if(round.zombiesRemaining < 0){
            round.zombiesRemaining = 0;
        }
        round.nukePause = true;
        }
        //Also maybe just do something where the timer gets forced to be zero like...
        /*round.nukeTime = 0;*/
        player.addPoints(400);
        effectOver = true;
        cycler.removeObject(this);
    }
    
    private void max(){
        Audio max = new Audio("maxCollect.wav");
        weapons.resuply();
        effectOver = true;
        cycler.removeObject(this);
    }
    
    private void pac(){
        System.out.println("Packapunch");
//        weapons.packAPunch();
        effectOver = true;
        cycler.removeObject(this);
    }
    
    private void per(){
        if(perks.hasJug && perks.hasDouble && perks.hasQuick && perks.hasSpeed && perks.hasTiki && perks.hasStamin && perks.hasSuper){
            effectOver = true;
            cycler.removeObject(this);
            return;
        }
        if(perks.hasJug && perks.hasDouble && perks.hasQuick && perks.hasSpeed && perks.hasTiki && perks.hasStamin){
            perks.giveSuper();
            effectOver = true;
            cycler.removeObject(this);
            return;
        }else{
            Random r = new Random();
            int number = r.nextInt(6);
            if(number == 0 && !perks.hasJug){
                perks.giveJug();
                effectOver = true;
                cycler.removeObject(this);
                return;
            }
            if(number == 1 && !perks.hasDouble){
                perks.giveDouble();
                effectOver = true;
                return;
            }
            if(number == 2 && !perks.hasQuick){
                perks.giveQuick();
                effectOver = true;
                cycler.removeObject(this);
                return;
            }
            if(number == 3 && !perks.hasStamin){
                perks.giveStamin();
                effectOver = true;
                cycler.removeObject(this);
                return;
            }
            if(number == 4 && !perks.hasSpeed){
                perks.giveSpeed();
                effectOver = true;
                cycler.removeObject(this);
                return;
            }
            if(number == 5 && !perks.hasTiki){
                perks.giveTiki();
                effectOver = true;
                cycler.removeObject(this);
                return;
            }
            per();
        }
    }
    
    private void gun(){
        weapons.weaponChange();
        weapons.resuply();
        effectOver = true;
        cycler.removeObject(this);
    }
    
    public void update(long timePassed){
        
        if(!pickedUp){
        if(age < totalLifeTime){
            age += timePassed;
        }else{
            if(!dead){
                cycler.removeObject(this);
                dead = true;
            }
        }
        }
        if(Proximity.checkProx(player.getX(), player.getY(), this.x, this.y) && !dead && !pickedUp){
            //triggers the event
            sound.stopSound();
            Audio b2 = new Audio("collectPow.wav");
            pickedUp = true;
        }
        
        if(pickedUp && !effectOver){
            if(index == 0){
                ins(timePassed);
            }
            if(index == 1){
                nuk();
            }
            if(index == 2){
                max();
            }
            if(index == 3){
                dou(timePassed);
            }
            //Just for now; might be cool down the line, or add to EASY mode
//            if(index == 4){
//                pac();
//            }
//            if(index == 5){
//                per();
//            }
//            if(index == 6){
//                gun();
//            }
        }
        
        if(!pickedUp){
            a.update(timePassed);
        }
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if(!pickedUp){
        g2.drawImage(a.getImage(), (int) x, (int) y, null);}
    }
}
