package com.sixfingers.bp.model;

/**
 * Created by sainik on 11.11.18.
 */

public class ActionSpanable extends Spanable {

    public float audioTime;
    public String text;
    // in second iteration
    // public Uri videoClip;
    // public int id;

    public ActionSpanable(final int start, final int end, final int audioTime) {
        super(start, end);
        this.audioTime = audioTime;
    }
}
