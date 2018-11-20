package com.sixfingers.bp.model;

public class Position {

    public float top;
    public float left;
    public float width;
    public float height;

    public Position(final String formattedStr) {

        String[] vals = formattedStr.split(",");
        top = Float.parseFloat(vals[0]);
        left = Float.parseFloat(vals[1]);
        width = Float.parseFloat(vals[2]);
        height = Float.parseFloat(vals[3]);
        if (top >= 0 && left >= 0 && width > 0 && height > 0) {
            return;
        }
        throw new IllegalArgumentException("Position is not valid");
    }

}
