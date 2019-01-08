package com.sixfingers.bp.player;

import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/***
 *
 */
public class BackHomeControlPowerBookDecorator extends PowerEbookDecorator {

    public BackHomeControlPowerBookDecorator(final ViewGroup viewGroup) {
        super(viewGroup);
        inflate(viewGroup.getContext(), R.layout.back_control_top_left, this);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(getScreenWidthByPercentage(7),
                getScreenHeightByPercentage(7), Gravity.TOP | Gravity.START);
        layoutParams.setMargins(getScreenWidthByPercentage(0.5f),
                getScreenHeightByPercentage(0.5f), 0, 0);
        frameLayout.addView(this, layoutParams);
    }
}
