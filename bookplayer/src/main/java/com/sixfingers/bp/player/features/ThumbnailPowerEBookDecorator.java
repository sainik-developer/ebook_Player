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
 * This will show the all the thumbnail of
 * image which is in a book, in the middle
 */
public class ThumbnailPowerEBookDecorator extends PowerEbookDecorator {

    public ThumbnailPowerEBookDecorator(final ViewGroup viewGroup) {
        super(viewGroup);
        inflate(viewGroup.getContext(), R.layout.thumnail_control_panel, this);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(getScreenSquareByPercentage(5),
                getScreenSquareByPercentage(5), Gravity.TOP | Gravity.END);
        layoutParams.setMargins(0, getScreenHeightByPercentage(0.7f), getScreenWidthByPercentage(0.5f), 0);
        frameLayout.addView(this, layoutParams);
    }
}
