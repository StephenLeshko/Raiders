
package raiders;

import java.awt.*;
import javax.swing.*;

public class GameWindow {
    
// This is where I will eventually put an "App" Icon for the game
//    ImageIcon logo = new ImageIcon("/Users/21sleshko/NetBeansProjects/Testing1/WLR_Logo.jpg");

   private JFrame frame;
   private JPanel panel;

   private final ImageIcon logo = new ImageIcon("/Users/21sleshko/NetBeansProjects/Raiders/FreeGun1.png");
    
    public GameWindow(int width, int height, String title, MainGame mG){
        frame = new JFrame(title);
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Will eventually add this
        frame.setIconImage(logo.getImage());

        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(mG);
        frame.setVisible(true);
        panel = (JPanel) 
        frame.getContentPane();
        panel.setFont(new Font("Avenir", Font.BOLD, 48));
           
    }
    
   public JFrame getFrame(){
      return frame;
   }
   
   public JPanel getPanel(){
       return panel;
   }
   
 
    
  
    
    
    
}
