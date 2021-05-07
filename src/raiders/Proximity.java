/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raiders;


public class Proximity {
    public static boolean checkProx(float pX, float pY, float oX, float oY){
        if((pX + 19 > oX - 50 && pX + 19 <= oX + 50) && (pY + 19 > oY - 50 && pY + 19 <= oY + 50)){
            return true;
        }else{
            return false;
        }
    }
    
    public static boolean inButton(float x, float y, float width, float height, float mX, float mY){
        if(mX > x && mX < (x + width) && mY > y && mY < (y + height)){
            return true;
        }
        return false;
    }
}
