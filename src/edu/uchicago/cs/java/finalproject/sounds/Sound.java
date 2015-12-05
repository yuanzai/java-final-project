package edu.uchicago.cs.java.finalproject.sounds;



import javafx.scene.media.Media;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javazoom.jl.player.Player;

public class Sound {
    public static boolean isMuted;
	//for individual wav sounds (not looped)
	//http://stackoverflow.com/questions/26305/how-can-i-play-sound-in-java
	public static synchronized void playSound(final String strPath) {
        if (isMuted)
            return;
	    new Thread(new Runnable() { 
	      public void run() {
	        try {
	          Clip clp = AudioSystem.getClip();

	          AudioInputStream aisStream = 
	        		  AudioSystem.getAudioInputStream(Sound.class.getResourceAsStream(strPath));
     
	          
	          clp.open(aisStream);
	          clp.start(); 
	        } catch (Exception e) {
	          System.err.println(e.getMessage());
	        }
	      }
	    }).start();
	  }
	
	
	//for looping wav clips
	//http://stackoverflow.com/questions/4875080/music-loop-in-java
	public static Clip clipForLoopFactory(String strPath){
		
		Clip clp = null;
		
		// this line caused the original exceptions
		
		try {
			AudioInputStream aisStream = 
					  AudioSystem.getAudioInputStream(Sound.class.getResourceAsStream(strPath));
			clp = AudioSystem.getClip();
		    clp.open( aisStream );
				
		} catch (UnsupportedAudioFileException exp) {
			
			exp.printStackTrace();
		} catch (IOException exp) {
			
			exp.printStackTrace();
		} catch (LineUnavailableException exp) {
			
			exp.printStackTrace();
			
		//the next three lines were added to catch all exceptions generated
		}catch(Exception exp){
			System.out.println("error");
		}
		
		return clp;
		
	}





	private String filename;
	private Player player;
    private int lastStop;
    private Thread t;

	public void close() { if (player != null) player.close(); }

	// play the MP3 file to the sound card
    public void play() {
        play(filename);
    }

	public void play(String filename) {
        this.filename = filename;

		try {
			FileInputStream fis     = new FileInputStream(Sound.class.getResource(filename).getFile());
			BufferedInputStream bis = new BufferedInputStream(fis);
			player = new Player(bis);
		}
		catch (Exception e) {
			System.out.println("Problem playing file " + filename);
			System.out.println(e);
		}

		// run in new thread to play in background
		t = new Thread() {
			public void run() {
                try {
                    player.play();
                } catch (Exception e) { System.out.println(e); }
			}
		};
        t.start();

	}
}
