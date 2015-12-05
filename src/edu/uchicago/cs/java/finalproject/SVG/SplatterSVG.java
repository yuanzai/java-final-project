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
public class SplatterSVG {

    /**
     * Paints the transcoded SVG image on the specified graphics context. You
     * can install a custom transformation on the graphics context to scale the
     * image.
     * 
     * @param g Graphics context.
     */
    public static void paint(Graphics2D g, int x, int y, int length) {
        Shape shape = null;
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(221.406, 138.375);
        ((GeneralPath) shape).curveTo(177.981, 138.375, 152.04901, 169.002, 215.031, 169.0);
        ((GeneralPath) shape).curveTo(258.457, 168.998, 284.391, 138.373, 221.40701, 138.375);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(358.094, 155.813);
        ((GeneralPath) shape).curveTo(314.947, 155.813, 283.628, 165.705, 275.156, 183.281);
        ((GeneralPath) shape).curveTo(191.606, 180.781, 122.281006, 209.98901, 140.25, 230.001);
        ((GeneralPath) shape).curveTo(142.592, 232.608, 140.55, 235.57101, 135.0, 235.845);
        ((GeneralPath) shape).curveTo(-3.9550018, 242.672, -29.757996, 305.22, 104.03, 324.595);
        ((GeneralPath) shape).curveTo(109.692, 325.417, 110.822, 329.529, 108.125, 332.189);
        ((GeneralPath) shape).curveTo(75.631, 364.232, 146.515, 387.689, 251.781, 387.689);
        ((GeneralPath) shape).curveTo(359.30902, 387.689, 417.511, 369.179, 398.971, 339.189);
        ((GeneralPath) shape).curveTo(394.879, 332.57098, 396.958, 327.879, 405.971, 326.939);
        ((GeneralPath) shape).curveTo(548.218, 312.145, 512.12103, 231.879, 389.626, 224.157);
        ((GeneralPath) shape).curveTo(364.66602, 222.58699, 370.586, 210.73, 379.68802, 209.563);
        ((GeneralPath) shape).curveTo(481.80502, 196.485, 419.65802, 155.813, 358.09503, 155.813);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(451.719, 359.09302);
        ((GeneralPath) shape).curveTo(416.086, 359.74802, 398.362, 383.51602, 453.155, 387.31302);
        ((GeneralPath) shape).curveTo(493.452, 390.105, 517.508, 363.33502, 459.061, 359.281);
        ((GeneralPath) shape).curveTo(456.543, 359.108, 454.09302, 359.051, 451.71802, 359.095);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(107.37399, 399.406);
        ((GeneralPath) shape).curveTo(39.00399, 399.40802, 8.210991, 448.754, 107.37399, 448.75);
        ((GeneralPath) shape).curveTo(175.74399, 448.748, 206.53699, 399.402, 107.37399, 399.406);
        ((GeneralPath) shape).closePath();

        AffineTransform at = new AffineTransform();
        at.scale((double)length/512,(double)length/512);
        ((GeneralPath) shape).transform(at);
        at = new AffineTransform();
        at.translate(x-length/2,y-length/2);
        ((GeneralPath) shape).transform(at);
        g.setPaint(RED);
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

