package com.sixfingers.bp.player;

import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class PlayPauseControlPowerBookDecorator extends PowerEbookDecorator {

    public PlayPauseControlPowerBookDecorator(final ViewGroup viewGroup) {
        super(viewGroup);
        inflate(viewGroup.getContext(), R.layout.play_pause_control_panel, this);
        frameLayout.addView(this, new FrameLayout.LayoutParams(100,
                100, Gravity.TOP | Gravity.END));

    }
}
