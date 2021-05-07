
package raiders;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;


public class Pause {
    
    public boolean paused = false;
    private final Image pBG = new ImageIcon("/Users/21sleshko/NetBeansProjects/Raiders/BlackBG.png").getImage();

    Action pauseGame;
    
    private long pauseBlink = 0;
    private long pauseClosed = 0;
    private boolean showPauseMessage = false;
    private ButtonCycler buttonCycler;
    
    private JPanel panel;
    
    public MouseListener mouseListener;
    private Menu menu;
    
    private boolean showControls = false;
    private long controlsTimer;
    private final long controlsDuration = 3000;
    
    private Player player;
    private Round round;
    private Weapons weapons;
    private Perks perks;
    private Cycler cycler;
    private PCreator pCreator;
    public Pause(JPanel panel, ButtonCycler buttonCycler, Player player, Round round, Weapons weapons, Perks perks, 
            Cycler cycler, PCreator pCreator){
        this.panel = panel;
        this.buttonCycler = buttonCycler;
        this.player = player;
        this.round = round;
        this.weapons = weapons;
        this.perks = perks;
        this.cycler = cycler;
        this.pCreator = pCreator;
//        button = new ButtonTest(30, 25, 120, 50, "QUIT", this.panel);
//        button2 = new ButtonTest(200, 25, 310, 50, "SAVE & QUIT", this.panel);
        actionSetUp();
        Button quit = new Button(30, 25, 120, 50, "QUIT", this.panel, 0);
        Button save = new Button(160, 25, 308, 50, "SAVE & QUIT", this.panel, 1);
        Button controls = new Button(478, 25, 275, 50, "CONTROLS", this.panel, 2);
        buttonCycler.addObject(quit);
        buttonCycler.addObject(save);
        buttonCycler.addObject(controls);
        buttonCycler.setVisible();
        mouseListener = new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                for(int i = 0; i < buttonCycler.buttons.size(); i++)
                if(buttonCycler.buttons.get(i).isVisible && buttonCycler.buttons.get(i).isTouched){
                   actionSelect(buttonCycler.buttons.get(i).getAction());
                   buttonCycler.buttons.get(i).setColor(Color.RED);
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
    public class PauseGame extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(menu != null){
                if(!menu.menuPresent){
                    if(paused){
                        System.out.println("Game unpaused");
                        buttonCycler.setInvisible();
                        paused = false;
                    }else{
                        System.out.println("Game paused");
        //                panel.setFocusable(true);
                        buttonCycler.setVisible();
                        paused = true;
                    }
                }
            }
        }
    }
    public void actionSetUp(){
        pauseGame = new PauseGame();
        panel.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_P, 0), "pauseGame");
        panel.getActionMap().put("pauseGame", pauseGame);
    }
    public void setMenu(Menu menu){
        this.menu = menu;
    }
    public void clearMap(){
        for(int i = 0; i < cycler.object.size(); i++){
            if(cycler.object.get(i).id == ID.Zombie){
                cycler.object.remove(i);
                i = i-1;
            }
        }
        for(int i = 0; i < cycler.object.size(); i++){
            if(cycler.object.get(i).id == ID.PowerUp){
                PowerUp powerUp = (PowerUp) cycler.object.get(i);
                powerUp.sound.stopSound();
                cycler.object.remove(i);
                i = i-1;
            }
        }
        pCreator.hudPowerUp.clearPow();
        
        pCreator.clear();
        pCreator.hudPowerUp.clearPow();
        round.roundReset();
        perks.removePerks();
    }
    
    public void actionSelect(int index){
        if(index == 0) quit();
        if(index == 1) save();
        if(index == 2) displayControls();
    }
    public void quit(){
        System.out.println("Quit");
        round.stopChange();
        clearMap();
        menu.playSong = true;
        buttonCycler.setInvisible();
        menu.menuPresent = true;
        paused = false;

    }
    public void save(){
        try {
            System.out.println("Save");
            round.stopChange();
            if(player.getHealth() != 0){
                Saver saver = new Saver(player, round, weapons, perks);
                saver.saveData();
            }
            clearMap();
            menu.playSong = true;
            buttonCycler.setInvisible();
            menu.menuPresent = true;
            paused = false;

        } catch (IOException ex) {
            System.out.println("IO Exception");
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFound");
        }
    }
    public void displayControls(){
        System.out.println("Controls");
        controlsTimer = 0l;
        showControls = true;
    }
    
    
    public void update(long timePassed){
            showPauseMessage = true;
            pauseBlink += timePassed;
            if(showControls) controlsTimer += timePassed;
            if(controlsTimer >= controlsDuration){
                showControls = false;
                controlsTimer = 0l;
            }
            if(pauseBlink >= 1000){
                
                showPauseMessage = false;
                pauseClosed += timePassed;
            }
            if(pauseClosed >= 400){
                showPauseMessage = true;
                pauseBlink = 0;
                pauseClosed = 0;
            }
            buttonCycler.update(timePassed);
    }
    
    public void draw(Graphics g){
        g.drawImage(pBG, 0, 0, null);
        g.setFont(new Font("Avenir", Font.BOLD, 250));
        g.setColor(Color.WHITE);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.drawString("PAUSED", 240, 380);
        g.setFont(new Font("Avenir", Font.BOLD, 40));
        if(showPauseMessage){
            g2.drawString("Press 'P' to Resume", 510, 420);
        }
        g.setFont(new Font("Avenir", Font.BOLD, 30));
        g.setColor(Color.YELLOW);
        if(showControls){
            
            g2.drawString("'F' to Fire, 'R' to Reload,", 520, 600);
            g2.drawString("Arrows for Movement, 'P' to Pause", 450, 630);
        }
        buttonCycler.draw(g);
    }
    
}
