package com.sixfingers.bp.model;

import java.util.LinkedList;
import java.util.List;

public class Paragraph {
    public int lineSpacing = 5;
    public int lineIndent = 7;
    public int headIndent = 7;
    public String text = "";
    public List<Spanable> spanables = new LinkedList<>();

    public List<TextStyleSpanable> getTextStyles(final TextStyleSpanable.Type type) {
        List<TextStyleSpanable> textStyleSpanables = new LinkedList<>();
        for (Spanable spanable : spanables)
            if (spanable instanceof TextStyleSpanable
                    && ((TextStyleSpanable) spanable).type == type)
                textStyleSpanables.add((TextStyleSpanable) spanable);
        return textStyleSpanables;
    }

    public List<ActionSpanable> getActionSpanables() {
        List<ActionSpanable> actionSpanables = new LinkedList<>();
        for (Spanable spanable : spanables) {
            if (spanable instanceof ActionSpanable) {
                actionSpanables.add((ActionSpanable) spanable);
            }
        }
        return actionSpanables;
    }

}
