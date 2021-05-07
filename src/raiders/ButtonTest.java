
package raiders;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;


public class ButtonTest {

    public boolean isVisible;
    
    private boolean isTouched;
    
    private boolean isPressed;
    
    private float x;
    private float y;
    private float width;
    private String name;
    private float height;
    public MouseListener mouseListener;
    private Color color = Color.decode("#5A5A5A");
    
    private float mouseX;
    private float mouseY;
    
    private JPanel panel;
    public ButtonTest(float x, float y, float width, float height, String name, JPanel panel){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.name = name;
        this.panel = panel;
        mouseListener = new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                if(isTouched & isVisible){
                   System.out.println("The Button has been pressed...");
                   color = Color.RED;
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {
//                System.out.println("Mouse Pressed");
        
            }
            public void mouseReleased(MouseEvent e) {
//                System.out.println("Mouse Released");
            }
            public void mouseEntered(MouseEvent e) {
            }
            public void mouseExited(MouseEvent e) {
                color = Color.BLACK;
                System.out.println("Mouse exited");
            }};
        isVisible = true;

    }
    
    public void update(long timePassed){
        try{
            mouseX = (float) panel.getMousePosition().x;
        }catch(Exception e1){
            mouseX = 0;
        }
        try{
            mouseY = (float) panel.getMousePosition().y;
        }catch(Exception e1){
            mouseY = 0;
        }
        
        if(Proximity.inButton(x, y, width, height, mouseX, mouseY)){
            isTouched = true;
        }else{
            isTouched = false;
            color = Color.decode("#5A5A5A");
        }
    
        if(!color.equals(Color.RED)){
        if(isTouched){
            color = Color.decode("#8F8F8F");
        }else{
            color = Color.decode("#5A5A5A");
        }
        }
        //Do not do any updates related to the button actually being pressed
    }
    
    public void draw(Graphics g){
        g.setColor(Color.black);
        g.fillRect((int) x - 2, (int) y - 2, (int) width + 4, (int) height + 4);
        g.setColor(color);
        g.fillRect((int) x, (int) y, (int) width, (int) height);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Avenir", Font.BOLD, (int) height));
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.drawString(name, x, y + height - (height / 10));
    }
    
    
    
    
}
