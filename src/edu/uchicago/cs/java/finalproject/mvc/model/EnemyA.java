package edu.uchicago.cs.java.finalproject.mvc.model;

import edu.uchicago.cs.java.finalproject.SVG.RobotASVG;
import edu.uchicago.cs.java.finalproject.SVG.RobotBSVG;

import java.awt.*;

/**
 * Created by Junyuan on 12/4/2015.
 */
public class EnemyA extends Enemy {
    public EnemyA(int x, int y) {
        super(x, y, Cc.ENEMYA_MOVE_RADIUS, Cc.ENEMYA_HITPOINTS,Cc.ENEMYA_MOVE_SPEED);

    }

    public void drawSVG(Graphics g) {
        RobotASVG.paint((Graphics2D) g, getCenter().x, getCenter().y,(int)(getRadius()*2.2),Color.cyan);
    }

    public void fire() {
        if (fireCountdown>0)
            return;
        Projectile projectile = new Projectile(this, Color.red, Cc.ENEMYA_PROJECTILE_RANGE, Cc.ENEMYA_PROJECTILE_SPEED, 6);
        Cc.getInstance().getOpsList().enqueue(projectile, CollisionOp.Operation.ADD);
    }
}
