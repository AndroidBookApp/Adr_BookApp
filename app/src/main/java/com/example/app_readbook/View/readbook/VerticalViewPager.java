package com.example.app_readbook.View.readbook;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class VerticalViewPager extends ViewPager {
    public VerticalViewPager(@NonNull Context context) {
        super(context);
        init();
    }


    public VerticalViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setPageTransformer(true , new VerticalPage());
        setOverScrollMode(OVER_SCROLL_NEVER);
    }
    private MotionEvent getIntercambioXY(MotionEvent ev)
    {
        float width = getWidth();
        float height = getHeight();
        float newX = (ev.getY() / height) * width;
        float newY = (ev.getX() / width) * height;
        ev.setLocation(newX , newY);
        return ev;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean inter = super.onInterceptTouchEvent(getIntercambioXY(ev));
        getIntercambioXY(ev);
        return inter;

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(getIntercambioXY(ev));
    }
    private class VerticalPage implements ViewPager.PageTransformer{
        /**
         * Apply a property transformation to the given page.
         *
         * @param page     Apply the transformation to this page
         * @param position Position of page relative to the current front-and-center
         *                 position of the pager. 0 is front and center. 1 is one full
         */
        @Override
        public void transformPage(@NonNull View page, float position) {
            if(position < -1)
            {
                page.setAlpha(0);
            }else if(position <=1)
            {
                page.setAlpha(1);
                page.setTranslationX(page.getWidth() * -position);
                float yPosition = position * page.getHeight();
                page.setTranslationY(yPosition);
            }else{
                page.setAlpha(0);
            }
        }
    }
}
