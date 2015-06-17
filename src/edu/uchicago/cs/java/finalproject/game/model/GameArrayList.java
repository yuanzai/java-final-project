package edu.uchicago.cs.java.finalproject.game.model;

import java.util.ArrayList;
import java.util.Queue;
import edu.uchicago.cs.java.finalproject.game.model.Movable;

/**
 * Created by ag on 6/17/2015.
 */
public class GameArrayList extends ArrayList {


    //each GameArrayList knows about this queue of operaitons
    private Queue<CollisionOp> mQueue;


    public GameArrayList(Queue queue) {
        //this should be sufficiently large init size so that we avoid copy-on-upgrade capacity
        super(150);
        mQueue = queue;
    }

    //overloaded to take another capacity
    public GameArrayList(Queue queue, int cap) {
        super(cap);
        mQueue = queue;
    }



    public boolean add(Movable mov) {
        return mQueue.add(new CollisionOp(mov, CollisionOp.Operation.ADD ));
    }


    public boolean remove(Movable mov) {
        return mQueue.add(new CollisionOp(mov, CollisionOp.Operation.REMOVE));
    }




}
