package com.sixfingers.bp.player;

import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class PortraitControlPowerBookDecorator extends PowerEbookDecorator {

    public PortraitControlPowerBookDecorator(final ViewGroup viewGroup) {
        super(viewGroup);
        inflate(viewGroup.getContext(), R.layout.portrait_control_panel, this);
        frameLayout.addView(this, new FrameLayout.LayoutParams(MATCH_PARENT,
                150,
                Gravity.BOTTOM | Gravity.LEFT));
    }

}
