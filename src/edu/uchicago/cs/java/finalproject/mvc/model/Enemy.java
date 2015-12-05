package edu.uchicago.cs.java.finalproject.mvc.model;

import edu.uchicago.cs.java.finalproject.SVG.RobotASVG;
import edu.uchicago.cs.java.finalproject.SVG.SplatterSVG;

import java.awt.*;
import java.util.Random;

/**
 * Created by Junyuan on 11/26/2015.
 */
public class Enemy extends Character{

    private final int FIRE_RATE = 50;
    public int fireCountdown;

    private int moveCounter;
    private boolean moveDirection;
    private int maxMoves = 100;
    double moveSpeed;

    int hitPoints;
    int maxHitPoints;

    public boolean isDead;

    private int splatX;
    private int splatY;

    public Enemy(int x, int y, int rad, int hitPoints, int moveSpeed) {
        super();
        setTeam(Team.FOE);
        setColor(Color.BLUE);
        setCenter(new Point(x,y));
        setMapPosition(new Point(x, y));
        setRadius(rad);
        fireCountdown = new Random().nextInt(FIRE_RATE);
        moveDirection = (Math.random() > .5 ? true : false);

        this.hitPoints = hitPoints;
        maxHitPoints = hitPoints;
        this.moveSpeed = moveSpeed;
        setExpire(-1);
        Cc.getInstance().addEnemyCount();
        //System.out.println("enemycount - " + Cc.getInstance().getEnemyCount() + " x - " + x + " y " + y);

    }

    public Point getBasePoint() {
        Point center = getCenter();
        return new Point(center.x, center.y + getRadius());
    }

    public Point getHeadPoint() {
        Point center = getCenter();
        return new Point(center.x, center.y - getRadius());
    }

    public void turnToPlayer(Player player) {
        double delta_x = player.getCenter().x - getCenter().getX();
        double delta_y = player.getCenter().y - getCenter().getY();

        double theta_radians = Math.atan2(delta_y, delta_x) / Math.PI * 180;
        setOrientation((int) theta_radians);

        if (Math.sqrt(delta_x * delta_x+delta_y*delta_y) < 500) {
            fireCountdown = Math.min(fireCountdown, FIRE_RATE/2);
        }
    }

    public void preMove(){

        double xAdj = 0;
        double yAdj = 0;

        if (!bOnGround) {
            yAdj += GRAVITY;
            if (moveCounter > 0 && moveCounter != maxMoves){
                // prevent from falling off grouind
                xAdj = moveDirection ? -moveSpeed : moveSpeed;
                xAdj = 2 * xAdj;
                yAdj = 0;
                moveCounter = maxMoves;
                moveDirection = !moveDirection;
            }
        } else {
            if (moveCounter > 0){
                xAdj = moveDirection ? moveSpeed : -moveSpeed;
            } else {
                moveCounter = maxMoves;
                moveDirection = !moveDirection;
            }
        }

        setDeltaX( xAdj );
        setDeltaY( yAdj );

        if (isDead) {
            setDeltaX( 0 );
        }
    }

    public void isHit() {
        if (isDead)
            return;

        hitPoints--;
        if (hitPoints == 0)
            isDead();
    }

    public void isDead() {
        isDead = true;
        setExpire(8);
        splatX = new Random().nextInt(getRadius()) - getRadius();
        splatY = new Random().nextInt(getRadius()) - getRadius();
        Cc.getInstance().killEnemy();
    }

    public void move() {
        if (getExpire() == 0) {
            Cc.getInstance().getOpsList().enqueue(this, CollisionOp.Operation.REMOVE);
            return;
        }else {
            setExpire(getExpire() - 1);
        }
        fireCountdown--;

        if (fireCountdown < 0)
            fireCountdown = FIRE_RATE;

        if (moveCounter> 0)
            moveCounter--;

        if (isDead)
            fireCountdown = FIRE_RATE;
        super.move();
    }

    public void draw(Graphics g) {
        // Health Bar
        drawHealthbar(g,hitPoints,maxHitPoints);

        // Draw SVG
        drawSVG(g);

        // Blood splatter
        if (isDead)
            SplatterSVG.paint((Graphics2D)g, getCenter().x+splatX,getCenter().y+splatY, 35);
    }

    public void drawSVG(Graphics g) {

    }

    public void fire() {

    }
}
