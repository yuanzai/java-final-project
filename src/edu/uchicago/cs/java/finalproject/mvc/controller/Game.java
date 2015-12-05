package edu.uchicago.cs.java.finalproject.mvc.controller;

import edu.uchicago.cs.java.finalproject.mvc.model.*;
import edu.uchicago.cs.java.finalproject.mvc.model.Character;
import edu.uchicago.cs.java.finalproject.mvc.model.Map;
import edu.uchicago.cs.java.finalproject.mvc.view.GamePanel;
import edu.uchicago.cs.java.finalproject.sounds.Sound;

import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.*;

// ===============================================
// == This Game class is the CONTROLLER
// ===============================================

public class Game implements Runnable {

    // ===============================================
    // FIELDS
    // ===============================================

    public static Dimension DIM = new Dimension(1100, 900); //the dimension of the game.

    private GamePanel gmpPanel;
    private ActionListener actionListener;

    public static Random R = new Random();
    public final static int ANI_DELAY = 45; // milliseconds between screen

    // updates (animation)
    private Thread thrAnim;
    private int nTick = 0;

    private boolean bMuted = true;

    private Clip clpThrust;
    private Clip clpMusicBackground;

    public int xMapOffset;
    public boolean bLeft;
    public boolean bRight;
    public boolean bStart;
    Sound sound;


    // ===============================================
    // ==CONSTRUCTOR
    // ===============================================

    public Game() {

        gmpPanel = new GamePanel(DIM);
        Cc.getInstance().gameState = Cc.GameState.Start;

        // separate listener class for cleanliness
        actionListener = new ActionListener(this);
        gmpPanel.addKeyListener(actionListener);
        gmpPanel.addMouseListener(actionListener);
        gmpPanel.addMouseMotionListener(actionListener);


        //Used Jlayer to play MP3
        sound = new Sound();
        sound.play("background128.mp3");

    }

    // ===============================================
    // ==METHODS
    // ===============================================

