package com.eschao.android.widget.renderer;

import android.content.Context;
import android.graphics.Canvas;

import com.sixfingers.bp.model.Page;


public abstract class CanvasDecorator {

    public final CanvasDecorator canvasDecorator;

    public CanvasDecorator(final CanvasDecorator canvasDecorator) {
        this.canvasDecorator = canvasDecorator;
    }

    /***
     * This is where decoration will go on
     */
    public abstract void decorateCanvas(final Canvas canvas, final Page page, final Context context);
}
