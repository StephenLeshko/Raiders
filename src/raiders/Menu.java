
package raiders;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;


public class Menu {
    
    public boolean menuPresent = false;
    public boolean playSong = true;
    
    private JPanel panel;
    public Player player;
    public Round round;
    public Weapons weapons;
    public Perks perks;
    
    private final Image menuBG = new ImageIcon("/Users/21sleshko/NetBeansProjects/Raiders/MenuBackground.png").getImage();
    
    private Button newGame;
    private Button loadGame;
    public ButtonCycler menuCycler;
    private boolean[] noPerks = {false, false, false, false, false, false, false};
    
    public MouseListener mouseListener;
    private Audio song;
    
    private long waitTimer = 0;
    private final long waitMax = 500;
    
    public Menu(JPanel panel, Player player, Round round, Weapons weapons, Perks perks, ButtonCycler menuCycler){
        this.panel = panel;
        this.player = player;
        this.round = round;
        this.weapons = weapons;
        this.perks = perks;
        this.menuCycler = menuCycler;
        newGame = new Button(565, 400, 280, 50, "NEW GAME", this.panel, 5);
        loadGame = new Button(555, 460, 300, 50, "LOAD GAME", this.panel, 6);
        menuCycler.addObject(newGame);
        menuCycler.addObject(loadGame);
        menuCycler.setVisible();
        mouseListener = new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                for(int i = 0; i < menuCycler.buttons.size(); i++){
                    if(menuCycler.buttons.get(i).isVisible && menuCycler.buttons.get(i).isTouched){
                       actionSelect(menuCycler.buttons.get(i).getAction());
                       menuCycler.buttons.get(i).setColor(Color.RED);
                    }
                }
            }
            public void mousePressed(MouseEvent e) {
            }
            public void mouseReleased(MouseEvent e) {
            }
            public void mouseEntered(MouseEvent e) {
            }
            public void mouseExited(MouseEvent e) {
            }};

    }
    public void actionSelect(int index){
        if(index == 5) newGame();
        if(index == 6) loadGame();
    }
    
    public void playerCheck(){
        boolean playerPresent = false;
        for(int i = 0; i < round.cycler.object.size(); i++){
            if(round.cycler.object.get(i).id == ID.Player){
                playerPresent = true;
            }
        }
        if(!playerPresent){
            round.cycler.addObject(player);
            player.makeAlive();
        }
    }
    
    public void newGame(){
            System.out.println("Menu: New Game");
            playerCheck();
            player.setPoints(500);
            player.setMaxHealth();
            round.setRound(1);
            round.gameStart = true;
            weapons.setWeapon(0);
            weapons.currentMagAmmo[weapons.getWeapon()] = weapons.maxMagAmmo[weapons.getWeapon()];
            weapons.reserveAmmo[weapons.getWeapon()] = weapons.maxReserveAmmo[weapons.getWeapon()];
            round.zombiesRemaining = 6;
            
            perks.setPerks(noPerks);
            round.spawnTimeSet();
            
            waitTimer = 0;
            menuCycler.setInvisible();
            song.stopSound();
            menuPresent = false;
    }
    
    
    
    
    public void loadGame(){
        System.out.println("Menu: Load Game");
        try{
            Saver saver = new Saver(player, round, weapons, perks);
            GameSaveState saveData = saver.loadData();
            round.gameStart = true;
            playerCheck();
            player.setPoints(saveData.points);
            player.setMaxHealth();
            round.setRound(saveData.round);
            weapons.setWeapon(saveData.weapon);
            weapons.currentMagAmmo[weapons.getWeapon()] = saveData.magAmmo;
            weapons.reserveAmmo[weapons.getWeapon()] = saveData.reserveAmmo;
            round.zombiesRemaining = saveData.zombiesRemaining;
            round.roundZombies = round.getZombieCount() - saveData.zombiesRemaining;
            perks.setPerks(saveData.perksHad);
            round.spawnTimeSet();
            
            waitTimer = 0;
            menuCycler.setInvisible();
            song.stopSound();
            menuPresent = false;
        }catch(Exception ex){
        }
    }
    
    public void update(long timePassed){
        if(playSong){
            song = new Audio("ghosts14.wav");
            playSong = false;
        }
        if(!menuCycler.buttons.get(0).isVisible){
            waitTimer += timePassed;
            if(waitTimer >= waitMax){
                menuCycler.setVisible();
                waitTimer = 0;
            }
        }
//        menuCycler.setVisible();
        menuCycler.update(timePassed);
    }
    
    public void draw(Graphics g){
        g.drawImage(menuBG, 0, 0, null);
        menuCycler.draw(g);
    }
    
    
    
}
