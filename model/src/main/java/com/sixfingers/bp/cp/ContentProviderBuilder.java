package com.sixfingers.bp.cp;

import com.sixfingers.bp.model.Book;

import java.util.Locale;

public class ContentProviderBuilder {
    private Book book;
    private Locale locale;
    private ContentProviderType type;
    private String fileSystemBasePath;

    public ContentProviderBuilder(final ContentProviderType type) {
        this.type = type;
    }

    public ContentProviderBuilder setLocale(final Locale locale) {
        this.locale = locale;
        return this;
    }

    public ContentProviderBuilder setBook(final Book book) {
        this.book = book;
        return this;
    }

    public ContentProviderBuilder setFileSystemBasePath(final String fileSystemBasePath) {
        this.fileSystemBasePath = fileSystemBasePath;
        return this;
    }

    public ContentProvider build() {
        switch (type) {
            case CLOUD:
                return null;
            case FILE:
                if (fileSystemBasePath == null || fileSystemBasePath.isEmpty()) {
                    throw new IllegalArgumentException("fileSystemBasePath has to be set for FileContentProviderType");
                }
                if (book == null) {
                    throw new IllegalArgumentException("Book has be to set");
                }
                if (locale == null) {
                    return new FileSystemContentProvider(book, fileSystemBasePath);
                } else {
                    return new FileSystemContentProvider(book, locale, fileSystemBasePath);
                }
            default:
                return null;
        }
    }

    /***
     * Content like image, audio file and configuration json can come from
     * either file system packed with apk or cloud
     */
    public enum ContentProviderType {
        /***
         * when assets have to be taken from apk unpacked file system
         */
        FILE,
        /***
         * when assets have to be taken from cloud
         */
        CLOUD;
    }
}
