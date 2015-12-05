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
public class ShotgunSVG {

    /**
     * Paints the transcoded SVG image on the specified graphics context. You
     * can install a custom transformation on the graphics context to scale the
     * image.
     * 
     * @param g Graphics context.
     */
    public static void paint(Graphics2D g, int x, int y, int length, Color color)  {

        // taken from http://game-icons.net/delapouite/originals/sawed-off-shotgun.html

        Shape shape = null;
        int side = length;
        double scale = (double)side/512;
        g.setStroke(new BasicStroke(2));
        g.setPaint(color);

        g.drawRect(x,y,side,side);
        // _0_1
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(472.133, 19.812);
        ((GeneralPath) shape).lineTo(162.52, 197.03);
        ((GeneralPath) shape).lineTo(184.516, 231.163);
        ((GeneralPath) shape).lineTo(483.97, 38.183);
        ((GeneralPath) shape).lineTo(472.133, 19.812998);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(400.236, 113.56);
        ((GeneralPath) shape).lineTo(282.609, 189.36);
        ((GeneralPath) shape).lineTo(298.959, 199.77);
        ((GeneralPath) shape).curveTo(344.93903, 166.89, 381.56903, 137.926, 399.44202, 117.226006);
        ((GeneralPath) shape).lineTo(400.23502, 113.560005);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(265.953, 200.095);
        ((GeneralPath) shape).lineTo(165.828, 264.618);
        ((GeneralPath) shape).lineTo(186.308, 276.74802);
        ((GeneralPath) shape).curveTo(220.331, 254.18301, 253.298, 231.94302, 283.096, 211.00803);
        ((GeneralPath) shape).lineTo(265.953, 200.09503);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(147.12701, 206.372);
        ((GeneralPath) shape).lineTo(140.90001, 210.38399);
        ((GeneralPath) shape).curveTo(134.30602, 230.36398, 139.50002, 246.69398, 148.71, 254.236);
        ((GeneralPath) shape).lineTo(169.38701, 240.913);
        ((GeneralPath) shape).lineTo(147.12701, 206.37299);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(123.417015, 248.41699);
        ((GeneralPath) shape).curveTo(117.905014, 253.94899, 110.48901, 261.615, 101.12901, 272.057);
        ((GeneralPath) shape).curveTo(83.904015, 291.269, 64.77602, 315.602, 57.65901, 332.462);
        ((GeneralPath) shape).curveTo(39.116013, 376.39, 42.31901, 430.452, 39.65901, 480.198);
        ((GeneralPath) shape).curveTo(42.06901, 487.27798, 45.067013, 490.118, 47.85601, 491.335);
        ((GeneralPath) shape).curveTo(50.81601, 492.62698, 54.75201, 492.529, 59.59601, 490.511);
        ((GeneralPath) shape).curveTo(68.989006, 486.60098, 79.29201, 474.916, 81.85801, 465.22897);
        ((GeneralPath) shape).curveTo(81.684006, 462.99698, 80.93801, 453.11197, 80.62101, 439.58597);
        ((GeneralPath) shape).curveTo(80.26501, 424.44595, 80.37501, 406.58398, 83.25101, 393.28897);
        ((GeneralPath) shape).curveTo(88.40101, 369.46896, 96.64601, 344.09897, 113.519005, 324.23895);
        ((GeneralPath) shape).curveTo(123.712006, 312.24594, 140.651, 301.75894, 155.439, 293.39096);
        ((GeneralPath) shape).curveTo(160.11899, 290.74097, 164.10399, 288.66696, 167.85399, 286.74097);
        ((GeneralPath) shape).curveTo(147.97398, 274.45096, 131.198, 269.04095, 123.41699, 248.41797);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(65.667015, 286.21);
        ((GeneralPath) shape).lineTo(28.029015, 295.85);
        ((GeneralPath) shape).lineTo(43.6, 320.01);
        ((GeneralPath) shape).curveTo(49.031998, 308.996, 56.93, 297.423, 65.664, 286.21002);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(191.52701, 295.03);
        ((GeneralPath) shape).lineTo(190.373, 295.562);
        ((GeneralPath) shape).curveTo(190.373, 295.562, 183.608, 298.678, 174.27701, 303.605);
        ((GeneralPath) shape).curveTo(174.02701, 314.595, 168.68701, 323.341, 161.66, 329.105);
        ((GeneralPath) shape).curveTo(156.168, 333.61002, 149.64, 335.948, 143.85701, 335.80002);
        ((GeneralPath) shape).curveTo(139.50201, 335.68802, 135.51102, 334.433, 131.84401, 331.25003);
        ((GeneralPath) shape).curveTo(130.044, 332.87003, 128.48401, 334.43002, 127.23801, 335.89404);
        ((GeneralPath) shape).curveTo(124.79401, 338.77005, 122.548004, 341.85403, 120.46801, 345.09103);
        ((GeneralPath) shape).curveTo(127.24801, 350.68604, 135.378, 353.59103, 143.39601, 353.79504);
        ((GeneralPath) shape).curveTo(154.15901, 354.07104, 164.60901, 349.96805, 173.07602, 343.02203);
        ((GeneralPath) shape).curveTo(185.83002, 332.56003, 194.04901, 314.85202, 191.52602, 295.03204);
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

