package edu.uchicago.cs.java.finalproject.mvc.model;

import edu.uchicago.cs.java.finalproject.mvc.controller.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Cc {
    // **************** Global Constants *****************
    public static int PLAYER_PROJECTILE_RANGE = 800;
    public static int PLAYER_PROJECTILE_SPEED = 35;
    public static int PLAYER_PROJECTILE_COOLDOWN = 6;

    public static int ENEMYA_PROJECTILE_RANGE = 600;
    public static int ENEMYA_PROJECTILE_SPEED = 25;

    public static int ENEMYB_PROJECTILE_SEEKER_RANGE = 1800;
    public static int ENEMYB_PROJECTILE_SEEKER_SPEED = 15;


    public static int PLAYER_LASER_RANGE = 1200;
    public static int PLAYER_LASER_COOLDOWN = 28;

    public static int PLAYER_SPREAD_RANGE = 500;
    public static int PLAYER_SPREAD_SPEED = 30;
    public static int PLAYER_SPREAD_COOLDOWN = 12;

    public static int PLAYER_LIVES = 10;

	public static int ENEMYA_HITPOINTS = 2;
	public static int ENEMYB_HITPOINTS = 4;
    public static int ENEMYC_HITPOINTS = 6;

    public static int ENEMYA_MOVE_SPEED = 7;
    public static int ENEMYB_MOVE_SPEED = 6;
    public static int ENEMYC_MOVE_SPEED = 3;

    public static int ENEMYA_MOVE_RADIUS = 23;
    public static int ENEMYB_MOVE_RADIUS = 30;
    public static int ENEMYC_MOVE_RADIUS = 37;

    public static int TIMER_TOTAL = 30000;
    public static int TIMER_DAMAGE = 2000;
    public int timer;

    // ***************************************************

	private  int nLevel;
	private  long lScore;

	private  Player player;

    public static enum GameState {
        Start, Level, InGame, Pause, GameOver, Died
    }

    public GameState gameState;
	// These ArrayLists with capacities set

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

    private int enemyCount;

	// Constructor made private - static Utility class only
	private Cc() {}


	public static Cc getInstance(){
		if (instance == null){
			instance = new Cc();
		}
		return instance;
	}

    public void startLevel(int i) {
        clearAll();
        generateMap();
        enemyCount =0;
        setLevel(i);
        spawnPlayer();
        spawnEnemyA(i * 3 + 2);
        spawnEnemyB(i/2 + 1);
        spawnEnemyC(i/3 + 1);
        timer = 0;
        gameState = GameState.InGame;
    }

    public boolean isLevelClear() {
        if (gameState == GameState.InGame && enemyCount ==0)
            return true;
        return false;
    }

	public void spawnPlayer() {
		player = new Player(100, (int) Game.DIM.getHeight() - 160, PLAYER_LIVES);
		opsList.enqueue(player, CollisionOp.Operation.ADD);
	}

    public void spawnEnemyA(int count) {
        Random r = new Random();
        for (int i = 0; i < count; i++) {
            opsList.enqueue(new EnemyA(r.nextInt((int)map.xBounds-300) + 200, Game.DIM.height - r.nextInt(5) * Map.GROUND_HEIGHT - 100), CollisionOp.Operation.ADD);
        }
    }

	public void spawnEnemyB(int count) {
		Random r = new Random();
		for (int i = 0; i < count; i++) {
			opsList.enqueue(new EnemyB(r.nextInt((int)map.xBounds-300) + 200, Game.DIM.height - r.nextInt(5) * Map.GROUND_HEIGHT - 100), CollisionOp.Operation.ADD);
		}
	}

    public void spawnEnemyC(int count) {
        Random r = new Random();
        for (int i = 0; i < count; i++) {
            opsList.enqueue(new EnemyC(r.nextInt((int)map.xBounds-300) + 200, Game.DIM.height-100), CollisionOp.Operation.ADD);
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
