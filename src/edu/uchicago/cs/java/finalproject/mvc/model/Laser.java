package edu.uchicago.cs.java.finalproject.mvc.model;

import edu.uchicago.cs.java.finalproject.mvc.controller.Game;

import java.awt.*;

/**
 * Created by Junyuan on 11/28/2015.
 */
public class Laser extends Sprite{
    public Point startPoint;
    public Point endPoint;
    public boolean bCollisionChecked;
    private int xBounds;
    private int yBounds;
    private double angle;
    private Character owner;
    private int xOffset;

    public Laser(Character character, Color color, Map map, int range) {
        super();
        owner = character;

        if (owner.getTeam() == Team.PLAYER) {
            setTeam(Team.FRIENDLYFIRE);
        } else if (character.getTeam() == Team.FOE) {
            setTeam(Team.ENEMYFIRE);
        }
        startPoint = owner.getMapPosition();

        setColor(color);
        setCenter( new Point(map.xBounds,0) );
        setMapPosition(character.getMapPosition());
        xBounds = map.xBounds;
        yBounds = Game.DIM.height;
        angle = character.getOrientation();

        endPoint = new Point(startPoint.x + (int) (Math.cos( Math.toRadians( angle ) ) * range), startPoint.y + (int) (Math.sin( Math.toRadians( angle ) ) * range));
        setExpire(2);
    }

    public void move(){
        if (getExpire() == 0) {
            Cc.getInstance().getOpsList().enqueue(this, CollisionOp.Operation.REMOVE);
            return;
        }else {
            setExpire(getExpire()- 1);
        }
        super.move();
    }


    @Override
    public void draw(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke((1)));
        g2d.setColor(getColor());
        g2d.drawLine(startPoint.x-xOffset,startPoint.y,endPoint.x-xOffset,endPoint.y);

        //super.draw(g);
    }

    public void updateEndPoint(Point point) {
        endPoint = point;
    }

    public void updateXOffset(int offset) {
        xOffset = offset;
    }
}
