
package raiders;

import raiders.BuyElements.BuyCycler;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;


public class KeyInput {
    
    private BuyCycler buyCycler;
    public boolean keys[] = new boolean[4];
    
    public void setCycler(BuyCycler theBuyCycler){
        this.buyCycler = theBuyCycler;
    }
    
            
    /* 
       keys[0] = right
       keys[1] = left
       keys[2] = up
       keys[3] = down
    */
    
    Action upAction;
    Action downAction;
    Action leftAction;
    Action rightAction;
    Action upReleasedAction;
    Action downReleasedAction;
    Action leftReleasedAction;
    Action rightReleasedAction;
    Action reload;
    Action shoot;
    Action buyAction;

    
    public class Buy extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(buyCycler != null){
                buyCycler.buy();
            }
        }
    }
    
        
    public class UpAction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            keys[2] = true;
//            System.out.println("Up");
        }
    }
    
    

    
    public class DownAction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            keys[3] = true;
//            System.out.println("Down");
        }
    }
    
    public class RightAction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            keys[0] = true;
//            System.out.println("Right");

        }
    }
    
    public class LeftAction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            keys[1] = true;
//            System.out.println("Left");
        }
    }
    
    public class UpReleasedAction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            keys[2] = false;
//            System.out.println("Up Released");
        }
    }
    
    public class DownReleasedAction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            keys[3] = false;
//            System.out.println("Down Released");
        }
    }
    
    public class RightReleasedAction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            keys[0] = false;
//            System.out.println("Right Released");

        }
    }
    
    public class LeftReleasedAction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            keys[1] = false;
//            System.out.println("Left Released");
        }
    }
    
    
    
    
    public void actionSetUp(JPanel panel){
        //Key Pressed
        upAction = new UpAction();
        downAction = new DownAction();
        leftAction = new LeftAction();
        rightAction = new RightAction();
        
        //Key Released
        upReleasedAction = new UpReleasedAction();
        downReleasedAction = new DownReleasedAction();
        leftReleasedAction = new LeftReleasedAction();
        rightReleasedAction = new RightReleasedAction();
        
        //buy
        buyAction = new Buy();
        
        
        //Key Released Puts
        panel.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, true), "upReleasedAction");
        panel.getActionMap().put("upReleasedAction", upReleasedAction);
        panel.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true), "rightReleasedAction");
        panel.getActionMap().put("rightReleasedAction", rightReleasedAction);
        panel.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, true), "downReleasedAction");
        panel.getActionMap().put("downReleasedAction", downReleasedAction);
        panel.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true), "leftReleasedAction");
        panel.getActionMap().put("leftReleasedAction", leftReleasedAction);

        //Key Pressed Puts
        panel.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false), "upAction");
        panel.getActionMap().put("upAction", upAction);
        panel.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false), "rightAction");
        panel.getActionMap().put("rightAction", rightAction);
        panel.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false), "downAction");
        panel.getActionMap().put("downAction", downAction);
        panel.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false), "leftAction");
        panel.getActionMap().put("leftAction", leftAction);
        
        //Buy Stuff
        panel.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_B, 0, false), "buyAction");
        panel.getActionMap().put("buyAction", buyAction);
    }
//    
    
    
//     @Override
//     public void keyPressed(KeyEvent e) {
//        int key = e.getKeyCode();
//        System.out.println("You pressed " + e.getKeyChar());
//        
//        if(key == KeyEvent.VK_D){ keys[0] = true;}
//        else if(key == KeyEvent.VK_A){ keys[1] = true;}
//        else if(key == KeyEvent.VK_W){ keys[2] = true;}
//        else if(key == KeyEvent.VK_S){ keys[3] = true;}
//        else{e.consume();}
//        
//    }
//     
//    @Override
//    public void keyReleased(KeyEvent e) {
//        int key = e.getKeyCode();
//        System.out.println("You released " + e.getKeyChar());
//
//        if(key == KeyEvent.VK_D){ keys[0] = false;}
//        else if(key == KeyEvent.VK_A){ keys[1] = false;}
//        else if(key == KeyEvent.VK_W){ keys[2] = false;}
//        else if(key == KeyEvent.VK_S){ keys[3] = false;}
//        else{e.consume();}
//    }
//
//    public void keyTyped(KeyEvent e) {
//        e.consume();
//    }
       
}
