package com.sixfingers.bp.model;

import android.text.Spannable;

/**
 * Created by sainik on 11.11.18.
 */

public abstract class Spanable {

    public int start;
    public int end;

    public Spanable(final int start, final int end){
        this.start = start;
        this.end = end;
    }
}
