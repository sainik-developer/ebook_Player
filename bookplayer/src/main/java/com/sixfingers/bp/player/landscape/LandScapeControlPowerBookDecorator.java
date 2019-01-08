package com.sixfingers.bp.player.landscape;

import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.sixfingers.bp.player.PowerEbookDecorator;
import com.sixfingers.bp.player.R;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class LandScapeControlPowerBookDecorator extends PowerEbookDecorator {

    public LandScapeControlPowerBookDecorator(ViewGroup viewGroup) {
        super(viewGroup);
        inflate(viewGroup.getContext(), R.layout.landscape_control_panel, this);
        frameLayout.addView(this, new FrameLayout.LayoutParams(MATCH_PARENT,
                getScreenHeightByPercentage(15.0f),
                Gravity.BOTTOM | Gravity.START));
    }
}
