package com.eschao.android.widget.renderer;

import android.content.Context;
import android.graphics.Typeface;

import java.lang.ref.WeakReference;

public class FontManager {

    private static WeakReference<Typeface> typefaceWeakReference;

    public static Typeface getByName(final Context context, final String fontName) {
        return typefaceWeakReference.get() != null ? typefaceWeakReference.get() :
                (typefaceWeakReference = new WeakReference<>(Typeface.createFromAsset(context.getAssets(), fontName))).get();
    }


}
