
package raiders.HudElements;

import java.util.*;
import java.awt.*;

public class HudCycler {
    public LinkedList<HudObject> hudObject = new LinkedList<HudObject>();
    
    public void update(long timePassed){
//        if(object.size() == 0) return;
        for(int i = 0; i < hudObject.size(); i++){
            hudObject.get(i).update(timePassed);
        }
    }
    
    public void draw(Graphics g){
//        if(object.size() == 0) return;
        for(int i = 0; i < hudObject.size(); i++){
            hudObject.get(i).draw(g);
        } 
    }
    
    public void addObject(HudObject o){
        hudObject.add(o);
    }
    
    public void removeObject(HudObject o){
        hudObject.remove(o);
    }
}
