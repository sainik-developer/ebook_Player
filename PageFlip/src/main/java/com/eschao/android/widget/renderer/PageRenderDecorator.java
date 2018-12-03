package com.eschao.android.widget.renderer;

public class PageRenderDecorator extends PageRender {

    public final PageRender pageRender;

    public PageRenderDecorator(final PageRender pageRender) {
        super(null, null, null, 0);
        this.pageRender = pageRender;
    }


    @Override
    public void onDrawFrame() {
        pageRender.onDrawFrame();
    }

    @Override
    public void onSurfaceChanged(int width, int height) {
        pageRender.onSurfaceChanged(width, height);
    }

    @Override
    public boolean onEndedDrawing(int what) {
        return pageRender.onEndedDrawing(what);
    }

    @Override
    public boolean canFlipForward() {
        return pageRender.canFlipForward();
    }

    @Override
    public boolean canFlipBackward() {
        return pageRender.canFlipBackward();
    }
}
