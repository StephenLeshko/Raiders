
package raiders;

import java.io.File;
import javax.sound.sampled.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import javax.swing.JOptionPane;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class Audio implements Runnable{
    String fileStart = "/Users/21sleshko/NetBeansProjects/Raiders/";
    //Just for reference
     String[] sounds = {"intialRoundChange.wav", "fullRoundChange.wav", "pistolReload.wav", 
        "pistolShot.wav", "akShot.wav", "akReload.wav", "pStep.wav", "zStep.wav", "zDeath.wav", 
        "zHit.wav", "pRegen.wav"};
     
     //Stuff I need audio for::
     //Power up collected (causes the audio to play for collection)
     //Power up "Name" callout (ie kaboom, "INSTAKILL"; depends on the ID)
     //Hum while the powerup is in
     
     //Thing I should consider... As the powerup decreases in timer, it resets the animation frames
     
     
    String soundName;
    long waitTime;
    long originTime;
    private String soundChoice;
    private Thread thread;
    
    InputStream music;
    AudioStream audio;

    
    public Audio(String soundChoice){
        this.soundChoice = soundChoice;
        thread = new Thread(this);
        thread.start();
    }
    public void playSound(String soundChoice){
        soundName = fileStart.concat(soundChoice);
        try{
//        File file = new File(soundName);
//        AudioInputStream sound = AudioSystem.getAudioInputStream(file);
//        Clip clip = AudioSystem.getClip();
//       
//        clip.open(sound);
//        clip.setFramePosition(0);
//        clip.start();
//        System.out.println("Audio");
//        originTime = System.currentTimeMillis();
//        System.out.println(clip.getMicrosecondLength());
//        while(clip.getMicrosecondLength()/1000 > waitTime){
//            waitTime = System.currentTimeMillis() - originTime;
//        }
//        clip.drain();
//        waitTime = 0;
//        clip.close();
//        clip = null;
//////        System.out.println("Closed audio");
//        sound.close();
//        sound = null;
////        System.gc();
//        thread = null;
////        System.gc();
////        System.out.println("Audio stopped");

            //New Audio
            
//            music = new FileInputStream(new File(soundName));
//            audio = new AudioStream(music);
//            AudioPlayer.player.start(audio);
//            
        }catch(Exception e){
            System.out.println(soundName +" Audio did not run");
        }
    }
    public void stopSound(){
        AudioPlayer.player.stop(audio);
    }
    

    public void run() {
        playSound(soundChoice);
    }
    
    
    
}
