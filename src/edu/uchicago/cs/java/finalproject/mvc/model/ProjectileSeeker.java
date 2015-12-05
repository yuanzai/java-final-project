package edu.uchicago.cs.java.finalproject.mvc.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Junyuan on 12/1/2015.
 */
public class ProjectileSeeker extends Projectile {

    private final int MAX_SEEKER_ADJ_COUNTER = 3;
    private int seekAdjCounter;

    public ProjectileSeeker(Character character, Color color, int range, int speed){

        super( character, color, range, speed);

/*        //this.range = range;
        //this.speed = speed;
        if (character.getTeam() == Team.PLAYER) {
            setTeam(Team.FRIENDLYFIRE);
        } else if (character.getTeam() == Team.FOE) {
            setTeam(Team.ENEMYFIRE);
        }*/

        //defined the points on a cartesean grid
        ArrayList<Point> pntCs = new ArrayList<Point>();

        pntCs.add(new Point(0,3)); //top point

        pntCs.add(new Point(1,1));
        pntCs.add(new Point(1,-3));
        pntCs.add(new Point(-1,-3));
        pntCs.add(new Point(-1,1));


        assignPolarPoints(pntCs);

        //a bullet expires after 20 frames

        setRadius(10);


        setDeltaX(Math.cos(Math.toRadians(getOrientation())) * speed);
        setDeltaY(Math.sin(Math.toRadians(getOrientation())) * speed);

        setExpire( (int) (range / speed));
        seekAdjCounter = MAX_SEEKER_ADJ_COUNTER;

    }



    //implementing the expire functionality in the move method - added by Dmitriy
    public void move(){

        if (hitWall)
            setExpire(0);

        if (getExpire() == 0) {
            Cc.getInstance().getOpsList().enqueue(this, CollisionOp.Operation.REMOVE);
            return;
        }else {
            setExpire(getExpire() - 1);
        }
        adjustHeading();

        super.move();
    }

    public void adjustHeading() {
        seekAdjCounter--;
        if (seekAdjCounter > 0)
            return;

        Player player = Cc.getInstance().getPlayer();
        double delta_x = player.getCenter().x - getCenter().getX();
        double delta_y = player.getCenter().y - getCenter().getY();

        double theta_radians = Math.atan2(delta_y, delta_x) / Math.PI * 180;

        if (Math.abs(theta_radians - getOrientation()) < 90){
            setOrientation(theta_radians);
        }

        setDeltaX(Math.cos(Math.toRadians(getOrientation())) * speed);
        setDeltaY(Math.sin(Math.toRadians(getOrientation())) * speed);
        seekAdjCounter = MAX_SEEKER_ADJ_COUNTER;
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke((1)));
        super.draw(g2d);
    }

}