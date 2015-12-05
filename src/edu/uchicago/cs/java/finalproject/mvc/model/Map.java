package edu.uchicago.cs.java.finalproject.mvc.model;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Junyuan on 11/25/2015.
 */
public class Map {

    public static int GROUND_HEIGHT = 145;
    public ArrayList<Ground> grounds;
    public int xBounds;

    public Map() {
        grounds = new ArrayList<>();



        grounds.add(new Ground(GROUND_HEIGHT*4,1400,2600));
        grounds.add(new Ground(GROUND_HEIGHT*4,0,400));


        grounds.add(new Ground(GROUND_HEIGHT*3,200,900));
        grounds.add(new Ground(GROUND_HEIGHT*3,1200,2000));
        grounds.add(new Ground(GROUND_HEIGHT*3,2600,3000));


        grounds.add(new Ground(GROUND_HEIGHT*2,0,100));
        grounds.add(new Ground(GROUND_HEIGHT*2,900,1500));
        grounds.add(new Ground(GROUND_HEIGHT*2,2900,3200));

        grounds.add(new Ground(GROUND_HEIGHT*1,0,300));
        grounds.add(new Ground(GROUND_HEIGHT*1,600,1000));
        grounds.add(new Ground(GROUND_HEIGHT*1,1600,1800));
        grounds.add(new Ground(GROUND_HEIGHT*1,2600,3200));

        grounds.add(new Ground(0,0,3200));
        for (Ground g : grounds) {
            xBounds = Math.max(xBounds, g.x2);
        }

    }


    public void draw(Graphics g, int xOffset, int yHeight) {
        Graphics2D g2d = (Graphics2D) g;


        g2d.setStroke(new BasicStroke(4));
        Color c = new Color(66, 167, 66);
        g2d.setColor(c);
        for (Ground gr : grounds) {
            g2d.drawLine(gr.x1 - xOffset,gr.y,gr.x2-xOffset,gr.y);
        }


        g2d.drawLine(0-xOffset,0,0-xOffset,yHeight);
        g2d.drawLine(xBounds-xOffset-5,0,xBounds-xOffset-5,yHeight);
        g2d.fillRect(0,yHeight-50,xBounds,50);
    }

    public void drawWall(Graphics g) {

        //g2d.drawRect();
    }

}
