package edu.uchicago.cs.java.finalproject.mvc.controller;

import edu.uchicago.cs.java.finalproject.mvc.model.*;
import edu.uchicago.cs.java.finalproject.mvc.model.Character;

import java.awt.*;
import java.awt.geom.Line2D;

/**
 * Created by Junyuan on 12/4/2015.
 */
public class CollisionCheck {
    /*

    Public static methods which is used to determine various collisions and returns some sort of value for the game engine
    to further process.

     */


    public static boolean checkCharacterGroundCollision(Character p, Ground g, int xMapOffset) {
        // check for character reaching ground while falling or hitting ground while jumping

        Point basePoint = p.getBasePoint();
        Point headPoint = p.getHeadPoint();

        if (Line2D.linesIntersect(basePoint.getX(), basePoint.getY(), basePoint.getX() + p.getDeltaX(), basePoint.getY() + p.getDeltaY(),
                g.x1 - xMapOffset, g.y, g.x2 - xMapOffset, g.y) &&
                p.getDeltaY() > 0) {
            // landing collision on ground
            p.setDeltaY(g.y - basePoint.getY());
            return true;
        } else if (Line2D.linesIntersect(headPoint.getX(), headPoint.getY(), headPoint.getX() + p.getDeltaX(), headPoint.getY() + p.getDeltaY(),
                g.x1 - xMapOffset, g.y, g.x2 - xMapOffset, g.y) &&
                p.getDeltaY() < 0) {
            // jumping collision on ground
            p.iJumpCounter = 0;
            p.setDeltaY(g.y - headPoint.getY());
            return true;
        }
        return false;
    }

    public static boolean checkProjectileGroundCollisions(Projectile p, Ground g, int xMapOffset) {
        // check if projectile collides with ground with 3 pixels offset up and down to prevent hitting character through ground
        if (Line2D.linesIntersect(p.getCenter().getX(), p.getCenter().getY(), p.getCenter().getX() + p.getDeltaX(), p.getCenter().getY() + p.getDeltaY(),
                g.x1 - xMapOffset, g.y+5, g.x2 - xMapOffset, g.y+5)
                || (Line2D.linesIntersect(p.getCenter().getX(), p.getCenter().getY(), p.getCenter().getX() + p.getDeltaX(), p.getCenter().getY() + p.getDeltaY(),
                g.x1 - xMapOffset, g.y-3, g.x2 - xMapOffset, g.y-3))     ) {
            p.setExpire(0);
            return true;
        }
        return false;
    }

    public static boolean checkProjectileHitCharacter(Projectile p, Character c) {
        return (p.getCenter().distance(c.getCenter()) < (p.getRadius() + c.getRadius()));
    }



    public static Point checkLaserCollision(Laser laser){

        double min_length = Double.MAX_VALUE;
        Object min_obj = null;
        Point min_point = laser.endPoint;

        // check laser collision with ground
        for (Ground g : Cc.getInstance().getMap().grounds){
            if (Line2D.linesIntersect(laser.startPoint.getX(), laser.startPoint.getY(), laser.endPoint.getX(),laser.endPoint.getY(), g.x1,g.y,g.x2,g.y)) {

                Point p = intersectionPoint(laser.startPoint.getX(), laser.startPoint.getY(), laser.endPoint.getX(),laser.endPoint.getY(), g.x1,g.y,g.x2,g.y);
                // determine nearest ground/enemy
                if (p.distance(laser.startPoint) < min_length) {
                    min_length = p.distance(laser.startPoint);
                    min_obj = g;
                    min_point = p;
                }
            }
        }

        //check laser collision with enemy
        for (Movable enemy : Cc.getInstance().getMovFoes()){
            double x = enemy.getMapPosition().getX();
            double x1 = x - enemy.getRadius();
            double x2 = x + enemy.getRadius();

            double y = enemy.getMapPosition().getY();
            double y1 = y - enemy.getRadius();
            double y2 = y + enemy.getRadius();

            if (Line2D.linesIntersect(laser.startPoint.getX(), laser.startPoint.getY(), laser.endPoint.getX(),laser.endPoint.getY(), x1,y,x2,y)) {
                // check laser collision with enemy x axis
                Point p = intersectionPoint(laser.startPoint.getX(), laser.startPoint.getY(), laser.endPoint.getX(),laser.endPoint.getY(), x1,y,x2,y);

                // determine nearest ground/enemy
                if (p.distance(laser.startPoint) < min_length) {
                    min_length = p.distance(laser.startPoint);
                    min_obj = enemy;
                    min_point = p;
                }
            } else if (Line2D.linesIntersect(laser.startPoint.getX(), laser.startPoint.getY(), laser.endPoint.getX(),laser.endPoint.getY(), x,y1,x,y2)) {
                // check laser collision with enemy y axis
                Point p = intersectionPoint(laser.startPoint.getX(), laser.startPoint.getY(), laser.endPoint.getX(),laser.endPoint.getY(), x,y1,x,y2);

                // determine nearest ground/enemy
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

        return min_point;  // returns point where laser should stop. if null, laser will continue for predetermined length.
    }

    public static Point intersectionPoint(double x1,double y1,double x2,double y2,double x3,double y3,double x4,double y4) {

        // determines if 2 lines cross, mainly for laser line versus ground line/ enemy x and y axis line

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
}
