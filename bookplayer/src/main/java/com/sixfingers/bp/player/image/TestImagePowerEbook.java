package com.sixfingers.bp.player.image;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.sixfingers.bp.player.AbstractPowerEBook;
import com.sixfingers.bp.player.features.PowerEbookDecorator;

import java.util.LinkedList;
import java.util.List;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

/***
 * This is the test Power Ebook for testing, Just to test verious position
 * of control and their appearance and animation.
 */
public class TestImagePowerEbook extends AbstractPowerEBook {

    public ImageView bgImgView;
    private List<PowerEbookDecorator> allChildrenViews;

    public TestImagePowerEbook(final Context context, int imageResId) {
        super(context);
        setLayoutParams(new FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT));
        setBackgroundColor(Color.argb(1, 44, 4, 4));
        createBackground(imageResId);
        addView(bgImgView, 0, new FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT, Gravity.CENTER));
        setSystemUiVisibility(SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // If no child view consumed the event then execute this code, Not even Bg image
        if (event.getAction() == MotionEvent.ACTION_UP) {
            allChildrenViews = allChildrenViews == null ? getAllChildrenButFirst() : allChildrenViews;
            toggleVisibilityViews(getAllChildrenButFirst());
        }
        return true;
    }

    private void createBackground(int imageResId) {
        bgImgView = new ImageView(getContext());
        bgImgView.setLayoutParams(new ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT));
        bgImgView.setImageDrawable(getResources().getDrawable(imageResId));
        bgImgView.setScaleType(ImageView.ScaleType.FIT_XY);
    }

    private void toggleVisibilityViews(List<PowerEbookDecorator> views) {
        for (PowerEbookDecorator view : views) {
            switch (view.getVisibility()) {
                case VISIBLE:
                    view.hideView();
                    break;
                case GONE:
                case INVISIBLE:
                    view.revealView();
                    break;
                default:

            }
        }
    }

    private List<PowerEbookDecorator> getAllChildrenButFirst() {
        int count = getChildCount();
        List<PowerEbookDecorator> viewList = new LinkedList<>();
        for (int i = 1; i < count; i++) {
            if (getChildAt(i) instanceof PowerEbookDecorator) {
                viewList.add((PowerEbookDecorator) getChildAt(i));
            }
        }
        return viewList;
    }
}
