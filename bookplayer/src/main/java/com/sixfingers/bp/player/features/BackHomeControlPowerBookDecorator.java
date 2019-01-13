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

import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.sixfingers.bp.player.R;

/***
 *
 */
public class BackHomeControlPowerBookDecorator extends PowerEbookDecorator {

    public BackHomeControlPowerBookDecorator(final ViewGroup viewGroup) {
        super(viewGroup);
        inflate(viewGroup.getContext(), R.layout.back_control_top_left, this);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(getScreenSquareByPercentage(5),
                getScreenSquareByPercentage(5), Gravity.TOP | Gravity.START);
        layoutParams.setMargins(getScreenWidthByPercentage(1.0f),
                getScreenHeightByPercentage(1.0f), 0, 0);
        frameLayout.addView(this, layoutParams);
    }
}
