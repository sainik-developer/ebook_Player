package com.sixfingers.bp.cp;

import android.graphics.Bitmap;

import com.sixfingers.bp.model.Page;

import java.util.Locale;

/***
 * provider provides the next Page and Bitmap.
 * as well it allows the set Language select filter the pages of selected language
 * If no language is selected then Default `English` is cconsidered to be chosen
 */
public interface ContentProvider {

    /***
     * get the file name by index and make a bitmap to fit the height and widget
     * @param height the screen height
     * @param width the screen width
     * @param index the image will be fetched by the index
     * @return
     */
    Bitmap getBackgroundBitmap(final int height, final int width, final int index);

    /***
     * give the page of the index or null if index of out of range
     * @param index of the book
     * @return Page
     */
    Page provide(final int index);

    /***
     *  To selected the language so Page content can be provided as per preference
     * @param language
     */
    void setLanguage(Locale language);

    int lastPageIndex();
}
