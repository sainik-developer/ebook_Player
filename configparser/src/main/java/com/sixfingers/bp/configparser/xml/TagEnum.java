package com.sixfingers.bp.configparser.xml;

/**
 * Created by sainik on 14.11.18.
 */

public enum TagEnum {

    TEXT(new TextTagProcessor(), "Text"),
    PARAGRAPH(new ParagraphTagProcessor(), "p"),
    ACTION(new ActionTagProcessor(), "ac"),
    BOLD(new ActionTagProcessor(), "b"),
    ITALIC(new ItalicTagProcessor(), "i"),
    SHADOW(new ShadowTagProcessor(), "sh"),
    TEXT_BACK_GROUND(new TextBackGroundTagProcessor(), "bg");


    private TagProcessor<?> tagProcessor;
    private String tagName;


    TagEnum(final TagProcessor<?> tagProcessor, final String tagName) {
        this.tagProcessor = tagProcessor;
        this.tagName = tagName;
    }

    @SuppressWarnings("unchecked")
    public <T> TagProcessor<T> getProcessor() {
        return (TagProcessor<T>) tagProcessor;
    }
}
