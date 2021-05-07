
package raiders;

import raiders.HudElements.HudPerks;
import java.awt.Image;
import javax.swing.ImageIcon;


public class Perks {
    //Idek if I actually need this, for now I wil keep
    public String[] names;
    
    private Player player;
    private Weapons weapons;
    private HudPerks hudPerks;
    private PCreator pCreator;
    public boolean hasJug = false;
    public boolean hasQuick = false;
    public boolean hasSpeed = false;
    public boolean hasStamin = false;
    public boolean hasDouble = false;
    public boolean hasTiki = false;
    public boolean hasSuper = false;

    public Perks(Player player, Weapons weapons, HudPerks hudPerks, PCreator pCreator){
        this.player = player;
        this.weapons = weapons;
        this.hudPerks = hudPerks;
        this.pCreator = pCreator;
        names = new String[] {"Juggernog", "Quick Revive", "Speed Cola", "Stamin-Up", "Double Tap", 
            "Tiki Tiki", "Supercharge"};
    }
    //Used for save/load
    public boolean[] getPerks(){
        boolean[] perkArray = {false, false, false, false, false, false, false};
        if(hasJug) perkArray[0] = true;
        if(hasQuick) perkArray[1] = true;
        if(hasSpeed) perkArray[2] = true;
        if(hasStamin) perkArray[3] = true;
        if(hasDouble) perkArray[4] = true;
        if(hasTiki) perkArray[5] = true;
        if(hasSuper) perkArray[6] = true;
        return perkArray;
    }
    
    public void setPerks(boolean[] perkArray){
        if(perkArray[0]) giveJug();
        if(perkArray[1]) giveQuick();
        if(perkArray[2]) giveSpeed();
        if(perkArray[3]) giveStamin();
        if(perkArray[4]) giveDouble();
        if(perkArray[5]) giveTiki();
        if(perkArray[6]) giveSuper();
        
    }
    
    
    public void giveJug(){
        hasJug = true;
        player.updateHealth();
        player.updateSuper(hasJug, hasQuick, hasStamin, hasSuper);
        hudPerks.addPerk(0);
    }
    public void giveQuick(){
        hasQuick = true;
        player.updateRegen();
        player.updateSuper(hasJug, hasQuick, hasStamin, hasSuper);
        hudPerks.addPerk(4);

    }
    public void giveSpeed(){
        hasSpeed = true;
        weapons.updateReload();
        weapons.updateSuper(hasSpeed, hasDouble, hasSuper);
        hudPerks.addPerk(3);

    }
    public void giveStamin(){
        hasStamin = true;
        player.updateSpeed();
        player.updateSuper(hasJug, hasQuick, hasStamin, hasSuper);
        hudPerks.addPerk(2);
    }
    public void giveDouble(){
        hasDouble = true;
        weapons.updateDamage();
        weapons.updateSuper(hasSpeed, hasDouble, hasSuper);
        hudPerks.addPerk(1);
    }
    
    public void giveTiki(){
        hasTiki = true;
        pCreator.updateProb();
        pCreator.updateSuper(hasSuper);
        hudPerks.addPerk(5);
    }
    public void removePerks(){
        hasJug = false;
        hasQuick = false;
        hasSpeed = false;
        hasStamin = false;
        hasDouble = false;
        hasTiki = false;
        hasSuper = false;
        hudPerks.removeAll();
    }
    
    
    public void giveSuper(){
        hasSuper = true;
        player.updateSuper(hasJug, hasQuick, hasStamin, hasSuper);
        weapons.updateSuper(hasSpeed, hasDouble, hasSuper);
        pCreator.updateSuper(hasSuper);
        hudPerks.addPerk(6);
    }
    
    
    
    
}
