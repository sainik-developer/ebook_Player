package com.sixfingers.bp.player;

import android.support.constraint.ConstraintLayout;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class PowerEbookDecorator extends ConstraintLayout {

    public FrameLayout frameLayout;

    public PowerEbookDecorator(final ViewGroup viewGroup) {
        super(viewGroup.getContext());
        if (viewGroup instanceof FrameLayout) {
            frameLayout = (FrameLayout) viewGroup;
        } else if (viewGroup instanceof PowerEbookDecorator) {
            frameLayout = ((PowerEbookDecorator) viewGroup).frameLayout;
        }
    }


}
