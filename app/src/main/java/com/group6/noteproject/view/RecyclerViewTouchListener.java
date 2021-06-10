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
 * Class to implements Recycler View Touch Listener
 */
public class RecyclerViewTouchListener implements RecyclerView.OnItemTouchListener {
    private GestureDetector gestureDetector;        // gesture detector
    private ClickListener clickListener;            // click listener

    /**
     * Constructor
     * @param context
     * @param recyclerView
     * @param clickListener
     */
    public RecyclerViewTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
        this.clickListener = clickListener;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            /**
             * Notify when a tap occurs
             * @param motionEvent
             * @return
             */
            @Override
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                return true;
            }

            /**
             * Call children's on click when long click is detected on Recycler's surface
             * @param motionEvent
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
     * @param recyclerView
     * @param motionEvent
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
     * @param rv
     * @param e
     */
    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    /**
     * Do nothing when a child of Recycler View intercept touch events
     * @param disallowIntercept
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