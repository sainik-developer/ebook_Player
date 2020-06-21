package com.sixfingers.bp.cp;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.LruCache;

import com.sixfingers.bp.model.Book;
import com.sixfingers.bp.model.Page;

import java.io.File;
import java.net.URI;
import java.util.Locale;

/***
 * This is the Page provider where Content is available on page,
 * it should be mainly in SD card
 */
public class FileSystemContentProvider implements ContentProvider<String> {

    private final Book book;
    private Locale locale;
    private final String basePath;
    private int totalPagesNo;

    private LruCache<Integer, Bitmap> bitmapCache = new LruCache<Integer, Bitmap>(3) {
        @Override
        protected void entryRemoved(boolean evicted,
                                    Integer key,
                                    Bitmap oldValue,
                                    Bitmap newValue) {
            if (evicted && oldValue != null)
                oldValue.recycle();
        }
    };

//    public FileSystemContentProvider(final Book book, final Locale locale) {
//        this.book = book;
//        this.locale = locale;
//        this.basePath = book.globalProp.basePath;
//    }

    public FileSystemContentProvider(final Book book, final String fileSystemBasePath) {
        this(book, Locale.ENGLISH, fileSystemBasePath);
        totalPagesNo = book.getPagesByLanguage(Locale.ENGLISH).size();
    }

    public FileSystemContentProvider(final Book book, final Locale locale, final String fileSystemBasePath) {
        this.book = book;
        this.locale = locale;
        this.basePath = book.globalProp.basePath = fileSystemBasePath;
    }

    @Override
    public Bitmap getBackgroundBitmap(final int height, final int width, final int index) {
        final Page requestPage;
        Bitmap bgBitmap = bitmapCache.get(index);
        if (bgBitmap == null && (requestPage = provide(index)) != null) {
            bgBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            bgBitmap.eraseColor(0xFFFFFFFF);
            Canvas bgCanvas = new Canvas(bgBitmap);
            Drawable bitmapDrawable = BitmapDrawable.createFromPath(basePath + File.separator + requestPage.backgroundImageName);
            if (bitmapDrawable != null) {
                bitmapDrawable.setBounds(0, 0, width, height);
                bitmapDrawable.draw(bgCanvas);
                bitmapCache.put(index, bgBitmap);
            }
            return bgBitmap;
        }
        return bgBitmap;
    }

    @Override
    public Page provide(int index) {
        return book.getPagesByLanguage(this.locale).get(index);
    }

    @Override
    public void setLanguage(Locale locale) {
        this.locale = locale;
    }

    @Override
    public int lastPageIndex() {
        return totalPagesNo - 1;
    }

    @Override
    public URI getAssetUri(String path) {
        return URI.create(basePath + File.separator + path);
    }
}
