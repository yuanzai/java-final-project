package edu.uchicago.cs.java.finalproject.mvc.model;

import edu.uchicago.cs.java.finalproject.mvc.controller.Game;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by junyuanlau on 19/11/15.
 */
public class Projectile extends Sprite {
    private int range;
    private int speed;
    public Projectile(Character character, Color color, int range, int speed, boolean isSpread){

        super();

        this.range = range;
        this.speed = speed;
        if (character.getTeam() == Team.PLAYER) {
            setTeam(Team.FRIENDLYFIRE);
        } else if (character.getTeam() == Team.FOE) {
            setTeam(Team.ENEMYFIRE);
        }

            //defined the points on a cartesean grid
        ArrayList<Point> pntCs = new ArrayList<Point>();

        pntCs.add(new Point(0,3)); //top point

        pntCs.add(new Point(1,-1));
        pntCs.add(new Point(0,-2));
        pntCs.add(new Point(-1,-1));

        assignPolarPoints(pntCs);

        //a bullet expires after 20 frames

        setRadius(6);
        setColor(color);
        setOrientation(character.getOrientation());

        if (!isSpread) {
            if (character instanceof Player) {
                Player p = (Player) character;
                setOrientation(getOrientation() + (new Random().nextInt((int) p.mouseDistance * 2) - p.mouseDistance) / 250);
            }

            setDeltaX(Math.cos(Math.toRadians(getOrientation())) * speed);
            setDeltaY(Math.sin(Math.toRadians(getOrientation())) * speed);

            int expireByDistance = (int) (range / Math.sqrt(getDeltaX() * getDeltaX() + getDeltaY() * getDeltaY()));
            setExpire(expireByDistance);
        }
        setCenter( character.getCenter() );
        setMapPosition(character.getMapPosition());
    }

    public void initForSpreadProjectiles(double orientation){
        setOrientation(orientation);
        setDeltaX(Math.cos(Math.toRadians(getOrientation())) * speed);
        setDeltaY(Math.sin(Math.toRadians(getOrientation())) * speed);

        int expireByDistance = (int) (range / Math.sqrt(getDeltaX() * getDeltaX() + getDeltaY() * getDeltaY()));
        setExpire(expireByDistance);
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

        super.move();
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke((1)));
        super.draw(g2d);
    }

}
