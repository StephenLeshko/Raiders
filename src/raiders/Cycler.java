
package raiders;

import java.util.*;
import java.awt.*;

//This guy runs the show...
public class Cycler {
    public LinkedList<GameObject> object = new LinkedList<GameObject>();
    
    public void update(long timePassed){
//        if(object.size() == 0) return;
        for(int i = 0; i < object.size(); i++){
            object.get(i).update(timePassed);
        }
    }
    
    public void draw(Graphics g){
//        if(object.size() == 0) return;
        for(int i = 0; i < object.size(); i++){
            object.get(i).draw(g);
        } 
    }
    
    public void addObject(GameObject o){
        object.add(o);
    }
    
    public void removeObject(GameObject o){
        object.remove(o);
    }
    
    
}
