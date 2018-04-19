package com.muzi.cardview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by muzi on 2018/4/19.
 * 727784430@qq.com
 */

public class CustomCardView extends ViewGroup {

    private int width1, width2;
    private int height1, height2;
    private int showHeight = 0;
    private boolean isShow = false;

    public CustomCardView(@NonNull Context context) {
        this(context, null);
    }

    public CustomCardView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.item_view1, this, true);
        LayoutInflater.from(getContext()).inflate(R.layout.item_view2, this, true);
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                toggle();
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureView(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(width1, height1 + showHeight);
    }

    private void measureView(int widthMeasureSpec, int heightMeasureSpec) {
        View view1 = getChildAt(0);
        measureChild(view1, widthMeasureSpec, heightMeasureSpec);
        width1 = view1.getMeasuredWidth();
        height1 = view1.getMeasuredHeight();

        View view2 = getChildAt(1);
        measureChild(view2, widthMeasureSpec, heightMeasureSpec);
        width2 = view2.getMeasuredWidth();
        height2 = view2.getMeasuredHeight();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        View view2 = getChildAt(1);
        view2.layout(0, height1, width1, height1 + showHeight);

        View view1 = getChildAt(0);
        view1.layout(0, 0, width1, height1);
    }

    private void openAnimator() {
        ValueAnimator animator = ValueAnimator.ofInt(0, height2);
        animator.setDuration(500);
        animator.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                showHeight = (int) animation.getAnimatedValue();
                requestLayout();
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                isShow = true;
            }
        });
    }

    private void closeAnimator() {
        ValueAnimator animator = ValueAnimator.ofInt(height2, 0);
        animator.setDuration(500);
        animator.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                showHeight = (int) animation.getAnimatedValue();
                requestLayout();
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                isShow = false;
            }
        });
    }

    public void toggle() {
        if (isShow) {
            closeAnimator();
        } else {
            openAnimator();
        }
    }
}
