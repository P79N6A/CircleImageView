package com.example.zhangzhixin.test;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Scroller;

public class MyScrollingLayout extends LinearLayout implements NestedScrollingParent {

    private  Context mContext;
    private Scroller mScroller;

    public MyScrollingLayout(Context context) {
        super(context);
        mContext =context;
    }

    public MyScrollingLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext =context;

    }

    public MyScrollingLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext =context;

    }

    public MyScrollingLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mContext =context;

    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        NestedScrollingParentHelper  helper=new NestedScrollingParentHelper(this);

        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedScrollAccepted(View child, View target, int axes) {
        super.onNestedScrollAccepted(child, target, axes);
    }

    @Override
    public void onStopNestedScroll(View child) {
        super.onStopNestedScroll(child);
    }

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        Boolean hidetop = dy>0&&getScrollY()<this.getChildAt(0).getHeight();
        Boolean showtop = dy<0&&getScrollY()>0&&!ViewCompat.canScrollVertically(target,-1);

        if(hidetop || showtop){
            scrollBy(0,dy);
            consumed[1]=dy;
        }

    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        return super.onNestedFling(target, velocityX, velocityY, consumed);

    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {

        if(getScrollY()>this.getChildAt(0).getHeight()){
            return false;
        }
        mScroller= new Scroller(mContext);
        mScroller.fling(0,getScrollY(),0, (int) velocityY,0,0,0,this.getChildAt(0).getHeight());
        invalidate();
        return true;

    }

    @Override
    public int getNestedScrollAxes() {
        return super.getNestedScrollAxes();
    }
}
