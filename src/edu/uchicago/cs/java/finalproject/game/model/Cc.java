package edu.uchicago.cs.java.finalproject.game.model;

import edu.uchicago.cs.java.finalproject.sounds.Sound;

import java.util.concurrent.CopyOnWriteArrayList;

// I only want one Command Center and therefore this is a perfect candidate for static
// Able to get access to methods and my movMovables ArrayList from the static context.
public class Cc {

	private static int nNumFalcon;
	private static int nLevel;
	private static long lScore;
	private static Falcon falShip;
	private static boolean bPlaying;
	private static boolean bPaused;
	
	// These ArrayLists are thread-safe
	public static CopyOnWriteArrayList<Movable> movDebris = new CopyOnWriteArrayList<Movable>();
	public static CopyOnWriteArrayList<Movable> movFriends = new CopyOnWriteArrayList<Movable>();
	public static CopyOnWriteArrayList<Movable> movFoes = new CopyOnWriteArrayList<Movable>();
	public static CopyOnWriteArrayList<Movable> movFloaters = new CopyOnWriteArrayList<Movable>();
	


	//added by Dmitriy
	private static Cc instance = null;

	// Constructor made private - static Utility class only
	private Cc() {}


	//added by Dmitriy
	public static Cc getInstance(){
		if (instance == null){
			instance = new Cc();
		}

		return instance;
	}



	public  void initGame(){
		setLevel(1);
		setScore(0);
		setNumFalcons(3);
		spawnFalcon(true);
	}
	
	// The parameter is true if this is for the beginning of the game, otherwise false
	// When you spawn a new falcon, you need to decrement its number
	public  void spawnFalcon(boolean bFirst) {

		if (getNumFalcons() != 0) {
			falShip = new Falcon();
			movFriends.add(falShip);
			if (!bFirst)
			    setNumFalcons(getNumFalcons() - 1);
		}
		
		Sound.playSound("shipspawn.wav");

	}
	
	public  void clearAll(){
		movDebris.clear();
		movFriends.clear();
		movFoes.clear();
		movFloaters.clear();
	}

	public  boolean isPlaying() {
		return bPlaying;
	}

	public  void setPlaying(boolean bPlaying) {
		Cc.bPlaying = bPlaying;
	}

	public  boolean isPaused() {
		return bPaused;
	}

	public  void setPaused(boolean bPaused) {
		Cc.bPaused = bPaused;
	}
	
	public  boolean isGameOver() {		//if the number of falcons is zero, then game over
		if (getNumFalcons() == 0) {
			return true;
		}
		return false;
	}

	public  int getLevel() {
		return nLevel;
	}

	public   long getScore() {
		return lScore;
	}

	public  void setScore(long lParam) {
		lScore = lParam;
	}

	public  void setLevel(int n) {
		nLevel = n;
	}

	public  int getNumFalcons() {
		return nNumFalcon;
	}

	public  void setNumFalcons(int nParam) {
		nNumFalcon = nParam;
	}
	
	public  Falcon getFalcon(){
		return falShip;
	}
	
	public  void setFalcon(Falcon falParam){
		falShip = falParam;
	}

	public  CopyOnWriteArrayList<Movable> getMovDebris() {
		return movDebris;
	}



	public  CopyOnWriteArrayList<Movable> getMovFriends() {
		return movFriends;
	}



	public  CopyOnWriteArrayList<Movable> getMovFoes() {
		return movFoes;
	}


	public  CopyOnWriteArrayList<Movable> getMovFloaters() {
		return movFloaters;
	}




}
