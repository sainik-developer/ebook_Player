package com.sixfingers.bp.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class Page {
    public Locale lang;
    public int pageNo;
    public String backgroundImageName;
    public List<Content> contents = new ArrayList<>();


    public List<?> getBySubType(final Class<? extends Content> aClass) {
        if (aClass.getName().equals(Text.class.getName())) {
            List<Text> texts = new LinkedList<>();
            for (Content content : contents) {
                if (content instanceof Text) {
                    texts.add((Text) content);
                }
            }
            return texts;
        } else if (aClass.getTypeName().equals(Audio.class.getTypeName())) {
            List<Audio> audios = new LinkedList<>();
            for (Content content : contents) {
                if (content instanceof Audio) {
                    audios.add((Audio) content);
                }
            }
            return audios;
        }
        return null;
    }

}
