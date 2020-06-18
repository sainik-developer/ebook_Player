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
package com.eschao.android.widget.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;

import com.eschao.android.widget.pageflip.PageFlip;
import com.eschao.android.widget.pageflip.PageFlipException;
import com.eschao.android.widget.renderer.BasePageRender;
import com.eschao.android.widget.renderer.DoublePagesRender;
import com.eschao.android.widget.renderer.PageRender;
import com.eschao.android.widget.renderer.SinglePageRender;
import com.eschao.android.widget.view.provider.ContentProvider;
import com.eschao.android.widget.view.provider.ContentProviderBuilder;
import com.sixfingers.bp.model.Book;

import java.util.concurrent.locks.ReentrantLock;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Page flip view
 *
 * @author sainik
 */
public class PageFlipView extends GLSurfaceView implements Renderer {

    private final static String TAG = "PageFlipView";

    private int mPageNo;
    private int mDuration;
    private final Book book;
    private PageFlip mPageFlip;
    private final PageFlipViewType type;
    private PageRender mPageRender;
    private ReentrantLock mDrawLock;

    private final ContentProvider contentProvider;

    private static Handler mHandler;

    /****
     *
     * @param context
     * @param type
     * @param book
     * @param contentProviderBuilder
     */
    public PageFlipView(final Context context, final PageFlipViewType type,
                        final Book book, final ContentProviderBuilder contentProviderBuilder) {
        super(context);
        if (type == null || book == null || contentProviderBuilder == null) {
            throw new IllegalArgumentException("either type, book or contentProviderBuilder is null");
        }
        this.type = type;
        this.book = book;
        contentProvider = prepareContentProvider(contentProviderBuilder);
        init(context);
    }

