/*
 * Quan Duc Loc CE140037
 * SE1402
 */

package com.group6.noteproject.view;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Class to implements Recycler View's Item On Click Listener
 */
public class RecyclerViewTouchListener implements RecyclerView.OnItemTouchListener {
    private GestureDetector gestureDetector;        // gesture detector
    private ClickListener clickListener;            // click listener

    /**
     * Constructor
     * @param context context
     * @param recyclerView recycler view
     * @param clickListener click listener
     */
    public RecyclerViewTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
        this.clickListener = clickListener;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            /**
             * Notify when a tap occurs
             * @param motionEvent motion event
             * @return
             */
            @Override
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                return true;
            }

            /**
             * Call children's on click when long click is detected on Recycler's surface
             * @param motionEvent motion event
             */
            @Override
            public void onLongPress(MotionEvent motionEvent) {
                View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                if (child != null && clickListener != null) {
                    clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                }
            }
        });
    }

    /**
     * Call children's on click when a touch event is detected on Recycler's surface
     * @param recyclerView recycler view
     * @param motionEvent motion event
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
        if (child != null && clickListener != null && gestureDetector.onTouchEvent(motionEvent)) {
            clickListener.onClick(child, recyclerView.getChildPosition(child));
        }
        return false;
    }

    /**
     * Do nothing on touch event
     * @param rv recycler view
     * @param e motion event
     */
    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    /**
     * Do nothing when a child of Recycler View intercept touch events
     * @param disallowIntercept disallow intercept status
     */
    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    /**
     * Interface for Click Listener
     */
    public interface ClickListener {
        void onClick(View view, int position);      // on short click listener
        void onLongClick(View view, int position);  // on long click listener
    }
}