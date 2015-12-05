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
public class PersonSVG {

    /**
     * Paints the transcoded SVG image on the specified graphics context. You
     * can install a custom transformation on the graphics context to scale the
     * image.
     * 
     * @param g Graphics context.
     */
    public static void paint(Graphics2D g) {

        // SOURCE - https://upload.wikimedia.org/wikipedia/commons/d/d8/Person_icon_BLACK-01.svg
        Shape shape = null;


        java.util.LinkedList<AffineTransform> transformations = new java.util.LinkedList<AffineTransform>();


        // 

        // _0

        // _0_0

        // _0_0_0

        // _0_0_0_0
        transformations.offer(g.getTransform());
        g.transform(new AffineTransform(1.25f, 0, 0, -1.25f, 359.06467f, 506.51276f));

        // _0_0_0_0_0

        // _0_0_0_0_0_0
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(-237.575, 383.262);
        ((GeneralPath) shape).curveTo(-233.413, 383.262, -230.037, 386.638, -230.037, 390.801);
        ((GeneralPath) shape).curveTo(-230.037, 394.96698, -233.41301, 398.34198, -237.575, 398.34198);
        ((GeneralPath) shape).curveTo(-241.741, 398.34198, -245.114, 394.96597, -245.114, 390.801);
        ((GeneralPath) shape).curveTo(-245.113, 386.638, -241.74, 383.262, -237.575, 383.262);

        g.setPaint(GRAY);
        g.fill(shape);

        g.setTransform(transformations.poll()); // _0_0_0_0_0
        transformations.offer(g.getTransform());
        g.transform(new AffineTransform(1.25f, 0, 0, -1.25f, 367.39905f, 508.44513f));

        // _0_0_0_0_1

        // _0_0_0_0_1_0
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(-235.914, 382.874);
        ((GeneralPath) shape).curveTo(-230.581, 382.874, -226.212, 378.547, -226.212, 373.21698);
        ((GeneralPath) shape).lineTo(-226.212, 349.83298);
        ((GeneralPath) shape).curveTo(-226.212, 348.01196, -227.645, 346.53497, -229.46901, 346.53497);
        ((GeneralPath) shape).curveTo(-231.29001, 346.53497, -232.77101, 348.01297, -232.77101, 349.83298);
        ((GeneralPath) shape).lineTo(-232.77101, 370.917);
        ((GeneralPath) shape).lineTo(-234.473, 370.917);
        ((GeneralPath) shape).lineTo(-234.473, 312.254);
        ((GeneralPath) shape).curveTo(-234.473, 309.80798, -236.46901, 307.829, -238.912, 307.829);
        ((GeneralPath) shape).curveTo(-241.358, 307.829, -243.337, 309.808, -243.337, 312.254);
        ((GeneralPath) shape).lineTo(-243.337, 346.317);
        ((GeneralPath) shape).lineTo(-245.15201, 346.317);
        ((GeneralPath) shape).lineTo(-245.15201, 312.254);
        ((GeneralPath) shape).curveTo(-245.15201, 309.80798, -247.13101, 307.829, -249.57301, 307.829);
        ((GeneralPath) shape).curveTo(-252.016, 307.829, -253.99802, 309.808, -253.99802, 312.254);
        ((GeneralPath) shape).curveTo(-253.99802, 315.868, -254.03702, 370.917, -254.03702, 370.917);
        ((GeneralPath) shape).lineTo(-255.71701, 370.917);
        ((GeneralPath) shape).lineTo(-255.71701, 349.833);
        ((GeneralPath) shape).curveTo(-255.71701, 348.012, -257.195, 346.535, -259.019, 346.535);
        ((GeneralPath) shape).curveTo(-260.842, 346.535, -262.276, 348.013, -262.276, 349.833);
        ((GeneralPath) shape).lineTo(-262.276, 373.217);
        ((GeneralPath) shape).curveTo(-262.276, 378.547, -257.908, 382.87402, -252.571, 382.87402);
        ((GeneralPath) shape).lineTo(-235.914, 382.87402);
        ((GeneralPath) shape).closePath();

        g.fill(shape);

        g.setTransform(transformations.poll()); // _0_0_0_0_1

    }

    /**
     * Returns the X of the bounding box of the original SVG image.
     * 
     * @return The X of the bounding box of the original SVG image.
     */
    public static int getOrigX() {
        return 40;
    }

    /**
     * Returns the Y of the bounding box of the original SVG image.
     * 
     * @return The Y of the bounding box of the original SVG image.
     */
    public static int getOrigY() {
        return 9;
    }

    /**
     * Returns the width of the bounding box of the original SVG image.
     * 
     * @return The width of the bounding box of the original SVG image.
     */
    public static int getOrigWidth() {
        return 46;
    }

    /**
     * Returns the height of the bounding box of the original SVG image.
     * 
     * @return The height of the bounding box of the original SVG image.
     */
    public static int getOrigHeight() {
        return 116;
    }
}

