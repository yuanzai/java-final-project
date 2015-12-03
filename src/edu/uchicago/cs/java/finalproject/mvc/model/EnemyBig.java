package edu.uchicago.cs.java.finalproject.mvc.model;

import edu.uchicago.cs.java.finalproject.mvc.RobotASVG;

import java.awt.*;

/**
 * Created by junyuanlau on 1/12/15.
 */
public class EnemyBig extends Enemy{

    public EnemyBig(int x, int y, int rad) {
        super(x, y, rad);
        hitPoints = Cc.ENEMYBIG_HITPOINTS;
    }

    private void drawSVG(Graphics g) {
        RobotASVG.paint((Graphics2D) g, getCenter().x, getCenter().y,(int)(getRadius()*2.2),Color.pink);
    }
}
