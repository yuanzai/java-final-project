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
public class GunSVG {

    /**
     * Paints the transcoded SVG image on the specified graphics context. You
     * can install a custom transformation on the graphics context to scale the
     * image.
     * 
     * @param g Graphics context.
     */
    public static void paint(Graphics2D g, int x, int y, int length, Color color) {

        // SVG from http://game-icons.net/delapouite/originals/mp5.html

        Shape shape = null;
        int side = length;
        double scale = (double)side/512;
        g.setStroke(new BasicStroke(2));
        g.setPaint(color);

        g.drawRect(x,y,side,side);

        // _0_1
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(421.938, 24.406);
        ((GeneralPath) shape).curveTo(416.288, 24.742, 411.31097, 26.131, 410.063, 33.313);
        ((GeneralPath) shape).lineTo(425.65598, 51.875);
        ((GeneralPath) shape).lineTo(188.62598, 245.155);
        ((GeneralPath) shape).curveTo(188.61598, 245.155, 188.60397, 245.159, 188.59398, 245.155);
        ((GeneralPath) shape).curveTo(177.28998, 244.877, 171.80399, 252.833, 170.03098, 256.655);
        ((GeneralPath) shape).curveTo(160.72098, 276.735, 155.00397, 298.29498, 143.12598, 318.625);
        ((GeneralPath) shape).curveTo(106.99597, 351.785, 61.16198, 382.627, 32.375977, 416.938);
        ((GeneralPath) shape).curveTo(49.655975, 440.762, 73.21597, 461.45798, 93.62598, 483.718);
        ((GeneralPath) shape).curveTo(101.88598, 483.215, 106.489975, 473.985, 112.531975, 468.15598);
        ((GeneralPath) shape).curveTo(139.26997, 417.666, 158.72397, 371.73798, 190.06396, 324.93597);
        ((GeneralPath) shape).curveTo(201.91997, 317.31897, 216.28596, 309.916, 222.84596, 301.84396);
        ((GeneralPath) shape).curveTo(223.57837, 302.1358, 224.34425, 302.33533, 225.12596, 302.43796);
        ((GeneralPath) shape).curveTo(230.69096, 303.20297, 235.06596, 304.69797, 238.25197, 306.90594);
        ((GeneralPath) shape).curveTo(241.03497, 308.83594, 243.16397, 311.37894, 244.84596, 315.62595);
        ((GeneralPath) shape).curveTo(239.94597, 343.62994, 248.51596, 358.47794, 252.09596, 372.21893);
        ((GeneralPath) shape).curveTo(253.38698, 377.22266, 258.4408, 380.2767, 263.47095, 379.09293);
        ((GeneralPath) shape).lineTo(307.97095, 368.68692);
        ((GeneralPath) shape).curveTo(310.91592, 367.99548, 313.35556, 365.94284, 314.54047, 363.15952);
        ((GeneralPath) shape).curveTo(315.72537, 360.3762, 315.5139, 357.19492, 313.97095, 354.59293);
        ((GeneralPath) shape).curveTo(306.31094, 341.73294, 302.30695, 323.59293, 298.87595, 303.96793);
        ((GeneralPath) shape).curveTo(316.38394, 298.55792, 327.77795, 285.4879, 338.00095, 275.05994);
        ((GeneralPath) shape).curveTo(339.0268, 274.0013, 339.3872, 272.46423, 338.93893, 271.05994);
        ((GeneralPath) shape).lineTo(330.93893, 245.90393);
        ((GeneralPath) shape).lineTo(347.12595, 232.43393);
        ((GeneralPath) shape).curveTo(347.89972, 231.78357, 348.56476, 231.0139, 349.09595, 230.15393);
        ((GeneralPath) shape).curveTo(382.27295, 254.06393, 416.93594, 275.41794, 456.81296, 287.84192);
        ((GeneralPath) shape).lineTo(466.09497, 263.34192);
        ((GeneralPath) shape).curveTo(428.20297, 250.59192, 382.31497, 224.40991, 354.25098, 197.62192);
        ((GeneralPath) shape).lineTo(359.93896, 191.68591);
        ((GeneralPath) shape).lineTo(458.12598, 88.84191);
        ((GeneralPath) shape).lineTo(463.96997, 82.71691);
        ((GeneralPath) shape).lineTo(458.56198, 76.21691);
        ((GeneralPath) shape).lineTo(457.93698, 75.43491);
        ((GeneralPath) shape).lineTo(482.781, 50.0);
        ((GeneralPath) shape).lineTo(474.251, 41.47);
        ((GeneralPath) shape).lineTo(455.721, 57.438004);
        ((GeneralPath) shape).lineTo(421.93802, 24.406002);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(325.563, 162.47);
        ((GeneralPath) shape).lineTo(331.593, 171.094);
        ((GeneralPath) shape).lineTo(304.593, 194.094);
        ((GeneralPath) shape).curveTo(304.58298, 194.10397, 304.57297, 194.11397, 304.563, 194.124);
        ((GeneralPath) shape).lineTo(228.28099, 258.906);
        ((GeneralPath) shape).lineTo(220.50099, 250.31201);
        ((GeneralPath) shape).lineTo(325.564, 162.46901);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(324.313, 251.406);
        ((GeneralPath) shape).lineTo(330.593, 271.186);
        ((GeneralPath) shape).curveTo(320.92798, 281.13, 311.328, 291.403, 297.5, 296.0);
        ((GeneralPath) shape).curveTo(296.424, 289.542, 295.357, 282.98, 294.22, 276.437);
        ((GeneralPath) shape).lineTo(324.312, 251.40701);
        ((GeneralPath) shape).closePath();

        g.setPaint(color);
        AffineTransform at = new AffineTransform();
        at.scale(scale,scale);
        ((GeneralPath) shape).transform(at);
        at = new AffineTransform();
        at.translate(x,y);
        ((GeneralPath) shape).transform(at);
        g.fill(shape);

       // g.setTransform(transformations.poll()); // _0

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

