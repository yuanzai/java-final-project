package edu.uchicago.cs.java.finalproject.game.model;

import java.util.ArrayList;
import java.util.Queue;

/**
 * Created by ag on 6/17/2015.
 */
public class GameArrayList<E> extends ArrayList<E> {


    private Queue mQueue;


    public GameArrayList(Queue queue) {
        //this should be sufficiently large init size so that we avoid copy-on-upgrade capacity
        super(150);
        mQueue = queue;
    }




    @Override
    public boolean add(E e) {
        return mQueue.add(e);
    }

    @Override
    public boolean remove(Object o) {
        return super.remove(o);
    }

    @Override
    public E remove(int index) {
        return super.remove(index);
    }

    @Override
    public void add(int index, E element) {
        super.add(index, element);
    }

    @Override
    public E set(int index, E element) {
        return super.set(index, element);
    }


}
