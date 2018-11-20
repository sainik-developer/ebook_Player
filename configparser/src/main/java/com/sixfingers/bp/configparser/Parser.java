package com.sixfingers.bp.configparser;

import com.sixfingers.bp.model.Book;

import java.io.InputStream;

/**
 * Created by sainik on 04.11.18.
 */

public interface Parser<I,T> {

    /***
     *
     * @param inputStream
     * @return
     */
    T parser(I inputStream);
}
