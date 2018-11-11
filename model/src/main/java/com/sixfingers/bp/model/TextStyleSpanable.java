package com.sixfingers.bp.model;

import android.graphics.Color;

/**
 * Created by sainik on 11.11.18.
 */
public class TextStyleSpanable extends Spanable {


    public final Type type;
    public final int color;

    TextStyleSpanable(final int start, final int end, final Type type, final int color) {
        super(start, end);
        this.type = type;
        this.color = color;
    }

    TextStyleSpanable(final int start, final int end, final Type type) {
        super(start, end);
        this.type = type;
        this.color = Color.WHITE;
    }

    public enum Type {
        BOLD, ITALIC, SHADOW, LABEL_BACKGROUND;

        TextStyleSpanable newInstance(final int start, final int end, final int color) {
            switch (this) {
                case SHADOW:
                    return new TextStyleSpanable(start, end, this, color);
                case LABEL_BACKGROUND:
                    return new TextStyleSpanable(start, end, this, color);
            }
            throw new IllegalArgumentException("unexpected error happened!, as neither of SHADOW, LABEL_BACKGROUND");
        }

        TextStyleSpanable newInstance(final int start, final int end) {
            switch (this) {
                case BOLD:
                    return new TextStyleSpanable(start, end, this);
                case ITALIC:
                    return new TextStyleSpanable(start, end, this);
            }
            throw new IllegalArgumentException("only for bold and italic!");
        }
    }
}
