package com.sixfingers.bp.player;

import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/***
 *
 */
public class PlayPauseControlPowerBookDecorator extends PowerEbookDecorator {

    public PlayPauseControlPowerBookDecorator(final ViewGroup viewGroup) {
        super(viewGroup);
        inflate(viewGroup.getContext(), R.layout.play_pause_control_panel, this);
        frameLayout.addView(this, new FrameLayout.LayoutParams(getScreenWidthByPercentage(7),
                getScreenHeightByPercentage(7), Gravity.CENTER));
    }
}
