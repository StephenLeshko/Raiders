/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raiders;

import java.io.*;

/**
 *
 * @author ObadegaJ
 */
public class Saver {
    
    public Player player;
    public Round round;
    public Weapons weapons;
    public Perks perks;
    
    public Saver(Player player, Round round, Weapons weapons, Perks perks){
        this.player = player;
        this.round = round;
        this.weapons = weapons;
        this.perks = perks;
    }
    
    public void saveData() throws FileNotFoundException, IOException, ClassNotFoundException{
        
        GameSaveState gameSaveState = new GameSaveState(player.getPoints(), round.getRound(), 
        weapons.getWeapon(), weapons.currentMagAmmo[weapons.getWeapon()], weapons.reserveAmmo[weapons.getWeapon()],
        round.zombiesRemaining, perks.getPerks());
        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("SaveFile.txt"));
        output.writeObject(gameSaveState);
        
    }
    
    public GameSaveState loadData() throws FileNotFoundException, IOException, ClassNotFoundException{
        ObjectInputStream input = new ObjectInputStream(new FileInputStream("SaveFile.txt"));
        GameSaveState gameSaveState = (GameSaveState) input.readObject();
        return gameSaveState;
    }
    
}
