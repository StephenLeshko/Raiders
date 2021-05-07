/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raiders.BuyElements;

import java.awt.Graphics;
import java.util.LinkedList;


public class BuyCycler {
    public LinkedList<BuyStation> buyStation = new LinkedList<BuyStation>();
    
    public void update(long timePassed){
        for(int i = 0; i < buyStation.size(); i++){
            buyStation.get(i).update(timePassed);
        }
    }
    
    public void buy(){
        for(int i = 0; i < buyStation.size(); i++){
            buyStation.get(i).buy();
        }
    }
    
    public void draw(Graphics g){
//        if(object.size() == 0) return;
        for(int i = 0; i < buyStation.size(); i++){
            buyStation.get(i).draw(g);
        } 
    }
    
    public void addObject(BuyStation o){
        buyStation.add(o);
    }
    
    public void removeObject(BuyStation o){
        buyStation.remove(o);
    }
}
