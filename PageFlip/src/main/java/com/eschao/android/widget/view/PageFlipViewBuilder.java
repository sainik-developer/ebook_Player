package com.eschao.android.widget.view;

import android.content.Context;

import com.eschao.android.widget.renderer.PageRender;
import com.sixfingers.bp.model.Book;

public class PageFlipViewBuilder {

    private final Context context;
    private PageRender pageRender;
    private Book book;

    public PageFlipViewBuilder(final Context context) {
        this.context = context;
    }

    public PageFlipViewBuilder addPageRenderer(final PageRender pageRender) {
        this.pageRender = pageRender;
        return this;
    }

    public PageFlipViewBuilder addPageFlipViewBuilder(final Book book) {
        this.book = book;
        return this;
    }

    public PageFlipView build() {
        return new PageFlipView(this.context, this.pageRender, this.book);
    }

}
