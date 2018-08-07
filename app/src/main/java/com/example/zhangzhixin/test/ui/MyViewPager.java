package com.example.zhangzhixin.test.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

public class MyViewPager extends ViewGroup {
    private Scroller mScroller;
    private float mDownX;
    private int mMinSlop;
    private boolean isChange =true;
    private int leftBorder;
    private int rightBorder;
    private float mLastMove;
    private float mMoveX;
    private int mChildWidth;

    public MyViewPager(Context context) {
        super(context);
        init(context);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public MyViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }

    public MyViewPager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);

    }

    private void init(Context context) {
        mScroller = new Scroller(context);
        ViewConfiguration configuration = ViewConfiguration.get(context);
        mMinSlop = configuration.getScaledTouchSlop();

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        if (isChange) {
            for (int i = 0; i < getChildCount(); i++) {
                View child = getChildAt(i);
                int width = child.getMeasuredWidth();
                mChildWidth=child.getWidth();
                int height = child.getMeasuredHeight();
                child.layout(i * width, 0, (i + 1) * width, height);
            }
        }
        leftBorder = getChildAt(0).getLeft();
        rightBorder = getChildAt(getChildCount() - 1).getRight();

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        for (int i = 0; i < getChildCount(); i++) {
            measureChild(getChildAt(i), widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    public void computeScroll() {
        if(mScroller.computeScrollOffset()){
            scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            invalidate();
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = ev.getX();
                mLastMove = mDownX;
                break;
            case MotionEvent.ACTION_MOVE:
                mMoveX = ev.getRawX();
                float slop = Math.abs(mMoveX - mDownX);
                mLastMove = mMoveX;
                if (slop > mMinSlop) {
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_MOVE:
                mMoveX = event.getRawX();
                int scrollX = (int)(mLastMove-mMoveX);
                if(getScrollX()+scrollX<leftBorder){
                    scrollTo(leftBorder,0);
                    return true;
                }else if (getScrollX()+getWidth()+scrollX>rightBorder){
                    scrollTo(rightBorder-getWidth(),0);
                    return true;
                }
                scrollBy(scrollX,0);
                mLastMove = mMoveX;
                break;
            case MotionEvent.ACTION_UP:
                int targetIndex = (getScrollX() + getWidth() / 2) / getWidth();
                int dx = targetIndex * getWidth() - getScrollX();
                // 第二步，调用startScroll()方法来初始化滚动数据并刷新界面
                mScroller.startScroll(getScrollX(), 0, dx, 0);
                break;
        }
        return super.onTouchEvent(event);
    }
}
