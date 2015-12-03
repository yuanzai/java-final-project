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
        actionListener = new ActionListener(this);
        gmpPanel.addKeyListener(actionListener);
        gmpPanel.addMouseListener(actionListener);
        gmpPanel.addMouseMotionListener(actionListener);

        clpThrust = Sound.clipForLoopFactory("whitenoise.wav");

        //Media hit = new Media("sounds/background128.mp3");
        sound = new Sound();
        sound.play("background128.mp3");

        //clpMusicBackground = Sound.clipForLoopFactory("background128.mp3");
        //clpMusicBackground.loop(-1);

    }

    // ===============================================
    // ==METHODS
    // ===============================================

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

            //tick();
            if (bStart){
                if (Cc.getInstance().gameState == Cc.GameState.Start ||
                        Cc.getInstance().gameState == Cc.GameState.GameOver ||
                        Cc.getInstance().gameState == Cc.GameState.Died ) {
                    startGame();
                }else if (Cc.getInstance().gameState == Cc.GameState.Level) {
                    startLevel();
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
                if (Cc.getInstance().getPlayer().lives == 0) {
                    Cc.getInstance().gameState = Cc.GameState.Died;
                }
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

    private void processMapOffset(){
        Player p = Cc.getInstance().getPlayer();
        if (bLeft) {
            if (p.getMapPosition().getX() < Cc.getInstance().getMap().xBounds - Game.DIM.getWidth()/2
                    && p.getMapPosition().getX() > Game.DIM.getWidth()/2){
                //xMapOffset -= (int) Player.STEP;
                gmpPanel.xSpriteOffset = (int) -Player.STEP;
            }


        } else if (bRight) {
            if (p.getMapPosition().getX() < Cc.getInstance().getMap().xBounds - Game.DIM.getWidth()/2
                    && p.getMapPosition().getX() > Game.DIM.getWidth()/2){
                xMapOffset += (int) Player.STEP;
                gmpPanel.xSpriteOffset = (int) Player.STEP;
            }
        }
        xMapOffset = Math.min(Math.max((int) (p.getMapPosition().x - Game.DIM.getWidth()/2),0),(int) (Cc.getInstance().getMap().xBounds - Game.DIM.getWidth()));
        gmpPanel.xMapOffset = xMapOffset;
    }

    private void checkGroundCollisions() {

        Player p = Cc.getInstance().getPlayer();
        if (p != null) {

            Map map = Cc.getInstance().getMap();

            Point basePoint = p.getBasePoint();
            Point headPoint = p.getHeadPoint();

            // check if player is on the ground
            p.bOnGround = false;
            for (Ground g : map.grounds) {
                if (Line2D.ptSegDist(g.x1 - xMapOffset, g.y, g.x2 - xMapOffset, g.y,p.getCenter().getX(),basePoint.getY()) == 0.0) {
                    p.bOnGround = true;
                    break;
                }
            }
            p.preMove();

            for(Movable enemy : Cc.getInstance().getMovFoes()){
                Enemy e = (Enemy) enemy;
                e.bOnGround = false;
                for (Ground g : map.grounds) {
                    if (Line2D.ptSegDist(g.x1 - xMapOffset, g.y, g.x2 - xMapOffset, g.y,e.getCenter().getX(),e.getBasePoint().getY()) == 0.0) {
                        e.bOnGround = true;
                        break;
                    }
                }
                e.preMove();
            }


            if (!p.bOnGround || p.bUp) {
                for (Ground g : map.grounds) {
                    if (checkCharacterGroundCollision(p,g))
                        break;
                }
            }

            for(Movable enemy : Cc.getInstance().getMovFoes()){
                Enemy e = (Enemy) enemy;
                if (!e.bOnGround) {
                    for (Ground g : map.grounds) {
                        if (checkCharacterGroundCollision(e, g))
                            break;
                    }
                }
            }


            for (Ground g : map.grounds) {
                for (Movable projectile : Cc.getInstance().getMovFriendlyFire()) {
                    if (projectile instanceof Projectile)
                        checkProjectileGroundCollisions((Projectile) projectile, g);
                }
            }

            for (Ground g : map.grounds) {
                for (Movable projectile : Cc.getInstance().getMovEnemyFire()) {
                    if (projectile instanceof Projectile)
                        checkProjectileGroundCollisions((Projectile) projectile, g);
                }
            }
        }
    }

    private void checkProjectileCollisions(){
        for (Movable projectile : Cc.getInstance().getMovFriendlyFire()) {
            for (Movable enemy : Cc.getInstance().getMovFoes()) {
                if (projectile instanceof Projectile) {
                    if (checkProjectileHitCharacter((Projectile) projectile, (Character) enemy)) {
                        Cc.getInstance().getOpsList().enqueue(projectile, CollisionOp.Operation.REMOVE);
                        ((Enemy) enemy).isHit();
                    }
                }
            }
        }

        for (Movable projectile : Cc.getInstance().getMovEnemyFire()) {
            if (projectile instanceof Projectile) {
                if (checkProjectileHitCharacter((Projectile) projectile, (Character) Cc.getInstance().getPlayer())) {
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
            if (e.fireCountdown == 0)
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
                    Cc.getInstance().getOpsList().enqueue(new Projectile(Cc.getInstance().getPlayer(), Color.white, Cc.PLAYER_PROJECTILE_RANGE, Cc.PLAYER_PROJECTILE_SPEED, false), CollisionOp.Operation.ADD);
                    Sound.playSound("projectile.wav");
                    p.weapon1cooldown = 5;
                }
                break;
            case 2:
                if (p.weapon2cooldown == 0){
                    Laser laser = new Laser(p,Color.white,Cc.getInstance().getMap(),Cc.PLAYER_LASER_RANGE);
                    Point pt = checkLaserCollision(laser);
                    if (pt!= null) {
                        laser.updateEndPoint(pt);
                    }
                    laser.updateXOffset(xMapOffset);
                    Cc.getInstance().getOpsList().enqueue(laser, CollisionOp.Operation.ADD);
                    Sound.playSound("laser.wav");
                    p.weapon2cooldown = 25;
                }
                break;
            case 3:
                if (p.weapon3cooldown == 0){
                    Projectile spread1 = new Projectile(Cc.getInstance().getPlayer(), Color.white, Cc.PLAYER_SPREAD_RANGE, Cc.PLAYER_SPREAD_SPEED, true);
                    spread1.initForSpreadProjectiles(p.getOrientation()-4);
                    Projectile spread2 = new Projectile(Cc.getInstance().getPlayer(), Color.white, Cc.PLAYER_SPREAD_RANGE, Cc.PLAYER_SPREAD_SPEED, true);
                    spread2.initForSpreadProjectiles(p.getOrientation());
                    Projectile spread3 = new Projectile(Cc.getInstance().getPlayer(), Color.white, Cc.PLAYER_SPREAD_RANGE, Cc.PLAYER_SPREAD_SPEED, true);
                    spread3.initForSpreadProjectiles(p.getOrientation()+4);
                    Cc.getInstance().getOpsList().enqueue(spread1, CollisionOp.Operation.ADD);
                    Cc.getInstance().getOpsList().enqueue(spread2, CollisionOp.Operation.ADD);
                    Cc.getInstance().getOpsList().enqueue(spread3, CollisionOp.Operation.ADD);
                    Sound.playSound("shotgun.wav");
                    p.weapon3cooldown = 5;
                }
                break;
        }
    }

    private Point checkLaserCollision(Laser laser){
        // collision with ground
        double min_length = Double.MAX_VALUE;
        Object min_obj = null;
        Point min_point = laser.endPoint;

        for (Ground g : Cc.getInstance().getMap().grounds){
            if (Line2D.linesIntersect(laser.startPoint.getX(), laser.startPoint.getY(), laser.endPoint.getX(),laser.endPoint.getY(), g.x1,g.y,g.x2,g.y)) {

                Point p = intersectionPoint(laser.startPoint.getX(), laser.startPoint.getY(), laser.endPoint.getX(),laser.endPoint.getY(), g.x1,g.y,g.x2,g.y);
                if (p.distance(laser.startPoint) < min_length) {
                    min_length = p.distance(laser.startPoint);
                    min_obj = g;
                    min_point = p;
                }
            }
        }

        for (Movable enemy : Cc.getInstance().getMovFoes()){
            double x = enemy.getMapPosition().getX();
            double x1 = x - enemy.getRadius();
            double x2 = x + enemy.getRadius();

            double y = enemy.getMapPosition().getY();
            double y1 = y - enemy.getRadius();
            double y2 = y + enemy.getRadius();

            if (Line2D.linesIntersect(laser.startPoint.getX(), laser.startPoint.getY(), laser.endPoint.getX(),laser.endPoint.getY(), x1,y,x2,y)) {
                Point p = intersectionPoint(laser.startPoint.getX(), laser.startPoint.getY(), laser.endPoint.getX(),laser.endPoint.getY(), x1,y,x2,y);
                System.out.println(p);
                if (p.distance(laser.startPoint) < min_length) {

                    min_length = p.distance(laser.startPoint);
                    min_obj = enemy;
                    min_point = p;
                    System.out.println(p);
                }
            } else if (Line2D.linesIntersect(laser.startPoint.getX(), laser.startPoint.getY(), laser.endPoint.getX(),laser.endPoint.getY(), x,y1,x,y2)) {
                Point p = intersectionPoint(laser.startPoint.getX(), laser.startPoint.getY(), laser.endPoint.getX(),laser.endPoint.getY(), x,y1,x,y2);
                if (p.distance(laser.startPoint) < min_length) {
                    min_length = p.distance(laser.startPoint);
                    min_obj = enemy;
                    min_point = p;
                }
            }
        }

        if (min_obj instanceof Enemy) {
            ((Enemy) min_obj).isHit();
        }

        return min_point;
    }

    private Point intersectionPoint(double x1,double y1,double x2,double y2,double x3,double y3,double x4,double y4) {

        // reimplemented from here
        // http://stackoverflow.com/questions/31506740/java-find-intersection-of-two-lines

        double m1 = (y2-y1)/ (x2-x1);
        double m2 = (y4-y3)/ (x4-x3);

        double int1 = y1 - m1 * x1;
        double int2 = y3 - m2 * x3;

        double x;
        double y;
        if (x4 == x3) {
            x = x3;
            y = m1 * x + int1;

        } else if(x1==x2) {
            x = x1;
            y = m2 * x + int2;
        } else {
            x = (int1 - int2) / (m2 - m1);
            y = m2 * x + int2;
        }


        return new Point((int) x, (int) y);

    }
    private boolean checkCharacterGroundCollision(Character p, Ground g) {
        Point basePoint = p.getBasePoint();
        Point headPoint = p.getHeadPoint();

        if (Line2D.linesIntersect(basePoint.getX(), basePoint.getY(), basePoint.getX() + p.getDeltaX(), basePoint.getY() + p.getDeltaY(), g.x1 - xMapOffset, g.y, g.x2 - xMapOffset, g.y) &&
                p.getDeltaY() > 0) {
            // landing collision on ground
            p.setDeltaY(g.y - basePoint.getY());
            return true;
        } else if (Line2D.linesIntersect(headPoint.getX(), headPoint.getY(), headPoint.getX() + p.getDeltaX(), headPoint.getY() + p.getDeltaY(), g.x1 - xMapOffset, g.y, g.x2 - xMapOffset, g.y) &&
                p.getDeltaY() < 0) {
            // jumping collision on ground
            p.iJumpCounter = 0;
            p.setDeltaY(g.y - headPoint.getY());
            return true;
        }
        return false;
    }

    private boolean checkProjectileGroundCollisions(Projectile p, Ground g) {
        if (Line2D.linesIntersect(p.getCenter().getX(), p.getCenter().getY(), p.getCenter().getX() + p.getDeltaX(), p.getCenter().getY() + p.getDeltaY(), g.x1 - xMapOffset, g.y, g.x2 - xMapOffset, g.y)) {
            p.setExpire(0);
            return true;
        }
        return false;
    }

    private boolean checkProjectileHitCharacter(Projectile p, Character c) {
        if (p.getCenter().distance(c.getCenter()) < (p.getRadius() + c.getRadius())) {
            return true;
        }
        return false;
    }

    public void playerFireProjectile() {


    }

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

    private void collisionOperation(CollisionOp.Operation operation, Movable mov, java.util.List list) {
        if (operation == CollisionOp.Operation.ADD){
            list.add(mov);
        } else {
            list.remove(mov);
        }
    }

    //some methods for timing events in the game,
    //such as the appearance of UFOs, floaters (power-ups), etc.
    public void tick() {
        if (nTick == Integer.MAX_VALUE)
            nTick = 0;
        else
            nTick++;
    }

    public int getTick() {
        return nTick;
    }

    // Called when user presses 's'
    public void startGame() {
        Cc.getInstance().clearAll();
        Cc.getInstance().initGame();
        Cc.getInstance().setLevel(1);
        Cc.getInstance().gameState = Cc.GameState.Level;
        //if (!bMuted)
        // clpMusicBackground.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void startLevel(){
        Cc.getInstance().startLevel(Cc.getInstance().getLevel());
        Cc.getInstance().getPlayer().lives = Cc.PLAYER_LIVES;
        xMapOffset=0;
        bLeft = false;
        bRight = false;
    }


    // Varargs for stopping looping-music-clips
    private static void stopLoopingSounds(Clip... clpClips) {
        for (Clip clp : clpClips) {
            clp.stop();
        }
    }

    public GamePanel getGmpPanel() {
        return gmpPanel;
    }
}


