/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raiders;

import java.awt.event.*;
import javax.swing.*;
import java.awt.event.KeyEvent;



//Has weapons, shoot, etc.
public class Weapons{
    
    public Cycler cycler;
    
    //Weapon characteristics
    public int[] reserveAmmo;
    public final int[] maxReserveAmmo;
    public int[] maxMagAmmo;
    public int[] damage;
    public String[] shotSounds;
    
    private String[] reloadSounds;
    private String[] reloadSounds2;
    private String[] reloadSounds3;
    private String[][] allReloadSounds;
    private int soundIndex = 0;
    
    
    public long[] reloadTimes;
    public boolean[] isFullAuto;
    public long fireRates[];
    long initialTime;
    
    int bulletCount;

    private Player player;
    public int[] currentMagAmmo;
    
    private int weapon;
    private boolean canShoot = true;
    //Used for reload; might need another for full auto
    private long reloadTimePassed;
    private boolean canReload;
    private boolean fullFire = false;
    private long shotTimePassed;
    
    private int dMult = 1;
    
    private double rMult = 1;
    
    private Pause pause;
    private Menu menu;
    
    Action reload;
    Action shootPressed;
    Action changeWeapon;
    Action shootReleased;
     
    //index 0: pistol
    //index 1: M16
    //index 2: AK47
    //index 3: Raygun
    
    public Weapons(Cycler cycler){
        this.cycler = cycler;
        weapon = 0;
        reserveAmmo = new int[] {80, 240, 300, 350};
        maxMagAmmo = new int[] {8, 30, 30, 35};
        currentMagAmmo = new int[] {maxMagAmmo[0], maxMagAmmo[1], maxMagAmmo[2], maxMagAmmo[3]};

        damage = new int[] {30, 200, 225, 1300};
        shotSounds = new String[] {"pistolShot.wav", "akShot.wav", "m16Shot.wav", "rayShot.wav"};
        
        reloadSounds = new String[] {"pistolReload.wav", "akReload.wav", "m16Reload.wav", "rayReload.wav"};
        reloadSounds2 = new String[] {"pistolReload2.wav", "akReload2.wav", "m16Reload2.wav", "rayReload2.wav"};
        reloadSounds3 = new String[] {"pistolReload3.wav", "akReload3.wav", "m16Reload3.wav", "rayReload3.wav"};
        allReloadSounds = new String[][] {reloadSounds, reloadSounds2, reloadSounds3};
        
        reloadTimes = new long[] {1000, 1500, 1700, 3000};
        isFullAuto = new boolean[] {false, true, true, true};
        fireRates = new long[] {0, 100, 75, 40};
        canReload = true;
        shotTimePassed = 0;
        maxReserveAmmo = new int[] {80, 240, 300, 350};
        
        bulletCount = 0;
    }
    
    public void setMenu(Menu menu){
        this.menu = menu;
    }
    
    public void setPause(Pause pause){
        this.pause = pause;
    }
    
    //Perk Updates
    public void updateReload(){
        rMult = 0.7;
        soundIndex = 1;
    }
    public void updateDamage(){
        dMult = 2;
    }
    
    public boolean atMax(){
        if(currentMagAmmo[weapon] == maxMagAmmo[weapon] && reserveAmmo[weapon] == maxReserveAmmo[weapon]){
            return true;
        }
        return false;
    }
    
    public void resuply(){
        currentMagAmmo[weapon] = maxMagAmmo[weapon];
        reserveAmmo[weapon] = maxReserveAmmo[weapon];
    }
    
    public void updateSuper(boolean hasSpeed, boolean hasDouble, boolean hasSuper){
        if(hasSuper){
        if(hasSpeed){
            rMult = 0.5;
            soundIndex = 2;
        }
        if(hasDouble){
            dMult = 3;
        }}
    }
    
