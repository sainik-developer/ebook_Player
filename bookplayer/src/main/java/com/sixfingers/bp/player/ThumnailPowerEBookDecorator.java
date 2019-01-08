package com.sixfingers.bp.player;

import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/***
 *
 */
public class ThumnailPowerEBookDecorator extends PowerEbookDecorator {

    public ThumnailPowerEBookDecorator(final ViewGroup viewGroup) {
        super(viewGroup);
        inflate(viewGroup.getContext(), R.layout.thumnail_control_panel, this);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(getScreenWidthByPercentage(7),
                getScreenHeightByPercentage(7), Gravity.TOP | Gravity.END);
        layoutParams.setMargins(0, getScreenHeightByPercentage(0.7f), getScreenWidthByPercentage(0.5f), 0);
        frameLayout.addView(this, layoutParams);
    }
}
