package com.mariostudio.audiorecoder;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;

/**
 * Created by MarioStudio on 2016/5/7.
 */

public class BasePopupWindow extends PopupWindow {

    private Context mContext;
    private float mShowAlpha = 0.88f;
    private Drawable mBackgroundDrawable;

    @Override
    public int getAnimationStyle() {
        return super.getAnimationStyle();
    }

    public BasePopupWindow(Context context) {
        this.mContext = context;
        initBasePopupWindow();
    }

    @Override
    public void setOutsideTouchable(boolean touchable) {
        super.setOutsideTouchable(touchable);
        if(touchable) {
            if(mBackgroundDrawable == null) {
                mBackgroundDrawable = new ColorDrawable(0x00000000);
            }
            super.setBackgroundDrawable(mBackgroundDrawable);
        } else {
            super.setBackgroundDrawable(null);
        }
    }

    @Override
    public void setBackgroundDrawable(Drawable background) {
        mBackgroundDrawable = background;
        setOutsideTouchable(isOutsideTouchable());
    }

    /**
     * 初始化BasePopupWindow的一些信息
     * */
    private void initBasePopupWindow() {
        setAnimationStyle(android.R.style.Animation_Dialog);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setOutsideTouchable(false);  //默认设置outside点击无响应
        setFocusable(true);
    }

    @Override
    public void setContentView(View contentView) {
        if(contentView != null) {
            contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            super.setContentView(contentView);
            addKeyListener(contentView);
        }
    }

    public Context getContext() {
        return mContext;
    }

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        super.showAtLocation(parent, gravity, x, y);
        ValueAnimator animator = showAnimator();
        if(animator != null) {
            animator.start();
        }
    }

    @Override
    public void showAsDropDown(View anchor) {
        super.showAsDropDown(anchor);
        ValueAnimator animator = showAnimator();
        if(animator != null) {
            animator.start();
        }
    }

    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff) {
        super.showAsDropDown(anchor, xoff, yoff);
        ValueAnimator animator = showAnimator();
        if(animator != null) {
            animator.start();
        }
    }

    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff, int gravity) {
        super.showAsDropDown(anchor, xoff, yoff, gravity);
        ValueAnimator animator = showAnimator();
        if(animator != null) {
            animator.start();
        }
    }

    @Override
    public void dismiss() {
        super.dismiss();
        ValueAnimator animator = dismissAnimator();
        if(animator != null) {
            animator.start();
        }
    }

    public void setShowAlpha(float alpha) {
        this.mShowAlpha = alpha;
    }

    /**
     * 窗口显示，窗口背景透明度渐变动画
     * */
    private ValueAnimator showAnimator() {
        if(mShowAlpha != 1.0f) {
            ValueAnimator animator = ValueAnimator.ofFloat(1.0f, mShowAlpha);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float alpha = (float) animation.getAnimatedValue();
                    setWindowBackgroundAlpha(alpha);
                }
            });
            animator.setDuration(360);
            return animator;
        } else {
            return null;
        }
    }

    /**
     * 窗口隐藏，窗口背景透明度渐变动画
     * */
    private ValueAnimator dismissAnimator() {
        if(mShowAlpha != 1.0f) {
            ValueAnimator animator = ValueAnimator.ofFloat(mShowAlpha, 1.0f);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float alpha = (float) animation.getAnimatedValue();
                    setWindowBackgroundAlpha(alpha);
                }
            });
            animator.setDuration(320);
            return animator;
        } else {
            return null;
        }
    }

    /**
     * 为窗体添加outside点击事件
     * */
    private void addKeyListener(View contentView) {
        if(contentView != null) {
            contentView.setFocusable(true);
            contentView.setFocusableInTouchMode(true);
            contentView.setOnKeyListener(new View.OnKeyListener() {

                @Override
                public boolean onKey(View view, int keyCode, KeyEvent event) {
                    switch (keyCode) {
                        case KeyEvent.KEYCODE_BACK:
                            dismiss();
                            return true;
                        default:
                            break;
                    }
                    return false;
                }
            });
        }
    }

    /**
     * 控制窗口背景的不透明度
     * */
    private void setWindowBackgroundAlpha(float alpha) {
        Window window = ((Activity)getContext()).getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.alpha = alpha;
        window.setAttributes(layoutParams);
    }
}
