package com.eschao.android.widget.feature;

import android.graphics.Canvas;

import com.sixfingers.bp.model.Content;

/***
 *
 */
public interface Drawable<T extends Content> {

    /***
     *
     * @param canvas
     */
    void draw(final Canvas canvas, final T t);
}
