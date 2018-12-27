package com.eschao.android.widget.view.provider;

import com.eschao.android.widget.view.PageFlipView;
import com.sixfingers.bp.model.Book;

import java.util.Locale;

public class ContentProviderBuilder {

    private Book book;
    private Locale locale;
    private PageFlipView pageFlipView;
    private ContentProviderType type;




    public ContentProviderBuilder(final ContentProviderType type) {
        this.type = type;
    }

    public ContentProviderBuilder setPageFlipView(PageFlipView pageFlipView) {
        this.pageFlipView = pageFlipView;
        return this;
    }

    public ContentProviderBuilder setLocale(Locale locale) {
        this.locale = locale;
        return this;
    }

    public ContentProviderBuilder setBook(Book book) {
        this.book = book;
        return this;
    }

    public ContentProvider build() {
        switch (type) {
            case Cloud:
                return null;
            case File:
                return new FileSystemContentProvider(book, locale);
            default:
                return null;
        }
    }


    enum ContentProviderType {
        File,
        Cloud;
    }
}
