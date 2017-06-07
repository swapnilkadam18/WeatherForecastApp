package com.tcs.android.weatherforecastapp.view.constants;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.tcs.android.weatherforecastapp.view.interfaces.ClickListener;

public class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

    private ClickListener clicklistener;
    private GestureDetector gestureDetector;

    public RecyclerTouchListener(Context context, final RecyclerView recycleView, final ClickListener clicklistener) {

        this.clicklistener = clicklistener;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent event) {
                return Boolean.TRUE;
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent event) {
        View child = recyclerView.findChildViewUnder(event.getX(), event.getY());
        if (child != null && clicklistener != null && gestureDetector.onTouchEvent(event)) {
            clicklistener.onClick(child, recyclerView.getChildAdapterPosition(child));
        }

        return Boolean.FALSE;
    }

    @Override
    public void onTouchEvent(RecyclerView recyclerView, MotionEvent event) {
        //nothing to do
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        //nothing to do
    }
}
