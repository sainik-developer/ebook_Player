package com.sixfingers.bp.model;

import android.graphics.Color;

import com.sixfingers.bp.model.enums.Border;
import com.sixfingers.bp.model.enums.TextAlign;

import java.util.LinkedList;
import java.util.List;

public class Text extends Content {

    public final static String DEFAULT_FONT_NAME = "helvetica.ttf";
    public final static String DEFAULT_FONT_NUMBER = "20";
    public final static String DEFAULT_FONT_ALIGN = "LEFT";
    public final static String DEFAULT_BORDER = "NONE";
    public final static String DEFAULT_BG_COLOR = String.valueOf(Color.TRANSPARENT);
    public final static String DEFAULT_TEXT_COLOR = String.valueOf(Color.BLACK);
    public final static String DEFAULT_ANGLE = "0";
    public final static String DEFAULT_ACTION_ENABLE = "false";
    public final static String DEFAULT_PARAGRAPH_SPACING = "20";

    public String fontName;
    public int fontNumber = 20;
    public TextAlign textAlign;
    public Border border;
    public int backGroudColor;
    public int textColor;
    public int angle;
    public boolean actionEnable;
    public String audioRes;
    public int paragraphSpacing;

    public List<Paragraph> paragraphs = new LinkedList<>();

}
