package com.sixfingers.bp.model;

import java.util.LinkedList;
import java.util.List;

public class Paragraph {
    public int lineSpacing;
    public int lineIndent;
    public int headIndent;
    public String text;
    public List<Spanable> spanables = new LinkedList<>();

    public List<TextStyleSpanable> getTextStyles() {
        List<TextStyleSpanable> textStyleSpanables = new LinkedList<>();
        for (Spanable spanable : spanables) {
            if (spanable instanceof TextStyleSpanable) {
                textStyleSpanables.add((TextStyleSpanable) spanable);
            }
        }
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
