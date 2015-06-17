package edu.uchicago.cs.java.finalproject.game.model;

import java.util.*;


/**
 * Created by ag on 6/17/2015.
 */
public class GameOpsList extends LinkedList {

    public void enqueue(Movable mov, CollisionOp.Operation operation) {
        addLast(new CollisionOp(mov, operation));
    }


}
