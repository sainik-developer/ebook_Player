package com.sixfingers.bp.player;


import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.LinkedList;
import java.util.List;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

/***
 *
 */
public class TestImagePowerEbook extends FrameLayout {

    public ImageView bgImgView;
    private int controlVisibility = VISIBLE;
    private List<View> views;

    public TestImagePowerEbook(final Context context, int imageResId) {
        super(context);
        setLayoutParams(new FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT));
        setBackgroundColor(Color.argb(1, 44, 4, 4));
        createBackground(imageResId);
        bgImgView.setScaleType(ImageView.ScaleType.FIT_XY);
        addView(bgImgView, 0, new FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT, Gravity.CENTER));
        setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN |
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_IMMERSIVE |
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // If no child view consumed the event then execute this code, Not even Bg image
        if (event.getAction() == MotionEvent.ACTION_UP)
            toggleVisibilityViews(getAllChildrenButFirst());
        return true;
    }


    private void createBackground(int imageResId) {
        bgImgView = new ImageView(getContext());
        bgImgView.setLayoutParams(new ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT));
        bgImgView.setImageDrawable(getResources().getDrawable(imageResId));
    }


    private void toggleVisibilityViews(List<View> views) {
        for (View view : views) {
            view.setVisibility(controlVisibility);
        }
        determineAndSetNextControlVisibility();
    }

    private void determineAndSetNextControlVisibility() {
        controlVisibility = controlVisibility == VISIBLE ? GONE : VISIBLE;
    }


    private List<View> getAllChildrenButFirst() {
        int count = getChildCount();
        List<View> viewList = new LinkedList<>();
        for (int i = 1; i < count; i++) {
            viewList.add(getChildAt(i));
        }
        return viewList;
    }
}
