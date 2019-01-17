package com.eschao.android.widget.renderer;

/***
 *
 */
public class PageRenderDecorator extends PageRender {

    protected final BasePageRender basePageRender;

    public PageRenderDecorator(final PageRender pageRender) {
        if (pageRender instanceof BasePageRender) {
            this.basePageRender = (BasePageRender) pageRender;
        } else if (pageRender instanceof PageRenderDecorator) {
            this.basePageRender = ((PageRenderDecorator) pageRender).basePageRender;
            if (this.basePageRender == null) {
                throw new IllegalArgumentException("Argument is wrong");
            }
        } else {
            throw new IllegalArgumentException("Argument is wrong");
        }
    }

    @Override
    public void onDrawFrame() {
        basePageRender.onDrawFrame();
    }

    @Override
    public void onSurfaceChanged(int width, int height) {
        basePageRender.onSurfaceChanged(width, height);
    }

    @Override
    public boolean onEndedDrawing(int what) {
        return basePageRender.onEndedDrawing(what);
    }

    @Override
    public boolean canFlipForward() {
        return basePageRender.canFlipForward();
    }

    @Override
    public boolean canFlipBackward() {
        return basePageRender.canFlipBackward();
    }

    @Override
    public int getPageNo() {
        return basePageRender.getPageNo();
    }

    @Override
    public void release() {
        basePageRender.release();
    }

    @Override
    public boolean onFingerMove(float x, float y) {
        return basePageRender.onFingerMove(x, y);
    }

    @Override
    public boolean onFingerUp(float x, float y) {
        return basePageRender.onFingerUp(x, y);
    }

    @Override
    public void sendMesaageDrawingFinished() {
        basePageRender.sendMesaageDrawingFinished();
    }
}
