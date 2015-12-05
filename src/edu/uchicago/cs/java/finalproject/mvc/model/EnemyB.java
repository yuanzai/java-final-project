package edu.uchicago.cs.java.finalproject.mvc.model;

import edu.uchicago.cs.java.finalproject.SVG.RobotBSVG;

import java.awt.*;

/**
 * Created by junyuanlau on 1/12/15.
 */
public class EnemyB extends Enemy{

    public EnemyB(int x, int y) {
        super(x, y, Cc.ENEMYB_MOVE_RADIUS, Cc.ENEMYB_HITPOINTS,Cc.ENEMYB_MOVE_SPEED);
    }

    public void drawSVG(Graphics g) {
        RobotBSVG.paint((Graphics2D) g, getCenter().x, getCenter().y,(int)(getRadius()*2.2),Color.yellow);
    }

    public void fire() {
        if (fireCountdown==9 || fireCountdown == 6) {
            Projectile burst = new Projectile(this, Color.red, Cc.ENEMYA_PROJECTILE_RANGE,Cc.ENEMYA_PROJECTILE_SPEED,6);
            Cc.getInstance().getOpsList().enqueue(burst, CollisionOp.Operation.ADD);
        }

        if (fireCountdown == 0) {
            ProjectileSeeker projectile = new ProjectileSeeker(this, Color.red, Cc.ENEMYB_PROJECTILE_SEEKER_RANGE, Cc.ENEMYB_PROJECTILE_SEEKER_SPEED);
            Cc.getInstance().getOpsList().enqueue(projectile, CollisionOp.Operation.ADD);
        }
    }
}
