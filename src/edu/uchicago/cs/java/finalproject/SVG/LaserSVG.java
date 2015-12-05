package edu.uchicago.cs.java.finalproject.SVG;

import java.awt.*;
import java.awt.geom.*;
import static java.awt.Color.*;
import static java.awt.MultipleGradientPaint.CycleMethod.*;
import static java.awt.MultipleGradientPaint.ColorSpaceType.*;

/**
 * This class has been automatically generated using
 * <a href="https://flamingo.dev.java.net">Flamingo SVG transcoder</a>.
 */
public class LaserSVG {

    /**
     * Paints the transcoded SVG image on the specified graphics context. You
     * can install a custom transformation on the graphics context to scale the
     * image.
     * 
     * @param g Graphics context.
     */
    public static void paint(Graphics2D g, int x, int y, int length, Color color) {

        // taken from http://game-icons.net/delapouite/originals/crosshair.html

        Shape shape = null;
        int side = length;
        double scale = (double)side/512;
        g.setStroke(new BasicStroke(2));
        g.setPaint(color);

        g.drawRect(x,y,side,side);

        // _0_1
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(247.0, 32.0);
        ((GeneralPath) shape).lineTo(247.0, 55.21);
        ((GeneralPath) shape).curveTo(143.25, 59.8, 59.798, 143.25, 55.21, 247.0);
        ((GeneralPath) shape).lineTo(32.0, 247.0);
        ((GeneralPath) shape).lineTo(32.0, 265.0);
        ((GeneralPath) shape).lineTo(55.21, 265.0);
        ((GeneralPath) shape).curveTo(59.8, 368.75, 143.25, 452.202, 247.0, 456.79);
        ((GeneralPath) shape).lineTo(247.0, 480.0);
        ((GeneralPath) shape).lineTo(265.0, 480.0);
        ((GeneralPath) shape).lineTo(265.0, 456.79);
        ((GeneralPath) shape).curveTo(368.75, 452.2, 452.202, 368.75, 456.79, 265.0);
        ((GeneralPath) shape).lineTo(480.0, 265.0);
        ((GeneralPath) shape).lineTo(480.0, 247.0);
        ((GeneralPath) shape).lineTo(456.79, 247.0);
        ((GeneralPath) shape).curveTo(452.2, 143.25, 368.75, 59.798, 265.0, 55.21);
        ((GeneralPath) shape).lineTo(265.0, 32.0);
        ((GeneralPath) shape).lineTo(247.0, 32.0);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(247.0, 73.223);
        ((GeneralPath) shape).lineTo(247.0, 128.0);
        ((GeneralPath) shape).lineTo(265.0, 128.0);
        ((GeneralPath) shape).lineTo(265.0, 73.223);
        ((GeneralPath) shape).curveTo(359.0, 77.76, 434.24, 153.0, 438.777, 247.0);
        ((GeneralPath) shape).lineTo(384.0, 247.0);
        ((GeneralPath) shape).lineTo(384.0, 265.0);
        ((GeneralPath) shape).lineTo(438.777, 265.0);
        ((GeneralPath) shape).curveTo(434.24, 359.0, 359.0, 434.24, 265.0, 438.777);
        ((GeneralPath) shape).lineTo(265.0, 384.0);
        ((GeneralPath) shape).lineTo(247.0, 384.0);
        ((GeneralPath) shape).lineTo(247.0, 438.777);
        ((GeneralPath) shape).curveTo(153.0, 434.24, 77.76, 359.0, 73.223, 265.0);
        ((GeneralPath) shape).lineTo(128.0, 265.0);
        ((GeneralPath) shape).lineTo(128.0, 247.0);
        ((GeneralPath) shape).lineTo(73.223, 247.0);
        ((GeneralPath) shape).curveTo(77.76, 153.0, 153.0, 77.76, 247.0, 73.223);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(247.0, 224.0);
        ((GeneralPath) shape).lineTo(247.0, 247.0);
        ((GeneralPath) shape).lineTo(224.0, 247.0);
        ((GeneralPath) shape).lineTo(224.0, 265.0);
        ((GeneralPath) shape).lineTo(247.0, 265.0);
        ((GeneralPath) shape).lineTo(247.0, 288.0);
        ((GeneralPath) shape).lineTo(265.0, 288.0);
        ((GeneralPath) shape).lineTo(265.0, 265.0);
        ((GeneralPath) shape).lineTo(288.0, 265.0);
        ((GeneralPath) shape).lineTo(288.0, 247.0);
        ((GeneralPath) shape).lineTo(265.0, 247.0);
        ((GeneralPath) shape).lineTo(265.0, 224.0);
        ((GeneralPath) shape).lineTo(247.0, 224.0);
        ((GeneralPath) shape).closePath();

        g.setPaint(color);
        AffineTransform at = new AffineTransform();
        at.scale(scale,scale);
        ((GeneralPath) shape).transform(at);
        at = new AffineTransform();
        at.translate(x,y);
        ((GeneralPath) shape).transform(at);
        g.fill(shape);

    }

    /**
     * Returns the X of the bounding box of the original SVG image.
     * 
     * @return The X of the bounding box of the original SVG image.
     */
    public static int getOrigX() {
        return 0;
    }

    /**
     * Returns the Y of the bounding box of the original SVG image.
     * 
     * @return The Y of the bounding box of the original SVG image.
     */
    public static int getOrigY() {
        return 0;
    }

    /**
     * Returns the width of the bounding box of the original SVG image.
     * 
     * @return The width of the bounding box of the original SVG image.
     */
    public static int getOrigWidth() {
        return 1;
    }

    /**
     * Returns the height of the bounding box of the original SVG image.
     * 
     * @return The height of the bounding box of the original SVG image.
     */
    public static int getOrigHeight() {
        return 1;
    }
}

