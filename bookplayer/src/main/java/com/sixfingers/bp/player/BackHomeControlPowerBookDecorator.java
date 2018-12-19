package com.sixfingers.bp.player;

import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class BackHomeControlPowerBookDecorator extends PowerEbookDecorator {


    public BackHomeControlPowerBookDecorator(final ViewGroup viewGroup) {
        super(viewGroup);
        inflate(viewGroup.getContext(), R.layout.back_control_top_left, this);
        frameLayout.addView(this, new FrameLayout.LayoutParams(100,
                100, Gravity.TOP | Gravity.START));
    }
}
