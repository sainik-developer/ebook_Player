package com.sixfingers.bp.callback;

public interface Action<T extends Enum> {

    /***
     *
     * @param t
     * @param args
     */
    void act(T t, Object... args);

}
