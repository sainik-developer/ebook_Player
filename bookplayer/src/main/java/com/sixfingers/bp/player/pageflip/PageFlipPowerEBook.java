package com.sixfingers.bp.player.pageflip;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import com.eschao.android.widget.renderer.CanvasDecorator;
import com.eschao.android.widget.view.PageFlipView;
import com.eschao.android.widget.view.PageFlipViewType;
import com.eschao.android.widget.view.provider.ContentProviderBuilder;
import com.sixfingers.bp.model.Book;
import com.sixfingers.bp.player.AbstractPowerEBook;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class PageFlipPowerEBook extends AbstractPowerEBook implements GestureDetector.OnGestureListener {

    private PageFlipView pageFlipView;
    private GestureDetector mGestureDetector;

    public PageFlipView getPageFlipView() {
        return pageFlipView;
    }

    public PageFlipPowerEBook(final Context context,
                              final Book book,
                              final String fileSystemLocation,
                              final int orientation,
                              final CanvasDecorator canvasDecorator) {
        super(context);
        setLayoutParams(new FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT));
        setSystemUiVisibility(SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        addView(createPageFlipView(context, book, fileSystemLocation, orientation, canvasDecorator),
                0, new FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT,
                        Gravity.CENTER));
        mGestureDetector = new GestureDetector(context, this);
    }

    private PageFlipView createPageFlipView(final Context context,
                                            final Book book,
                                            final String fileSystemLocation,
                                            final int orientation,
                                            final CanvasDecorator canvasDecorator) {
        // partially create the builder of page provider
        final ContentProviderBuilder builder = new ContentProviderBuilder(ContentProviderBuilder.ContentProviderType.FILE)
                .setFileSystemBasePath(fileSystemLocation);
        pageFlipView = new PageFlipView(context,
                orientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE ? PageFlipViewType.LANDSCAPE : PageFlipViewType.PORTRAIT,
                book, builder, canvasDecorator);
        return pageFlipView;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return event.getAction() == MotionEvent.ACTION_UP ?
                pageFlipView.onFingerUp(event.getX(), event.getY()) :
                mGestureDetector.onTouchEvent(event) || super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        pageFlipView.onFingerDown(e.getX(), e.getY());
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        pageFlipView.onFingerMove(e2.getX(), e2.getY());
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
}