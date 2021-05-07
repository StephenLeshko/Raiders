
package raiders;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Animations {
    
    public ArrayList<OneScene> scenes = new ArrayList();
    private int sceneIndex;
    private long movieTime;
    //Comparison between two times tells when to rerun the animation
    //totalTime sets maximum time of an animation
    public long totalTime;
    
    //Constructor
    public Animations(){
        totalTime = 0;
        start();
    }
    public synchronized void changeASpeed(long time){
        totalTime = 0;
        for(OneScene o : scenes){
           totalTime += time;
           o.changeEndTime(totalTime);
        }
    }
    
    
    //Synchronized makes it so that only this thread runs
    public synchronized void addScene(Image i, long t){
        totalTime += t;
        scenes.add(new OneScene(i, totalTime));
    }
    
    public synchronized void start(){
        movieTime = 0;
        sceneIndex = 0;
    }
    //Going to call this method a lot...
    //Used to change scenes
    public synchronized void update(long timePassed){
        //Checks to see if its actually an animation
        if(scenes.size() > 1){
            movieTime += timePassed;
            if(movieTime >= totalTime){
                //Basically calls start again (Restarts the animation
                movieTime = 0;
                sceneIndex = 0;
            }
            while(movieTime > getScene(sceneIndex).endTime){
                sceneIndex++;
            }
        }
    }
    public synchronized Image getImage(){
        if(scenes.size() == 0){
            return null;
        }else{
            return getScene(sceneIndex).pic;
        }
    }
    //x represents an index value
    private OneScene getScene(int x){
        return (OneScene) scenes.get(x);
    }
    
    //Private Inner Class
    //Converts the picture to an object; makes it easier to use
    private class OneScene{
        Image pic;
        public long endTime;
        
        public OneScene(Image pic, long endTime){
            this.pic = pic;
            this.endTime = endTime;
        }
        public synchronized void changeEndTime(long time){
            endTime = time;
        }
    }
}

