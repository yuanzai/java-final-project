package edu.uchicago.cs.java.finalproject.mvc.model;

import edu.uchicago.cs.java.finalproject.SVG.RobotCSVG;

import java.awt.*;

/**
 * Created by Junyuan on 12/4/2015.
 */
public class EnemyC extends Enemy {
    public EnemyC(int x, int y) {
        super(x, y, Cc.ENEMYC_MOVE_RADIUS, Cc.ENEMYC_HITPOINTS,Cc.ENEMYC_MOVE_SPEED);
    }

    public void drawSVG(Graphics g) {
        RobotCSVG.paint((Graphics2D) g, getCenter().x, getCenter().y,(int)(getRadius()*2.2),Color.magenta);
    }

    public void fire() {
        if (fireCountdown==15 || fireCountdown == 9) {
            Projectile burst = new Projectile(this, Color.red, Cc.ENEMYA_PROJECTILE_RANGE,Cc.ENEMYA_PROJECTILE_SPEED,6);
            Cc.getInstance().getOpsList().enqueue(burst, CollisionOp.Operation.ADD);
        }

        if (fireCountdown == 0) {
            turnToPlayer(Cc.getInstance().getPlayer());
            Projectile spread1 = new Projectile(this, Color.red, Cc.ENEMYA_PROJECTILE_RANGE *2,Cc.ENEMYA_PROJECTILE_SPEED /2,9);
            spread1.initForSpreadProjectiles(getOrientation()-4);

            Projectile spread2 = new Projectile(this, Color.red, Cc.ENEMYA_PROJECTILE_RANGE *2, Cc.ENEMYA_PROJECTILE_SPEED /2, 9);
            spread2.initForSpreadProjectiles(getOrientation());

            Projectile spread3 = new Projectile(this, Color.red, Cc.ENEMYA_PROJECTILE_RANGE *2, Cc.ENEMYA_PROJECTILE_SPEED /2, 9);
            spread3.initForSpreadProjectiles(getOrientation()+4);

            Cc.getInstance().getOpsList().enqueue(spread1, CollisionOp.Operation.ADD);
            Cc.getInstance().getOpsList().enqueue(spread2, CollisionOp.Operation.ADD);
            Cc.getInstance().getOpsList().enqueue(spread3, CollisionOp.Operation.ADD);
        }
    }
}
