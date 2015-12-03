package edu.uchicago.cs.java.finalproject.mvc.model;

import edu.uchicago.cs.java.finalproject.mvc.controller.Game;
import edu.uchicago.cs.java.finalproject.sounds.Sound;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Cc {
    // **************** Global Constants *****************
    public static int PLAYER_PROJECTILE_RANGE = 800;
    public static int PLAYER_PROJECTILE_SPEED = 35;
    public static int PLAYER_PROJECTILE_COOLDOWN = 3;

    public static int ENEMY_PROJECTILE_RANGE = 600;
    public static int ENEMY_PROJECTILE_SPEED = 22;

    public static int PLAYER_LASER_RANGE = 1200;
    public static int PLAYER_LASER_COOLDOWN = 25;

    public static int PLAYER_SPREAD_RANGE = 500;
    public static int PLAYER_SPREAD_SPEED = 30;
    public static int PLAYER_SPREAD_COOLDOWN = 5;

    public static int PLAYER_LIVES = 3;

	public static int ENEMY_HITPOINTS = 2;
	public static int ENEMYBIG_HITPOINTS = 3;



    // ***************************************************

	private  int nNumFalcon;
	private  int nLevel;
	private  long lScore;

	private  Player player;

    public static enum GameState {
        Start, Level, InGame, Pause, GameOver, Died
    }

    public GameState gameState;
	// These ArrayLists with capacities set
	private List<Movable> movDebris = new ArrayList<Movable>(300);
	private List<Movable> movFriends = new ArrayList<Movable>(100);
	private List<Movable> movFoes = new ArrayList<Movable>(200);
	private List<Movable> movFloaters = new ArrayList<Movable>(50);

    private List<Movable> movFriendlyFire = new ArrayList<Movable>(200);
    private List<Movable> movEnemyFire = new ArrayList<Movable>(200);
    private List<Movable> movPlayers = new ArrayList<Movable>(2);

	private Map map;

	private GameOpsList opsList = new GameOpsList();

	//added by Dmitriy
	private static Cc instance = null;

    public static Dimension gameDim;

    private int enemyCount;

	// Constructor made private - static Utility class only
	private Cc() {}


	public static Cc getInstance(){
		if (instance == null){
			instance = new Cc();
		}
		return instance;
	}


	public void initGame(){
		setScore(0);
        generateMap();
	}

    public void startLevel(int i) {
        enemyCount =0;
        gameState = GameState.InGame;
        setLevel(i);
        clearAll();
        spawnPlayer();
        spawnFoes(i * 3 + 5);
    }

    public boolean isLevelClear() {
        if (gameState == GameState.InGame && enemyCount ==0)
            return true;
        return false;
    }

	public void spawnPlayer() {
		player = new Player(100, (int) Game.DIM.getHeight() - 160);
		opsList.enqueue(player, CollisionOp.Operation.ADD);
	}

    public void spawnFoes(int count) {
        Random r = new Random();
        for (int i = 0; i < count; i++) {
            opsList.enqueue(new Enemy(r.nextInt((int)map.xBounds-300) + 200, r.nextInt((int)Game.DIM.getHeight()-200) + 100, 20), CollisionOp.Operation.ADD);
        }
    }

	public void generateMap() {
		System.out.println("Generate Map");
		map = new Map();
	}

	public Map getMap(){
		return map;
	}

	public Player getPlayer(){
		return player;
	}

	public GameOpsList getOpsList() {
		return opsList;
	}

	public void setOpsList(GameOpsList opsList) {
		this.opsList = opsList;
	}

	public  void clearAll(){
        movPlayers.clear();
		movFriends.clear();
		movFoes.clear();
		movFloaters.clear();
        movFriendlyFire.clear();
        movEnemyFire.clear();
	}
	
	public  boolean isGameOver() {		//if the number of falcons is zero, then game over

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

	public  List<Movable> getMovFriends() {
		return movFriends;
	}

	public  List<Movable> getMovFoes() {
		return movFoes;
	}

	public  List<Movable> getMovFloaters() {
		return movFloaters;
	}

    public  List<Movable> getMovPlayers() {
        return movPlayers;
    }

    public  List<Movable> getMovFriendlyFire() {
        return movFriendlyFire;
    }

    public  List<Movable> getMovEnemyFire() {
        return movEnemyFire;
    }

    public void killEnemy(){
        enemyCount--;
    }
    public void addEnemyCount(){
        enemyCount++;
    }
    public int getEnemyCount(){
        return enemyCount;
    }
}
