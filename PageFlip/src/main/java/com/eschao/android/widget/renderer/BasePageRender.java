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

package com.eschao.android.widget.renderer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;

import com.eschao.android.widget.pageflip.PageFlip;
import com.eschao.android.widget.view.provider.ContentProvider;

public abstract class BasePageRender extends PageRender {

    public final static int MSG_ENDED_DRAWING_FRAME = 1;
    private final static String TAG = "PageRender";

    final static int DRAW_MOVING_FRAME = 0;
    final static int DRAW_ANIMATING_FRAME = 1;
    final static int DRAW_FULL_PAGE = 2;

    final static int MAX_PAGES = 30;

    public int mPageNo;
    public int mDrawCommand;
    public Bitmap mBitmap;
    public Canvas mCanvas;
    public Bitmap mBackgroundBitmap;
    public Context mContext;
    public Handler mHandler;
    public PageFlip mPageFlip;
    public ContentProvider pageContentProvider;

    public BasePageRender(Context context, PageFlip pageFlip,
                          Handler handler, int pageNo,
                          final ContentProvider pageContentProvider) {
        mContext = context;
        mPageFlip = pageFlip;
        mPageNo = pageNo;
        mDrawCommand = DRAW_FULL_PAGE;
        mCanvas = new Canvas();
        mPageFlip.setListener(this);
        mHandler = handler;
        this.pageContentProvider = pageContentProvider;
    }

    /**
     * Get page number
     *
     * @return page number
     */
    public int getPageNo() {
        return mPageNo;
    }

    /**
     * Release resources
     */
    public void release() {
        if (mBitmap != null) {
            mBitmap.recycle();
            mBitmap = null;
        }

        mPageFlip.setListener(null);
        mCanvas = null;
        mBackgroundBitmap = null;
    }

    /**
     * Handle finger moving event
     *
     * @param x x coordinate of finger moving
     * @param y y coordinate of finger moving
     * @return true if event is handled
     */
    public boolean onFingerMove(float x, float y) {
        mDrawCommand = DRAW_MOVING_FRAME;
        return true;
    }

    /**
     * Handle finger up event
     *
     * @param x x coordinate of finger up
     * @param y y coordinate of inger up
     * @return true if event is handled
     */
    public boolean onFingerUp(float x, float y) {
        if (mPageFlip.animating()) {
            mDrawCommand = DRAW_ANIMATING_FRAME;
            return true;
        }

        return false;
    }

    /**
     * Calculate font size by given SP unit
     */
    protected int calcFontSize(int size) {
        return (int) (size * mContext.getResources().getDisplayMetrics()
                .scaledDensity);
    }

    /***
     * send message to main thread to notify drawing is ended so that
     * we can continue to calculate next animation frame if need.
     * Remember: the drawing operation is always in GL thread instead of
     * main thread
     */
    public void sendMesaageDrawingFinished() {
        Message msg = Message.obtain();
        msg.what = MSG_ENDED_DRAWING_FRAME;
        msg.arg1 = mDrawCommand;
        mHandler.sendMessage(msg);
    }
}
