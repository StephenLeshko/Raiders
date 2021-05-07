
package raiders;

import raiders.HudElements.HudPowerUp;
import java.util.Random;


public class PCreator {
    private Cycler cycler;
    private Player player;
    private double pMult = 1.0;
    private int powMax = 4;
    
    public long instaTimer = 0;
    public long instaMax = 30000;
    
    public long doubleTimer = 0;
    public long doubleMax = 30000;
    
    public boolean hasInsta = false;
    
    public boolean hasDouble = false;
    
    
    private int roundPows = 0;

    
    private Weapons weapons;
    
    private Perks perks;
    private Round round;
    public HudPowerUp hudPowerUp;
    
    public PCreator(Cycler cycler, Player player, Weapons weapons, HudPowerUp hudPowerUp){
        this.cycler = cycler;
        this.player = player;
        this.weapons = weapons;
        this.hudPowerUp = hudPowerUp;
    }
    
    public void setPerks(Perks perks){
        this.perks = perks;
    }
    public void setRound(Round round){
        this.round = round;
    }
    
    public void increasePow(){
        roundPows++;
    }
    
    public void resetPow(){
        roundPows = 0;
    }
    public void clear(){
        hasInsta = false;
        hasDouble = false;
        player.pointMult = 1;
        instaTimer = 0;
        doubleTimer = 0;
        roundPows = 0;
        hudPowerUp.removePow(0);
        hudPowerUp.removePow(1);

    }
    
    
    
    public void updateProb(){
        pMult = 2.0;
        powMax = 6;
    }
    
    public void updateSuper(boolean hasSuper){
        if(hasSuper){
            pMult = 2.5;
            powMax = 7;
        }
    }
    
    public void spawnP(float x, float y){
        Random number = new Random();
        int n = number.nextInt((int)(171 / pMult));
//        n = 0;
        
        //Just for now...will change later
//        if(n >= 100){
//            n = 0;
//        }else{
//            n = 3;
//        }
//        System.out.println(n);
        if((n >= 0 && n <= 3) && roundPows != powMax && perks != null && round != null){
            increasePow();
            PowerUp powerUp = new PowerUp(x, y, ID.PowerUp, n, cycler, player, weapons, perks, round, hudPowerUp, this);
            cycler.addObject(powerUp);
        }
    }
    
    
    
    
}
