package com.sixfingers.bp.player;

import android.app.Activity;
import android.support.constraint.ConstraintLayout;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class PowerEbookDecorator extends ConstraintLayout {

    public FrameLayout frameLayout;

    private DisplayMetrics displayMetrics = new DisplayMetrics();

    public PowerEbookDecorator(final ViewGroup viewGroup) {
        super(viewGroup.getContext());
        if (viewGroup instanceof FrameLayout) {
            frameLayout = (FrameLayout) viewGroup;
            ((Activity) frameLayout.getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        } else if (viewGroup instanceof PowerEbookDecorator) {
            frameLayout = ((PowerEbookDecorator) viewGroup).frameLayout;
        }
    }

    public int getScreenHeightByPercentage(final float percentage) {
        return (int) (displayMetrics.heightPixels * (percentage / 100));
    }

    public int getScreenWidthByPercentage(final float percentage) {
        return (int) (displayMetrics.widthPixels * (percentage / 100));
    }


}
