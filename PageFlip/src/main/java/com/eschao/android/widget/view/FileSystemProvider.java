package com.eschao.android.widget.view;

import android.graphics.Bitmap;
import android.opengl.GLSurfaceView;
import android.util.LruCache;

import com.sixfingers.bp.model.Book;
import com.sixfingers.bp.model.Page;

import java.util.Locale;

public class FileSystemProvider implements Provider<Page, Locale> {

    private final GLSurfaceView view;
    private final Book book;

    private Locale locale;
    private final String basePath;

    public FileSystemProvider(final GLSurfaceView view, final Book book, final Locale locale) {
        this.book = book;
        this.view = view;
        this.locale = locale;
        this.basePath = book.globalProp.basePath;
    }

    LruCache<Integer, Bitmap> tt = new LruCache<Integer, Bitmap>(3) {
        @Override
        protected void entryRemoved(boolean evicted,
                                    Integer key,
                                    Bitmap oldValue,
                                    Bitmap newValue) {
            if (evicted && oldValue != null)
                oldValue.recycle();
        }
    };

    @Override
    public Bitmap getBackgroundBitmap(final int index) {
//        BitmapDrawable.createFromPath(resourceLoc + "/" + imagePath);
        return null;
    }

    @Override
    public Page provide(int index) {
        return book.getPagesByLanguage(this.locale).get(index);
    }

    @Override
    public void setFilter(Locale o) {
        this.locale = o;
    }
}
