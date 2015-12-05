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
public class HeartSVG {

    /**
     * Paints the transcoded SVG image on the specified graphics context. You
     * can install a custom transformation on the graphics context to scale the
     * image.
     * 
     * @param g Graphics context.
     */
    public static void paint(Graphics2D g, int x, int y) {

        // Source : https://commons.wikimedia.org/wiki/File:Love_Heart_SVG.svg
        // Used Flamingo SVG transcoder to obtain .java from .svg
        // https://flamingo.dev.java.net

        Shape shape = null;
        
        float origAlpha = 1.0f;
        Composite origComposite = ((Graphics2D)g).getComposite();
        if (origComposite instanceof AlphaComposite) {
            AlphaComposite origAlphaComposite = (AlphaComposite)origComposite;
            if (origAlphaComposite.getRule() == AlphaComposite.SRC_OVER) {
                origAlpha = origAlphaComposite.getAlpha();
            }
        }


        // 

        // _0

        // _0_0

        // _0_0_0
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(297.29745, 550.8682);
        ((GeneralPath) shape).curveTo(283.52243, 535.4319, 249.1268, 505.33856, 220.86276, 483.9941);
        ((GeneralPath) shape).curveTo(137.11867, 420.7523, 125.72108, 411.5999, 91.71924, 380.2909);
        ((GeneralPath) shape).curveTo(29.03471, 322.5707, 2.413622, 264.58087, 2.5048478, 185.95123);
        ((GeneralPath) shape).curveTo(2.5493593, 147.56738, 5.165615, 132.7793, 15.914734, 110.15398);
        ((GeneralPath) shape).curveTo(34.151432, 71.768265, 61.014996, 43.244667, 95.360054, 25.799458);
        ((GeneralPath) shape).curveTo(119.68545, 13.443675, 131.6827, 7.9542046, 172.30447, 7.729624);
        ((GeneralPath) shape).curveTo(214.79778, 7.4947896, 223.7431, 12.449347, 248.7392, 26.18146);
        ((GeneralPath) shape).curveTo(279.1637, 42.89578, 310.4791, 78.617165, 316.95242, 103.99205);
        ((GeneralPath) shape).lineTo(320.95053, 119.66445);
        ((GeneralPath) shape).lineTo(330.81015, 98.07994);
        ((GeneralPath) shape).curveTo(386.5263, -23.892986, 564.4085, -22.06811, 626.31244, 101.11153);
        ((GeneralPath) shape).curveTo(645.95013, 140.18758, 648.1061, 223.6247, 630.69257, 270.6244);
        ((GeneralPath) shape).curveTo(607.9773, 331.93378, 565.31256, 378.67493, 466.68622, 450.301);
        ((GeneralPath) shape).curveTo(402.0054, 497.27463, 328.80148, 568.34686, 323.70554, 578.329);
        ((GeneralPath) shape).curveTo(317.79007, 589.91656, 323.4234, 580.1449, 297.29745, 550.8682);
        ((GeneralPath) shape).closePath();

        AffineTransform at = new AffineTransform();
        at.translate(x,y);
        at.scale(0.05,0.05);
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
        return 3;
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
        return 643;
    }

    /**
     * Returns the height of the bounding box of the original SVG image.
     * 
     * @return The height of the bounding box of the original SVG image.
     */
    public static int getOrigHeight() {
        return 585;
    }
}

