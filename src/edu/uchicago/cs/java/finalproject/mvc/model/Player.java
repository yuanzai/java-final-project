package edu.uchicago.cs.java.finalproject.mvc.model;

import edu.uchicago.cs.java.finalproject.mvc.controller.Game;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;

/**
 * Created by junyuanlau on 19/11/15.
 */


public class Player extends Character {
    public boolean bLeft;
    public boolean bRight;
    public boolean bUp;
    public boolean bDown;

    public boolean bPressed;

    public int lives;

    public Point pntMousePoint;

    public double mouseDistance;

    public static final double STEP = 18;
    private final double JUMP = 85;
    private final int JUMP_FRAMES = 7;
    private final int HITCOUNTS = 9;

    public int currentWeapon;
    public int weapon1cooldown;
    public int weapon2cooldown;
    public int weapon3cooldown;

    public int hitCounter;

    public Player(int x, int y) {
        super();
        setTeam(Team.PLAYER);

        /*ArrayList<Point> pntCs = new ArrayList<Point>();
        pntCs.add(new Point(15, 12));
        pntCs.add(new Point(-15, 8));
        pntCs.add(new Point(-15, -8));
        pntCs.add(new Point(15, -8));
        assignPolarPoints(pntCs);*/
        setColor(Color.white);
        setCenter(new Point(x, y));
        setMapPosition(new Point(x, y));
        setRadius(30);
        currentWeapon = 1;
        weapon1cooldown = 0;
        weapon2cooldown = 0;
        weapon3cooldown = 0;
        hitCounter = 0;
        //bOnGround = false;
    }

    public Point getBasePoint() {
        Point center = getCenter();
        return new Point(center.x, center.y + getRadius());
    }

    public Point getHeadPoint() {
        Point center = getCenter();
        return new Point(center.x, center.y - getRadius());
    }


    public void preMove(){
        setOrientationToMouse();
        weaponCooldown();
        double xAdj = 0;
        double yAdj = 0;
        if (bLeft) {
            xAdj -= STEP;
        } else if (bRight) {
            xAdj += STEP;
        }

        if (bUp && bOnGround && iJumpCounter ==0) {
            yAdj -= JUMP;
            iJumpCounter = JUMP_FRAMES;
            //bOnGround = false;
        } else if (iJumpCounter > 0) {
            iJumpCounter--;
            yAdj -= JUMP - (JUMP_FRAMES-iJumpCounter) * 10;
        }


        if (!bOnGround)
            yAdj +=GRAVITY;

        //System.out.println("xAdj " + xAdj + "   yAdj "  + yAdj);
        setDeltaX( xAdj );
        setDeltaY( yAdj );
    }

    public void weaponCooldown(){
        switch (currentWeapon){
            case 1:
                if (weapon1cooldown > 0)
                    weapon1cooldown--;
                break;
            case 2:
                if (weapon2cooldown > 0)
                    weapon2cooldown--;
                break;
            case 3:
                if (weapon3cooldown > 0)
                    weapon3cooldown--;
                break;
        }
    }

    public void move() {

        super.move();
    }

    public void pressUp() {
        System.out.println("Press Up");
        bUp = true;
    }
    public void pressDown() {
        bDown = true;
    }
    public void pressLeft() {
        bLeft = true;
    }
    public void pressRight() {
        bRight = true;
    }

    public void releaseUp(){
        bUp = false;
    }
    public void releaseDown(){ bDown = false; }
    public void releaseLeft(){
        bLeft = false;
    }
    public void releaseRight(){
        bRight = false;
    }

    public void clearMove() {
        bLeft = false;
        bRight = false;
        bUp = false;
        bDown = false;
    }

    public void draw(Graphics g) {

        //BODY
        ArrayList<Point> pntCs = new ArrayList<Point>();
        pntCs.add(new Point(13, 12));
        pntCs.add(new Point(-13, 8));
        pntCs.add(new Point(-13, -8));
        pntCs.add(new Point(13, -8));

        drawCustomPolygon(g,Color.gray,2,30,pntCs,true);

        //assignPolarPoints(pntCs);
        //drawPlayerWithColor(g, Color.white);


        //HEAD
        pntCs = new ArrayList<Point>();
        pntCs.add(new Point(20, 8));
        pntCs.add(new Point(20, -8));
        pntCs.add(new Point(30, -10));
        pntCs.add(new Point(30, 10));
        drawCustomPolygon(g,Color.white,2,30,pntCs,true);


        //LEG
        pntCs = new ArrayList<Point>();
        pntCs.add(new Point(-14, 4));
        pntCs.add(new Point(-14, -5));
        pntCs.add(new Point(-34, -7));
        pntCs.add(new Point(-34, 6));
        drawCustomPolygon(g,Color.white,2,10,pntCs,true);


        //GUN ARM
        pntCs = new ArrayList<Point>();
        pntCs.add(new Point(10, 25));
        pntCs.add(new Point(10, 0));
        pntCs.add(new Point(0, 0));
        pntCs.add(new Point(0, 25));
        drawCustomPolygon(g,Color.white,2,90,pntCs,true);

        drawCrossHair(g);

        if (hitCounter > 0 && hitCounter < HITCOUNTS) {
            hitCounter++;
        } else {
            hitCounter = 0;
        }
    }

