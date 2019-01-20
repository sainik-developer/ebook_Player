package com.eschao.android.widget.utils;

import com.sixfingers.bp.model.Position;

public class CalculationUtils {


    public static Position convertPercentageToAbsoluteForParagraph(Position position, int width, int height) {
        final Position actualPosition = new Position();
        actualPosition.left = width * position.left / 100;
        actualPosition.top = height * position.top / 100;
        //TODO this code to be changed : This holds the width of the text
        actualPosition.width = width * position.width / 100;
        //TODO this code to be changed : This holds the height of the text
        actualPosition.height = height * position.height / 100;
        return actualPosition;

    }
}
