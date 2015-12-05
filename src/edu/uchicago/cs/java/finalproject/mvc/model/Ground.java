package edu.uchicago.cs.java.finalproject.mvc.model;

import edu.uchicago.cs.java.finalproject.mvc.controller.Game;

/**
 * Created by Junyuan on 11/26/2015.
 */
public class Ground {
    public int x1;
    public int x2;
    public int y;
    public Ground(int y, int x1,int x2) {
        this.y = (int) Game.DIM.getHeight() - 50 - y;
        this.x1 = x1;
        this.x2 = x2;
    }
}