    public void isHit() {
        hitCounter = 1;
        lives--;
    }
    public void drawSVG(Graphics g) {

    }

    public void drawCrossHair(Graphics g) {
        if (pntMousePoint != null) {
            mouseDistance = Math.sqrt((pntMousePoint.getX() - getCenter().getX()) * (pntMousePoint.getX() - getCenter().getX()) + (pntMousePoint.getY() - getCenter().getY()) * (pntMousePoint.getY() - getCenter().getY()));
            Graphics2D g2d = (Graphics2D) g;

            if (currentWeapon == 1) {
                g2d.setStroke(new BasicStroke(1));
                if (mouseDistance > Cc.PLAYER_PROJECTILE_RANGE) {
                    g2d.setColor(Color.gray);
                    crosshairGraphics(g2d, pntMousePoint.x, pntMousePoint.y, 100);
                } else {
                    g2d.setColor(Color.GREEN);
                    crosshairGraphics(g2d, pntMousePoint.x, pntMousePoint.y, Math.max(20, (int) mouseDistance / 10));
                }

            } else if (currentWeapon == 2) {
                g2d.setStroke(new BasicStroke(1));
                if (mouseDistance > Cc.PLAYER_LASER_RANGE) {
                    g2d.setColor(Color.gray);
                    crosshairGraphics(g2d, pntMousePoint.x, pntMousePoint.y, 20);
                } else {
                    crosshairGraphics(g2d, pntMousePoint.x, pntMousePoint.y, 20);
                }
            } else if (currentWeapon == 3) {
                g2d.setStroke(new BasicStroke(2));
                if (mouseDistance > Cc.PLAYER_SPREAD_RANGE) {
                    g2d.setColor(Color.gray);
                    crosshairGraphics(g2d, pntMousePoint.x, pntMousePoint.y, 40);
                } else {
                    crosshairGraphics(g2d, pntMousePoint.x, pntMousePoint.y, 40);
                }
            }
        }
    }

    private void crosshairGraphics(Graphics2D g2d,int x,int y, int crosshairSize) {
        if (currentWeapon == 1 ) {
            g2d.drawOval(x - crosshairSize / 2, y - crosshairSize / 2, crosshairSize, crosshairSize);
            g2d.drawOval(x - crosshairSize/2 +4, y - crosshairSize/2 +4, crosshairSize-8, crosshairSize-8);
            g2d.drawLine(x - crosshairSize / 2, y, x + crosshairSize / 2, y);
            g2d.drawLine(x, y - crosshairSize / 2, x, y + crosshairSize / 2);
        } else if (currentWeapon == 2 ) {
            g2d.setColor(Color.GREEN);
            g2d.drawOval(x - crosshairSize / 2, y - crosshairSize / 2, crosshairSize, crosshairSize);
            Color c = new Color(205,1,1);
            g2d.setColor(c);
            g2d.drawLine(x - crosshairSize / 2+1, y, x + crosshairSize / 2-1, y);
            g2d.drawLine(x, y - crosshairSize / 2+1, x, y + crosshairSize / 2-1);
        } else if (currentWeapon == 3 ) {
            g2d.drawLine(x - crosshairSize / 2+2, y, x - crosshairSize / 2-2, y);
            g2d.drawLine(x + crosshairSize / 2+2, y, x + crosshairSize / 2-2, y);
            g2d.drawLine(x, y - crosshairSize / 2+2, x, y - crosshairSize / 2-2);
            g2d.drawLine(x, y + crosshairSize / 2+2, x, y + crosshairSize / 2-2);
        }
        //if (currentWeapon == 2)
            //g2d.drawOval(x - crosshairSize/2, y - crosshairSize/2, crosshairSize, crosshairSize-2);
    }

    public void setOrientationToMouse(){
        //http://stackoverflow.com/questions/2676719/calculating-the-angle-between-the-line-defined-by-two-points
        if (pntMousePoint != null) {
            double delta_x = pntMousePoint.getX() - getCenter().getX();
            double delta_y = pntMousePoint.getY() - getCenter().getY();
            double theta_radians = Math.atan2(delta_y, delta_x) / Math.PI * 180;
            setOrientation( theta_radians);
        }
    }

}
