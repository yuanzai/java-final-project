package edu.uchicago.cs.java.finalproject.mvc.model;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Junyuan on 11/26/2015.
 */
public class Character extends Sprite{

    // extends sprite
    // main difference is that character is susceptile to gravity.
    // this class applies a y offset movement when the character is not on the ground
    // and a nice health bar too.

    public boolean bOnGround;
    public int iJumpCounter;
    final double GRAVITY = 25;

    public Point getBasePoint(){
        return null;
    };

    public Point getHeadPoint(){
        return null;
    };

    public void drawHealthbar(Graphics g, int hitPoints, int maxHitpoints) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(1));
        g2d.setColor(Color.green);
        g2d.drawRect(getCenter().x - getRadius(),getCenter().y - getRadius()-6,2*getRadius(), 4);
        g2d.fillRect(getCenter().x - getRadius(),getCenter().y - getRadius()-6,(int) (2 * (double)getRadius() * (double)hitPoints/(double)maxHitpoints), 4);
    }

    public void drawCustomPolygon(Graphics g, Color col,int stroke, int orientationRestriction, ArrayList<Point> points, boolean isFill, int radiusOverwrite) {
        double[] dDegrees = convertToPolarDegs(points);
        double[] dLengths = convertToPolarLens(points);

        int[] nXCoords = new int[dDegrees.length];
        int[] nYCoords = new int[dDegrees.length];
        //need this as well
        Point[] pntCoords = new Point[dDegrees.length];

        double restrictedOrientation = getOrientation();
        int xFlip = (restrictedOrientation< -90 || restrictedOrientation>90 ? -1 : 1);
        if (restrictedOrientation > orientationRestriction && restrictedOrientation <= 90){
            restrictedOrientation = orientationRestriction;
        } else if (restrictedOrientation >= 90 && restrictedOrientation < 180 - orientationRestriction){
            restrictedOrientation = 180 - orientationRestriction;
        } else if (restrictedOrientation <= -90 && restrictedOrientation > orientationRestriction-180){
            restrictedOrientation = orientationRestriction-180;
        } else if (restrictedOrientation < -orientationRestriction && restrictedOrientation >= -90){
            restrictedOrientation = -orientationRestriction;
        }

        if(restrictedOrientation< -90 || restrictedOrientation>90){
            restrictedOrientation = -restrictedOrientation;
        }

        for (int nC = 0; nC < dDegrees.length; nC++) {
            nXCoords[nC] =    (int) (getCenter().x + radiusOverwrite
                    *  dLengths[nC]
                    * Math.sin(Math.toRadians(restrictedOrientation) + dDegrees[nC]));
            nYCoords[nC] =    (int) (getCenter().y - xFlip * radiusOverwrite
                    * dLengths[nC]
                    * Math.cos(Math.toRadians(restrictedOrientation) + dDegrees[nC]));
            pntCoords[nC] = new Point(nXCoords[nC], nYCoords[nC]);
        }

        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(stroke));
        g2d.setColor(col);
        if (isFill) {
            g2d.fillPolygon(nXCoords, nYCoords, dDegrees.length);
        } else {
            g2d.drawPolygon(nXCoords, nYCoords, dDegrees.length);
        }
    }
    public void drawCustomPolygon(Graphics g, Color col,int stroke, int orientationRestriction, ArrayList<Point> points, boolean isFill) {
        drawCustomPolygon(g,col,stroke,orientationRestriction,points,isFill,getRadius());
    }

    public void fire() {

    }
}
