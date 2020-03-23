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

package com.sixfingers.bp.player;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import com.sixfingers.bp.player.features.PowerEbookDecorator;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractPowerEBook extends FrameLayout {

    private List<PowerEbookDecorator> allChildrenViews;

    protected AbstractPowerEBook(final Context context) {
        super(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // If no child view consumed the event then execute this code, Not even Bg image
        if (event.getAction() == MotionEvent.ACTION_UP) {
            allChildrenViews = allChildrenViews == null ? getAllChildrenButFirst() : allChildrenViews;
            toggleVisibilityViews(getAllChildrenButFirst());
        }
        return true;
    }

    private void toggleVisibilityViews(List<PowerEbookDecorator> views) {
        for (PowerEbookDecorator view : views) {
            switch (view.getVisibility()) {
                case VISIBLE:
                    view.hideView();
                    break;
                case GONE:
                case INVISIBLE:
                    view.revealView();
                    break;
                default:

            }
        }
    }

    private List<PowerEbookDecorator> getAllChildrenViews1() {
        return allChildrenViews = allChildrenViews == null ? getAllChildrenButFirst() : allChildrenViews;
    }

    private List<PowerEbookDecorator> getAllChildrenButFirst() {
        int count = getChildCount();
        List<PowerEbookDecorator> viewList = new LinkedList<>();
        for (int i = 1; i < count; i++) {
            if (getChildAt(i) instanceof PowerEbookDecorator) {
                viewList.add((PowerEbookDecorator) getChildAt(i));
            }
        }
        return viewList;
    }
}
