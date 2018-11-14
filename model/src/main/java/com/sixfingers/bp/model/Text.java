package com.sixfingers.bp.model;

import com.sixfingers.bp.model.enums.Border;

import java.util.LinkedList;
import java.util.List;

public class Text extends Content {
    public String fontName;
    public int fontNumber = 20;
    public String textAlign;
    public Border border;
    public String backGroudColor;
    public String textColor;
    public boolean highlightTextOnAudio;
    public boolean actionEnable;
    public String audioRes;
    public int paragraphSpacing;

    public List<Paragraph> paragraphs = new LinkedList<>();

}
