package com.eschao.android.widget.view;

import android.graphics.Bitmap;

public interface Provider<T, K> {

    Bitmap getBackgroundBitmap(final int index);

    T provide(final int index);

    void setFilter(K k);

}
