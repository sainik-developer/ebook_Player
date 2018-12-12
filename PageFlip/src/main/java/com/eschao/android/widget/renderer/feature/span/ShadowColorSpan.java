package com.eschao.android.widget.renderer.feature.span;

import android.os.Parcel;
import android.text.ParcelableSpan;
import android.text.TextPaint;
import android.text.style.CharacterStyle;
import android.text.style.UpdateAppearance;

public class ShadowColorSpan extends CharacterStyle implements UpdateAppearance, ParcelableSpan {

    private final int mColor;
    private final float mRedius;
    private final float mDx;
    private final float mDy;


    public ShadowColorSpan(final float redius, final float dx, float dy, int shadowColor) {
        mRedius = redius;
        mDx = dx;
        mDy = dy;
        mColor = shadowColor;
    }


    @Override
    public int getSpanTypeId() {
        return 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    @Override
    public void updateDrawState(TextPaint tp) {
        tp.setShadowLayer(mRedius, mDx, mDy, mColor);
    }
}