    // per base code
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() { // uses the Event dispatch thread from Java 5 (refactored)
            public void run() {
                try {
                    Game game = new Game(); // construct itself
                    game.fireUpAnimThread();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void fireUpAnimThread() { // called initially
        if (thrAnim == null) {
            thrAnim = new Thread(this); // pass the thread a runnable object (this)
            thrAnim.start();
        }
    }

    // implements runnable - must have run method
    public void run() {

        // lower this thread's priority; let the "main" aka 'Event Dispatch'
        // thread do what it needs to do first
        thrAnim.setPriority(Thread.MIN_PRIORITY);

        // and get the current time
        long lStartTime = System.currentTimeMillis();

        // this thread animates the scene
        while (Thread.currentThread() == thrAnim) {


            // determines the game state to decide what to render
            if (bStart){
                if (Cc.getInstance().gameState == Cc.GameState.Start ||
                        Cc.getInstance().gameState == Cc.GameState.GameOver ) {
                    startGame();
                }else if (Cc.getInstance().gameState == Cc.GameState.Level  ) {
                    startLevel();
                } else if (Cc.getInstance().gameState == Cc.GameState.Died) {
                    Cc.getInstance().gameState = Cc.GameState.Level;
                }
                bStart = false;
            }

            if (Cc.getInstance().isLevelClear()) {
                if (Cc.getInstance().getLevel() == 5) {
                    Cc.getInstance().gameState = Cc.GameState.GameOver;
                }else {
                    Cc.getInstance().gameState = Cc.GameState.Level;
                    Cc.getInstance().setLevel(Cc.getInstance().getLevel() + 1);
                }
            }

            if (Cc.getInstance().gameState == Cc.GameState.InGame) {
                if (Cc.getInstance().getPlayer().lives <= 0) {
                    Cc.getInstance().gameState = Cc.GameState.Died;
                }
                tick();
                processMapOffset();
                checkGroundCollisions();
                checkProjectileCollisions();
                executePlayerAction();
                executeEnemyActions();

            }

            gmpPanel.update(gmpPanel.getGraphics()); // update takes the graphics context we must
            // surround the sleep() in a try/catch block
            // this simply controls delay time between
            // the frames of the animation

            //this might be a good place to check for collisions
            opsDequeue();

            try {
                // The total amount of time is guaranteed to be at least ANI_DELAY long.  If processing (update)
                // between frames takes longer than ANI_DELAY, then the difference between lStartTime -
                // System.currentTimeMillis() will be negative, then zero will be the sleep time
                lStartTime += ANI_DELAY;
                Thread.sleep(Math.max(0,
                        lStartTime - System.currentTimeMillis()));
            } catch (InterruptedException e) {
                // just skip this frame -- no big deal
                continue;
            }
        } // end while
    } // end run



    // In creating a side scroller every sprites has 2 center points
    // a on-screen center and a map center
    // the on-screen center relies on the x-offset to move all sprites according on screen but retaining their map position
    // x offset is control by player moving left and right as well as how close the player is to the boundary of the map
    private void processMapOffset(){
        Player p = Cc.getInstance().getPlayer();
        xMapOffset = Math.min(Math.max((int) (p.getMapPosition().x - Game.DIM.getWidth()/2),0),(int) (Cc.getInstance().getMap().xBounds - Game.DIM.getWidth()));
        gmpPanel.xMapOffset = xMapOffset;
    }

    private void checkGroundCollisions() {
        // checking for sprite collisions with ground.

        Player p = Cc.getInstance().getPlayer();
        if (p != null) {

            Map map = Cc.getInstance().getMap();

            Point basePoint = p.getBasePoint();
            Point headPoint = p.getHeadPoint();

            // check if player is on the ground
            p.bOnGround = false;
            for (Ground g : map.grounds) {
                if (Line2D.ptSegDist(g.x1, g.y, g.x2, g.y,p.getMapPosition().getX(),basePoint.getY()) == 0.0) {
                    p.bOnGround = true;
                    break;
                }
            }
            p.preMove();

            // check if enemy is on ground
            for(Movable enemy : Cc.getInstance().getMovFoes()){
                Enemy e = (Enemy) enemy;
                e.bOnGround = false;
                for (Ground g : map.grounds) {
                    if (Line2D.ptSegDist(g.x1, g.y, g.x2 , g.y,e.getMapPosition().getX(),e.getBasePoint().getY()) == 0.0) {
                        e.bOnGround = true;
                        break;
                    }
                }
                e.preMove();
            }

            // check moving player ground collision
            if (!p.bOnGround || p.bUp) {
                for (Ground g : map.grounds) {
                    if (CollisionCheck.checkCharacterGroundCollision(p,g,xMapOffset))
                        break;
                }
            }

            // check moving enemy ground collision
            for(Movable enemy : Cc.getInstance().getMovFoes()){
                Enemy e = (Enemy) enemy;
                if (!e.bOnGround) {
                    for (Ground g : map.grounds) {
                        if (CollisionCheck.checkCharacterGroundCollision(e, g, xMapOffset))
                            break;
                    }
                }
            }

            // check friendly/ hostile projectile ground collision
            for (Ground g : map.grounds) {
                for (Movable projectile : Cc.getInstance().getMovFriendlyFire()) {
                    if (projectile instanceof Projectile)
                        CollisionCheck.checkProjectileGroundCollisions((Projectile) projectile, g, xMapOffset);
                }
                for (Movable projectile : Cc.getInstance().getMovEnemyFire()) {
                    if (projectile instanceof Projectile)
                        CollisionCheck.checkProjectileGroundCollisions((Projectile) projectile, g, xMapOffset);
                }
            }
        }
    }

    private void checkProjectileCollisions(){
        // checking for character(player/enemy sprites) collisions with projectiles
        for (Movable projectile : Cc.getInstance().getMovFriendlyFire()) {
            for (Movable enemy : Cc.getInstance().getMovFoes()) {
                if (projectile instanceof Projectile) {
                    if (CollisionCheck.checkProjectileHitCharacter((Projectile) projectile, (Character) enemy)) {
                        Cc.getInstance().getOpsList().enqueue(projectile, CollisionOp.Operation.REMOVE);
                        ((Enemy) enemy).isHit();
                    }
                }
            }
        }

        for (Movable projectile : Cc.getInstance().getMovEnemyFire()) {
            if (projectile instanceof Projectile) {
                if (CollisionCheck.checkProjectileHitCharacter((Projectile) projectile, (Character) Cc.getInstance().getPlayer())) {
                    Cc.getInstance().getPlayer().isHit();
                    Cc.getInstance().getOpsList().enqueue(projectile, CollisionOp.Operation.REMOVE);
                }
            }
        }

    }

    private void executeEnemyActions() {
        for(Movable enemy : Cc.getInstance().getMovFoes()){
            Enemy e = (Enemy) enemy;
            e.turnToPlayer(Cc.getInstance().getPlayer());
            e.fire();
        }
    }

    private void executePlayerAction(){

        Player p = Cc.getInstance().getPlayer();
        if (!p.bPressed)
            return;

        switch (p.currentWeapon) {
            case 1:
                if (p.weapon1cooldown == 0) {
                    Projectile projectile = new Projectile(Cc.getInstance().getPlayer(), Color.white, Cc.PLAYER_PROJECTILE_RANGE, Cc.PLAYER_PROJECTILE_SPEED, 6);
                    // Create inaccurate projectiles for aiming further
                    projectile.initForInaccurateProjectile(p);
                    Cc.getInstance().getOpsList().enqueue(projectile, CollisionOp.Operation.ADD);
                    Sound.playSound("projectile.wav");
                    p.weapon1cooldown = Cc.PLAYER_PROJECTILE_COOLDOWN;
                }
                break;
            case 2:
                if (p.weapon2cooldown == 0){
                    // determine laser length, aka if laser hits anything
                    Laser laser = new Laser(p,Color.white,Cc.getInstance().getMap(),Cc.PLAYER_LASER_RANGE);
                    Point pt = CollisionCheck.checkLaserCollision(laser);
                    if (pt!= null) {
                        laser.updateEndPoint(pt);
                    }
                    laser.updateXOffset(xMapOffset);
                    Cc.getInstance().getOpsList().enqueue(laser, CollisionOp.Operation.ADD);
                    Sound.playSound("laser.wav");
                    p.weapon2cooldown = Cc.PLAYER_LASER_COOLDOWN;
                }
                break;
            case 3:
                if (p.weapon3cooldown == 0){
                    // fires 3 projectiles
                    Projectile spread1 = new Projectile(Cc.getInstance().getPlayer(), Color.white, Cc.PLAYER_SPREAD_RANGE, Cc.PLAYER_SPREAD_SPEED, 6);
                    spread1.initForSpreadProjectiles(p.getOrientation()-4);
                    Projectile spread2 = new Projectile(Cc.getInstance().getPlayer(), Color.white, Cc.PLAYER_SPREAD_RANGE, Cc.PLAYER_SPREAD_SPEED, 6);
                    spread2.initForSpreadProjectiles(p.getOrientation());
                    Projectile spread3 = new Projectile(Cc.getInstance().getPlayer(), Color.white, Cc.PLAYER_SPREAD_RANGE, Cc.PLAYER_SPREAD_SPEED, 6);
                    spread3.initForSpreadProjectiles(p.getOrientation()+4);
                    Cc.getInstance().getOpsList().enqueue(spread1, CollisionOp.Operation.ADD);
                    Cc.getInstance().getOpsList().enqueue(spread2, CollisionOp.Operation.ADD);
                    Cc.getInstance().getOpsList().enqueue(spread3, CollisionOp.Operation.ADD);
                    Sound.playSound("shotgun.wav");
                    p.weapon3cooldown = Cc.PLAYER_SPREAD_COOLDOWN;
                }
                break;
        }
    }


    // per base code
    private void opsDequeue() {
        //we are dequeuing the opsList and performing operations in serial to avoid mutating the movable arraylists while iterating them above
        while(!Cc.getInstance().getOpsList().isEmpty()){
            CollisionOp cop =  Cc.getInstance().getOpsList().dequeue();
            Movable mov = cop.getMovable();
            CollisionOp.Operation operation = cop.getOperation();

            // I refactored the code here
            switch (mov.getTeam()){
                case PLAYER:
                    collisionOperation(operation, mov, Cc.getInstance().getMovPlayers());
                    break;
                case FOE:
                    collisionOperation(operation, mov, Cc.getInstance().getMovFoes());
                    break;
                case FRIEND:
                    collisionOperation(operation, mov, Cc.getInstance().getMovFriends());
                    break;
                case FLOATER:
                    collisionOperation(operation, mov, Cc.getInstance().getMovFloaters());
                    break;
                case FRIENDLYFIRE:
                    collisionOperation(operation, mov, Cc.getInstance().getMovFriendlyFire());
                    break;
                case ENEMYFIRE:
                    collisionOperation(operation, mov, Cc.getInstance().getMovEnemyFire());
                    break;
            }
        }
        //a request to the JVM is made every frame to garbage collect, however, the JVM will choose when and how to do this
        System.gc();
    }

    // per base code
    private void collisionOperation(CollisionOp.Operation operation, Movable mov, java.util.List list) {
        if (operation == CollisionOp.Operation.ADD){
            list.add(mov);
        } else {
            list.remove(mov);
        }
    }

    // per base code
    //some methods for timing events in the game,
    //such as the appearance of UFOs, floaters (power-ups), etc.
    public void tick() {
        if (nTick == Integer.MAX_VALUE)
            nTick = 0;
        else
            nTick++;
        Cc.getInstance().timer += ANI_DELAY;
        if (Cc.getInstance().timer > Cc.getInstance().TIMER_TOTAL){
            if ((nTick - Cc.getInstance().TIMER_TOTAL/ ANI_DELAY) % (Cc.getInstance().TIMER_DAMAGE/ANI_DELAY) == 0)
                Cc.getInstance().getPlayer().isHit();
        }
    }

    public int getTick() {
        return nTick;
    }

    public void startGame() {
        Cc.getInstance().clearAll();
        Cc.getInstance().setLevel(1);
        Cc.getInstance().gameState = Cc.GameState.Level;
    }

    public void startLevel(){
        Cc.getInstance().startLevel(Cc.getInstance().getLevel());
        xMapOffset=0;
        bLeft = false;
        bRight = false;
    }

    public GamePanel getGmpPanel() {
        return gmpPanel;
    }
}


