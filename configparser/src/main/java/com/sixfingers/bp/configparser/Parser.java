package com.sixfingers.bp.configparser;

/**
 * Created by sainik on 04.11.18.
 */

public interface Parser<I, T> {

    /***
     *
     * @param i
     * @return
     */
    T parser(I i);
}