    private void init(Context context) {
        // create handler to tackle message
        newHandler();

        // load preferences
        SharedPreferences pref = PreferenceManager
                .getDefaultSharedPreferences(context);
        mDuration = 1000;
        int pixelsOfMesh = pref.getInt(Constants.PREF_MESH_PIXELS, 10);
        boolean isAuto = pref.getBoolean(Constants.PREF_PAGE_MODE, true);

        // create PageFlip
        mPageFlip = new PageFlip(context);
        mPageFlip.setSemiPerimeterRatio(0.8f)
                .setShadowWidthOfFoldEdges(5, 60, 0.3f)
                .setShadowWidthOfFoldBase(5, 80, 0.4f)
                .setPixelsOfMesh(pixelsOfMesh)
                .enableAutoPage(isAuto);
        setEGLContextClientVersion(2);

        // init others
        mPageNo = 0;
        mDrawLock = new ReentrantLock();
        preparePageRenderer();
        // configure render
        setRenderer(this);
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

    private ContentProvider prepareContentProvider(final ContentProviderBuilder builder) {
        return builder
                .setBook(book)
                .setPageFlipView(this)
                .build();
    }

    private void preparePageRenderer() {
        switch (this.type) {
            case PORTRAIT:
                mPageRender = new SinglePageRender(getContext(), mPageFlip, mHandler, mPageNo, contentProvider);
                break;
            case LANDSCAPE:
                mPageRender = new DoublePagesRender(getContext(), mPageFlip, mHandler, mPageNo, contentProvider);
                break;
        }
    }

    /**
     * Is auto page mode enabled?
     *
     * @return true if auto page mode enabled
     */
    public boolean isAutoPageEnabled() {
        return mPageFlip.isAutoPageEnabled();
    }

    /**
     * Get duration of animating
     *
     * @return duration of animating
     */
    public int getAnimateDuration() {
        return mDuration;
    }

    /**
     * Set animate duration
     *
     * @param duration duration of animating
     */
    public void setAnimateDuration(int duration) {
        mDuration = duration;
    }

    /**
     * Get pixels of mesh
     *
     * @return pixels of mesh
     */
    public int getPixelsOfMesh() {
        return mPageFlip.getPixelsOfMesh();
    }

    /**
     * Handle finger down event
     *
     * @param x finger x coordinate
     * @param y finger y coordinate
     */
    public void onFingerDown(float x, float y) {
        // if the animation is going, we should ignore this event to avoid
        // mess drawing on screen
        if (!mPageFlip.isAnimating() &&
                mPageFlip.getFirstPage() != null) {
            mPageFlip.onFingerDown(x, y);
        }
    }

    /**
     * Handle finger moving event
     *
     * @param x finger x coordinate
     * @param y finger y coordinate
     */
    public void onFingerMove(float x, float y) {
        if (mPageFlip.isAnimating()) {
            // nothing to do during animating
        } else if (mPageFlip.canAnimate(x, y)) {
            // if the point is out of current page, try to start animating
            onFingerUp(x, y);
        }
        // move page by finger
        else if (mPageFlip.onFingerMove(x, y)) {
            try {
                mDrawLock.lock();
                if (mPageRender != null &&
                        mPageRender.onFingerMove(x, y)) {
                    requestRender();
                }
            } finally {
                mDrawLock.unlock();
            }
        }
    }

    /**
     * Handle finger up event and start animating if need
     *
     * @param x finger x coordinate
     * @param y finger y coordinate
     */
    public boolean onFingerUp(float x, float y) {
        boolean isEventConsumed = false;
        if (!mPageFlip.isAnimating()) {
            mPageFlip.onFingerUp(x, y, mDuration);
            try {
                mDrawLock.lock();
                if (mPageRender != null &&
                        mPageRender.onFingerUp(x, y)) {
                    requestRender();
                    isEventConsumed = true;
                }
            } finally {
                mDrawLock.unlock();
            }
        }
        return isEventConsumed;
    }

    /**
     * Draw frame
     *
     * @param gl OpenGL handle
     */
    @Override
    public void onDrawFrame(GL10 gl) {
        try {
            mDrawLock.lock();
            if (mPageRender != null) {
                mPageRender.onDrawFrame();
                mPageRender.sendMessageDrawingFinished();
            }
        } finally {
            mDrawLock.unlock();
        }
    }

    /**
     * Handle surface is changed TODO remove this function for now
     *
     * @param gl     OpenGL handle
     * @param width  new width of surface
     * @param height new height of surface
     */
    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        try {
            mPageFlip.onSurfaceChanged(width, height);

            // if there is the second page, create double page render when need
            int pageNo = mPageRender.getPageNo();
//            if (mPageFlip.getSecondPage() != null && width > height) {
//                if (!(mPageRender instanceof DoublePagesRender)) {
//                    mPageRender.release();
//                    mPageRender = new DoublePagesRender(getContext(),
//                            mPageFlip,
//                            mHandler,
//                            pageNo, null);
//                }
//            }
//            // if there is only one page, create single page render when need
//            else if (!(mPageRender instanceof SinglePageRender)) {
//                mPageRender.release();
//                mPageRender = new SinglePageRender(getContext(),
//                        mPageFlip,
//                        mHandler,
//                        pageNo, null);
//            }

            // let page render handle surface change
            mPageRender.onSurfaceChanged(width, height);
        } catch (PageFlipException e) {
            Log.e(TAG, "Failed to run PageFlipFlipRender:onSurfaceChanged");
        }
    }

    /**
     * Handle surface is created
     *
     * @param gl     OpenGL handle
     * @param config EGLConfig object
     */
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        try {
            mPageFlip.onSurfaceCreated();
        } catch (PageFlipException e) {
            Log.e(TAG, "Failed to run PageFlipFlipRender:onSurfaceCreated");
        }
    }

    /**
     * Create message handler to cope with messages from page render,
     * Page render will send message in GL thread, but we want to handle those
     * messages in main thread that why we need handler here
     */
    private void newHandler() {
        mHandler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case BasePageRender.MSG_ENDED_DRAWING_FRAME:
                        try {
                            mDrawLock.lock();
                            // notify page render to handle ended drawing
                            // message
                            if (mPageRender != null &&
                                    mPageRender.onEndedDrawing(msg.arg1)) {
                                requestRender();
                            }
                        } finally {
                            mDrawLock.unlock();
                        }
                        break;

                    default:
                        break;
                }
            }
        };
    }
}
