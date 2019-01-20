/*
 * Copyright 2019 sixfingers.com. All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sixfingers.bp.player.features;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.sixfingers.bp.player.R;

/***
 * Middle play/Pause button when user presses to play or pause depends on state
 */
public class PlayPauseControlPowerBookDecorator extends PowerEbookDecorator {

    public PlayPauseControlPowerBookDecorator(final ViewGroup viewGroup) {
        super(viewGroup);
        inflate(viewGroup.getContext(), R.layout.play_pause_control_panel, this);
        frameLayout.addView(this, new FrameLayout.LayoutParams(getScreenSquareByPercentage(5),
                getScreenSquareByPercentage(5), Gravity.CENTER));
    }

    @Override
    public void revealView() {
        this.setAlpha(0f);
        this.setVisibility(View.VISIBLE);
        this.animate()
                .alpha(1f)
                .setDuration(1000)
                .setListener(null);
    }

    @Override
    public void hideView() {
        this.animate()
                .alpha(0f)
                .setDuration(1000)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        PlayPauseControlPowerBookDecorator.this.setVisibility(View.GONE);
                    }
                });
    }
}
