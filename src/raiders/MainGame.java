
package raiders;

import raiders.BuyElements.*;
import raiders.HudElements.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Arrays;
import javax.swing.*;

public class MainGame extends Canvas implements Runnable{
    private boolean running;
    public static int WIDTH = 1366, HEIGHT = 685;
    public String title = "Raiders";
    private GameWindow gameWindow;
    private Thread thread;
    private KeyInput input;
    private Cycler cycler;
    private HudCycler hudCycler;
    private HudRound hudRound;
    private HudPerks hudPerks;
    private HudPowerUp hudPowerUp;
    private Weapons weapons;
    private Perks perks;
    private BuyCycler buyCycler;
    private PCreator pCreator;
    private Pause pause;
    private ButtonCycler pauseCycler;
    private Menu menu;
    private ButtonCycler menuCycler;
    
    //Creation of a player
    
    private Player player;
    private Round round;
    
    //Background Image
    private final Image bg = new ImageIcon("/Users/21sleshko/NetBeansProjects/Raiders/TempBackground.png").getImage();
    
    
    
    public static void main(String[] args) {
        
        new MainGame();
    }
    
    public MainGame(){
        //Contructor
        gameWindow = new GameWindow(WIDTH, HEIGHT, title, this);
        start();
        init();
    } 
    private void init(){
        
        cycler = new Cycler();
        hudCycler = new HudCycler();
        pauseCycler = new ButtonCycler();
        menuCycler = new ButtonCycler();
        input = new KeyInput();
        weapons = new Weapons(cycler);
        this.setFocusTraversalKeysEnabled(false);

        //new setup
        
        input.actionSetUp(gameWindow.getPanel());
        weapons.actionSetUp(gameWindow.getPanel());
        player = new Player(400, 300, ID.Player, input, cycler, weapons);
        hudRound = new HudRound(1315, 50);
        hudPerks = new HudPerks(0, 0);
        hudPowerUp = new HudPowerUp(0, 0);
        pCreator = new PCreator(cycler, player, weapons, hudPowerUp);
        perks = new Perks(player, weapons, hudPerks, pCreator);
        pCreator.setPerks(perks);
        
        round = new Round(1, cycler, hudRound, player, pCreator);
        pCreator.setRound(round);

        hudRound.setRound(round);
        
        weapons.findPlayer();
        
        buyCycler = new BuyCycler();
        input.setCycler(buyCycler);
        cycler.addObject(player);
        
        //Buy Stuff
        buyCycler.addObject(new JuggStation(50, 100, player, 2500, "Juggernog", perks));
        buyCycler.addObject(new DoubleStation(150, 100, player, 2000, "Double Tap", perks));
        buyCycler.addObject(new StaminStation(1166, 100, player, 2000, "Stamin-Up", perks));
        buyCycler.addObject(new SpeedStation(1266, 100, player, 3000, "Speed Cola", perks));
        buyCycler.addObject(new QuickStation(50, 435, player, 1500, "Quick Revive", perks));
        buyCycler.addObject(new TikiStation(150, 435, player, 4000, "Tiki Tiki", perks));
        buyCycler.addObject(new SuperStation(1166, 435, player, 15000, "SuperCharge", perks));
        
        buyCycler.addObject(new GroundWeapon(270, 160, player, 2000, "AK47", weapons, 1));
        buyCycler.addObject(new GroundWeapon(370, 450, player, 3000, "M16", weapons, 2));
        buyCycler.addObject(new GroundWeapon(1010, 450, player, 15000, "Ray k", weapons, 3));

        buyCycler.addObject(new AmmoStation(673, 335, player, 500, "Ammo", weapons));

        //Hud Stuff
        hudCycler.addObject(new HudAmmo(1150, 650, weapons));
        hudCycler.addObject(new HudHealth(25, 650, player));
        hudCycler.addObject(new HudPoints(25, 605, player));
        hudCycler.addObject(hudPerks);
        hudCycler.addObject(hudRound);
        hudCycler.addObject(hudPowerUp);
        
        //Pause
        pause = new Pause(gameWindow.getPanel(), pauseCycler, player, round, weapons, perks, cycler, pCreator);
        weapons.setPause(pause);
        menu = new Menu(gameWindow.getPanel(), player, round, weapons, perks, menuCycler);
        weapons.setMenu(menu);
        pause.setMenu(menu);
        //God Mode (just for testing)
//        player.addPoints(30000);
//        perks.giveJug();
//        perks.giveStamin();
//        perks.giveTiki();
//        perks.giveQuick();
//        perks.giveDouble();
//        perks.giveSpeed();
//        perks.giveSuper();
        this.addMouseListener(pause.mouseListener);
        this.addMouseListener(menu.mouseListener);
//        this.addMouseListener(pause.button.mouseListener);
//        this.addMouseListener(pause.button2.mouseListener);
        menu.menuPresent = true;
    }

    private synchronized void start(){
        if(running) return;
        thread = new Thread(this);
        thread.start();
        running = true;
        System.out.println("The game has started");
    }
    
    //Same as ticked; possibly could switch to not have parameters
    private void update(long timePassed){
        //The pause condition would just stop these events from occuring; and it would need to stop the other stuff from occuring
        
        if(!pause.paused && !menu.menuPresent){
        round.update(timePassed);
        cycler.update(timePassed);
        buyCycler.update(timePassed);
        hudCycler.update(timePassed);
        }else if(pause.paused){
            pause.update(timePassed);
        }else{
            menu.update(timePassed);
        }
    }
    
    private synchronized void stop(){
        if(!running) return;
        try{
            thread.join();
        }catch(Exception e){
            e.printStackTrace();
        }
        running = false;
    }
    
    private void draw(){
        //Renders the game
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        //Color Background
        if(!menu.menuPresent){
            g.drawImage(bg, 0, 0, null);
            //Cycler Stuff
            cycler.draw(g);
            //Buy elements second
            buyCycler.draw(g);
            //Hud elements last
            hudCycler.draw(g);

            //Pause
            if(pause.paused){
                pause.draw(g);
            }
        }else{
            menu.draw(g);
        }
        //Removal
        bs.show();
        g.dispose();
    }

    public void run() {
        this.requestFocus(true);
        System.out.println("Run has begun");
        try {
            //Lets everything catch up 
            Thread.sleep(1000);
        } catch (InterruptedException ex){
            System.out.println("Sleep fail");
        }
        long startTime = System.currentTimeMillis();
        long cumTime = startTime;
        while(running){
            long timePassed = System.currentTimeMillis() - cumTime;
            cumTime += timePassed;
            //Just basically supplying a time value 
            update(timePassed);
            draw();
            try{
                //Possibly eliminate this to boost speed
                Thread.sleep(20);
            }catch(Exception ex){
            }
        }
        stop();
    }
    }
    
