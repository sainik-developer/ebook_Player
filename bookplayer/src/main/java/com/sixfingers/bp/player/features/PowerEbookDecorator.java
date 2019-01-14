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

import android.app.Activity;
import android.support.constraint.ConstraintLayout;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/***
 *
 */
public abstract class PowerEbookDecorator extends ConstraintLayout {

    public FrameLayout frameLayout;

    private DisplayMetrics displayMetrics = new DisplayMetrics();

    public PowerEbookDecorator(final ViewGroup viewGroup) {
        super(viewGroup.getContext());
        if (viewGroup instanceof FrameLayout) {
            frameLayout = (FrameLayout) viewGroup;
            ((Activity) frameLayout.getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        } else if (viewGroup instanceof PowerEbookDecorator) {
            frameLayout = ((PowerEbookDecorator) viewGroup).frameLayout;
            displayMetrics = ((PowerEbookDecorator) viewGroup).displayMetrics;
        }
    }

    public int getScreenHeightByPercentage(final float percentage) {
        return (int) (displayMetrics.heightPixels * (percentage / 100));
    }

    public int getScreenWidthByPercentage(final float percentage) {
        return (int) (displayMetrics.widthPixels * (percentage / 100));
    }

    public int getScreenSquareByPercentage(final float percentage) {
        return (int) ((displayMetrics.heightPixels > displayMetrics.widthPixels ?
                displayMetrics.heightPixels : displayMetrics.widthPixels) * (percentage / 100));
    }

    public abstract void revealView();

    public abstract void hideView();
}
