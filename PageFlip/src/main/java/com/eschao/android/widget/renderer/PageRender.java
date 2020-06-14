/*
 * Copyright (C) 2016 eschao <esc.chao@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.eschao.android.widget.renderer;

import com.eschao.android.widget.pageflip.OnPageFlipListener;

/**
 * Abstract Page Render
 *
 * @author eschao
 */
public abstract class PageRender implements OnPageFlipListener {

    /**
     * Get page number
     *
     * @return page number
     */
    public abstract int getPageNo();

    /**
     * Release resources
     */
    public abstract void release();

    /**
     * Handle finger moving event
     *
     * @param x x coordinate of finger moving
     * @param y y coordinate of finger moving
     * @return true if event is handled
     */
    public abstract boolean onFingerMove(float x, float y);

    /**
     * Handle finger up event
     *
     * @param x x coordinate of finger up
     * @param y y coordinate of inger up
     * @return true if event is handled
     */
    public abstract boolean onFingerUp(float x, float y);


    /***
     * send message to main thread to notify drawing is ended so that
     * we can continue to calculate next animation frame if need.
     * Remember: the drawing operation is always in GL thread instead of
     * main thread
     */
    public abstract void sendMessageDrawingFinished();

    /**
     * Render page frame
     */
    public abstract void onDrawFrame();

//    public abstract void reflectDrawFrame();

    /**
     * Handle surface changing event
     *
     * @param width  surface width
     * @param height surface height
     */
    public abstract void onSurfaceChanged(int width, int height);

    /**
     * Handle drawing ended event
     *
     * @param what draw command
     * @return true if render is needed
     */
    public abstract boolean onEndedDrawing(int what);
}
