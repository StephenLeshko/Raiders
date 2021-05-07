
package raiders;

import java.awt.Graphics;
import java.util.LinkedList;


public class ButtonCycler {
    public LinkedList<Button> buttons = new LinkedList<Button>();
    
    public void update(long timePassed){
//        if(object.size() == 0) return;
        for(int i = 0; i < buttons.size(); i++){
            buttons.get(i).update(timePassed);
        }
    }
    public void setVisible(){
        for(int i = 0; i < buttons.size(); i++){
            buttons.get(i).isVisible = true;
        }
    }
    
    public void setInvisible(){
        for(int i = 0; i < buttons.size(); i++){
            buttons.get(i).isVisible = false;
            buttons.get(i).isTouched = false;
        } 
    }
    public void draw(Graphics g){
//        if(object.size() == 0) return;
        for(int i = 0; i < buttons.size(); i++){
            buttons.get(i).draw(g);
        } 
    }
    
    public void addObject(Button o){
        buttons.add(o);
    }
    
    public void removeObject(Button o){
        buttons.remove(o);
    }
}
