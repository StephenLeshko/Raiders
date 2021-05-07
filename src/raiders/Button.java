/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raiders;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.function.Consumer;
import javax.swing.*;


public class Button {
    private float x;
    private float y;
    private float width;
    private float height;
    private String name;
    private float mouseX;
    private float mouseY;
    
    public boolean isVisible;
    
    public boolean isTouched;
    private Color color = Color.decode("#5A5A5A");
    private int action;
    private JPanel panel;
    public Button(float x, float y, float width, float height, String name, JPanel panel, int action){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.name = name;
        this.panel = panel;
        this.action = action;
    }
    public int getAction(){
        return this.action;
    }
    public void setColor(Color color){
        this.color = color;
    }
    
    
    public void update(long timePassed){
        try{
            mouseX = (float) panel.getMousePosition().x;
        }catch(Exception e1){
            mouseX = 0;
        }
        try{
            mouseY = (float) panel.getMousePosition().y;
        }catch(Exception e2){
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
