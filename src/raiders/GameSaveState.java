
package raiders;

import java.io.Serializable;

public class GameSaveState implements Serializable{
    public int points;
    public int round;
    public int weapon;
    public int magAmmo;
    public int reserveAmmo;
    public int zombiesRemaining;
    public boolean[] perksHad;
    
    public GameSaveState(int points, int round, int weapon, int magAmmo, 
        int reserveAmmo, int zombiesRemaining, boolean[] perksHad){
        //Setting 
        this.points = points;
        this.round = round;
        this.weapon = weapon;
        this.magAmmo = magAmmo;
        this.reserveAmmo = reserveAmmo;
        this.zombiesRemaining = zombiesRemaining;
        this.perksHad = perksHad;
    }
    
    
}