    public void shootFullAuto(long timePassed){
        
        
        if(player != null){
        if(currentMagAmmo[weapon] != 0 && canShoot && fireRates[weapon] != 0){
        if(fullFire){
            shotTimePassed += timePassed;
        }
        if(shotTimePassed >= fireRates[weapon]){
            shotTimePassed = 0;
            createBullet();
        }
        }else{
            fullFire = false;
        }}else{findPlayer();}
    }
    
    
    public void actionSetUp(JPanel panel){
        reload = new Reload();
        shootPressed = new ShootPressed();
        changeWeapon = new ChangeWeapon();
        shootReleased = new ShootReleased();
                
        panel.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_F, 0, false), "shootPressed");
        panel.getActionMap().put("shootPressed", shootPressed);
        panel.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_F, 0, true), "shootReleased");
        panel.getActionMap().put("shootReleased", shootReleased);
        panel.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_R, 0), "reload");
        panel.getActionMap().put("reload", reload);
        //Just for testing purposes; probably going to need a weapon for each thing
        panel.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_G, 0), "changeWeapon");
        panel.getActionMap().put("changeWeapon", changeWeapon);
    }
    
    public void findPlayer(){
        for(int i = 0; i < cycler.object.size(); i++){
            if(cycler.object.get(i).getId() == ID.Player){
                player = (Player) cycler.object.get(i);
                break;
            }}}
    
    public class ShootReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            //That should be all I have to do
            fullFire = false;
        }
        
    }
    
    private void createBullet(){
        currentMagAmmo[weapon] += -1;
        float xSpot = (float) (player.x + 41 + (41 * Math.cos(player.getAngle())));
        float ySpot = (float) (player.y + 51 + (17 * Math.sin(player.getAngle())));
        GameObject bullet = new Bullet(xSpot, ySpot, ID.Bullet, cycler, (damage[weapon] * dMult));
        cycler.addObject(bullet);
        int bulletV = 30;
        bullet.vx = (float) ((bulletV) * Math.cos(player.getAngle()));
        bullet.vy = (float) ((bulletV) * Math.sin(player.getAngle()));
//        Audio.playSound(shotSounds[weapon]);
        Audio a = new Audio(shotSounds[weapon]);
    }
    
    public class ShootPressed extends AbstractAction{
        
        @Override
        public synchronized void actionPerformed(ActionEvent e) {
            //If a player is actually present
            if(!pause.paused && !menu.menuPresent){
            if(player != null){
                if(currentMagAmmo[weapon] != 0 && canShoot){
                    if(!isFullAuto[weapon]){
                    createBullet();
                    }else{
                        //is a full auto weapon
                        fullFire = true;
                    }
                }else{
            }
            }else{ findPlayer();}
        }
        }
    }
    
    public class Reload extends AbstractAction{
        @Override
        public synchronized void actionPerformed(ActionEvent e){
            //Just missing the time delay & sound
            if(!pause.paused && !menu.menuPresent){
            if(canReload && currentMagAmmo[weapon] != maxMagAmmo[weapon]){
            if(reserveAmmo[weapon] > 0){
                canShoot = false;
                canReload = false;
                //Will need to adjust the sound depending on perk`
//                Audio.playSound(reloadSounds[weapon]);
                Audio b = new Audio(allReloadSounds[soundIndex][weapon]);
                initialTime = System.currentTimeMillis();
                while((reloadTimes[weapon] * rMult) > reloadTimePassed){
                    reloadTimePassed = System.currentTimeMillis() - initialTime;
                }
                if(reserveAmmo[weapon] < (maxMagAmmo[weapon] - currentMagAmmo[weapon])){
                    currentMagAmmo[weapon] += reserveAmmo[weapon];
                    reserveAmmo[weapon] = 0;
                    canShoot = true;
                    reloadTimePassed = 0;
                    canReload = true;
                }else{
                    reserveAmmo[weapon] = reserveAmmo[weapon] - (maxMagAmmo[weapon] - currentMagAmmo[weapon]);
                    currentMagAmmo[weapon] = maxMagAmmo[weapon];
                    canShoot = true;
                    reloadTimePassed = 0;
                    canReload = true;
                }
                for(int i = 0; i < cycler.object.size(); i++){
                    if(cycler.object.get(i).id == ID.Bullet){
                        cycler.removeObject(cycler.object.get(i));
                        i = i-1;
                    }
                }
            }
//            System.out.print("Current Mag: " + currentMagAmmo + " ");
//            System.out.println("Reserves: " + reserveAmmo[weapon]);
            } 
        }
        }
    }
    
    public void weaponChange(){
            weapon++;
            reloadTimePassed = 0;
            if(weapon > 3){
                weapon = 0;
            }
    }
    
    //Not perminant
    public class ChangeWeapon extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!pause.paused && !menu.menuPresent){
//            weaponChange();
        }}
    }
    
    public void packAPunch(){
        this.weapon = weapon + 4;
    }
    
    //Use for the weapon buys on the ground
    public void setWeapon(int weapon){
        this.weapon = weapon;
    }
    
    public int getWeapon(){
        return weapon;
    }
}
