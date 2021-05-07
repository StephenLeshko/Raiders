
package raiders;

import raiders.HudElements.HudRound;
import java.util.Random;


public class Round {
    //This is going to be a huge class; very important for the MainGame
    private int round;
    private int[] totalZombieCount;
    public boolean roundChanging;
    private long timeWaited = 0;
    private final long breakTime;
    private final long colorTime;
    private long spawnTime;
    private long totalTime = 0;
    private long totalSpawnTime = 0;
    private long totalColorTime = 0;
    private Player player;
    public HudRound hudRound;
    Cycler cycler;
    public int roundZombies = 0;
    public int zombiesRemaining;
    public int zombiesOnMap = 0;
    private PCreator pCreator;
    public boolean instaKill = false;
    
    private Audio a;
    private Audio initial;
    public boolean gameStart = false;
    
    private long nukeTime = 0;
    public boolean nukePause = false;
    
    
    public Round(int round, Cycler cycler, HudRound hudRound, Player player, PCreator pCreator){
        this.round = round;
        this.cycler = cycler;
        this.hudRound = hudRound;
        this.player = player;
        this.pCreator = pCreator;
        //Might need to add powerups to contructor
        totalZombieCount = new int[] {6, 8, 13, 18, 24, 27, 28, 28, 29, 33, 34, 36, 39, 41, 44, 47, 
            50, 53, 56, 60, 63, 67, 71, 75, 80, 84, 89, 94, 99, 105, 110, 116, 122, 128, 134, 140, 
            147, 153, 160, 168, 175, 182, 190, 198, 206, 214, 222, 231, 240, 249};
            //50 rounds worth of stuff
            //Looks like the rounds get longer by a standard amount after a given point; can just use that in endgame
        roundChanging = false;
        breakTime = 15000;
        colorTime = 2000;
        //Should have a function that can decrease this value in order to make it harder
        spawnTimeSet();
        zombiesRemaining = totalZombieCount[round - 1];
    }
    
    public void zombieDied(){
        zombiesRemaining--;
        zombiesOnMap--;
    }
    
    public void setRound(int round){
        this.round = round;
    }
    
    public int getZombieCount(){
        return totalZombieCount[round - 1];
    }
    
    public void roundReset(){
        totalSpawnTime = 0;
        zombiesRemaining = 0;
        zombiesOnMap = 0;
        roundZombies = 0;
        initial.stopSound();
        nukePause = false;
        instaKill = false;
    }
    public void stopChange(){
        if(roundChanging){
           roundChanging = false;
           a.stopSound();
           this.round++;
           zombiesRemaining = totalZombieCount[round - 1];
           totalColorTime = 0;
           totalTime = 0;
           hudRound.changeColorToRed();
        }
    }
            
    
    
    private synchronized void spawn(long timePassed){
        if(nukePause){
            nukeTime += timePassed;
            if(nukeTime >= 5000){
                nukePause = false;
                nukeTime = 0;
            }
        }else{
        totalSpawnTime += timePassed;
        if(roundZombies < totalZombieCount[round - 1] && zombiesOnMap <= 24){
        if(totalSpawnTime >= spawnTime){
            Random rx = new Random();
            Random ry = new Random();
            float zx = rx.nextInt(1366);
            float zy = ry.nextInt(685);
            Zombie zombie = new Zombie(zx, zy, ID.Zombie, player, cycler, this, pCreator);
            if(!instaKill){
            zombie.healthIncrease();
            }else{
                zombie.instaKillable();
            }
            cycler.addObject(zombie);
            roundZombies++;
            zombiesOnMap++;
            totalSpawnTime = 0;
        }
        }else{
            totalSpawnTime = 0;
        }
        }
    }
    public void update(long timePassed){
        if(gameStart){
            initial = new Audio("intialRoundChange.wav");
            gameStart = false;
        }
        checkRoundEnd();
        if(!roundChanging){
            spawn(timePassed);
        }else{
            changeRound(timePassed);
        }
    }
    
    //This is a hefty method; waits a certain amount of time before resetting roundChanging to true; changes color
    private synchronized void changeRound(long timePassed){
        totalTime += timePassed;
        totalColorTime += timePassed;
        if(totalColorTime >= colorTime){
            hudRound.changeColor();
            totalColorTime = 0;
        }
        if(totalTime >= breakTime){
            totalTime = 0;
            pCreator.resetPow();
            increaseRound();
            spawnTimeSet();
            zombiesRemaining = totalZombieCount[round - 1];
            hudRound.changeColorToRed();
            roundChanging = false;
        }
    }
    private void checkRoundEnd(){
        if(!roundChanging){
            if(zombiesRemaining == 0){
            System.out.println("Round change time");
            a = new Audio("fullRoundChange.wav");
//            Audio.playSound("fullRoundChange.wav");
            roundChanging = true;
            zombiesRemaining = 0;
            zombiesOnMap = 0;
            roundZombies = 0;
            }
        }
    }
    
    public void spawnTimeSet(){
        spawnTime = 2200 - ((round - 1) * 90);
        
        if(spawnTime < 500){
            spawnTime = 500;
        }
        
    }
        
    
    public void increaseRound(){
        round++;
        
    }
    
    public int getRound(){
        return round;
    }
}
    
    

