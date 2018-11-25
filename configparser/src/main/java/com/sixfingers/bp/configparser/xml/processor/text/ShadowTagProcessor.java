package com.sixfingers.bp.configparser.xml.processor.text;

import com.sixfingers.bp.configparser.xml.processor.TagEnum;
import com.sixfingers.bp.configparser.xml.processor.TagProcessor;
import com.sixfingers.bp.model.ActionSpanable;
import com.sixfingers.bp.model.Paragraph;
import com.sixfingers.bp.model.TextStyleSpanable;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by sainik on 12.11.18.
 */
public class ShadowTagProcessor extends TagProcessor<Paragraph, TextStyleSpanable> {

    @Override
    public String[] childTags() {
        return new String[]{"ac", "b", "i"};
    }

    @Override
    public String name() {
        return "sh";
    }

    @Override
    public void read(XmlPullParser parser, Paragraph paragraph) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "sh");
        TextStyleSpanable shadowSpanable = TextStyleSpanable.Type.SHADOW.newInstance(paragraph.text.length(), 0, 0);
        readAttributes(parser, shadowSpanable);
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() == XmlPullParser.START_TAG) {
                String name = parser.getName();
                if (name.equals("ac")) {
                    TagProcessor<Paragraph, ActionSpanable> actionSpanableTagProcessor = TagEnum.ACTION.getProcessor();
                    actionSpanableTagProcessor.read(parser, paragraph);
                } else if (name.equals("b")) {
                    TagProcessor<Paragraph, TextStyleSpanable> processor = TagEnum.BOLD.getProcessor();
                    processor.read(parser, paragraph);
                } else if (name.equals("i")) {
                    TagProcessor<Paragraph, TextStyleSpanable> processor = TagEnum.ITALIC.getProcessor();
                    processor.read(parser, paragraph);
                } else {
                    throw new IllegalStateException("shadow tag only can have 'ac','b' or 'i'");
                }
            } else {
                paragraph.text = paragraph.text.concat(parser.getText());
            }
        }
        if (!parser.getName().equals("sh")) {
            throw new IllegalStateException("Shadow tag is not closed!");
        }
        shadowSpanable.end = paragraph.text.length();
        paragraph.spanables.add(shadowSpanable);
    }

    @Override
    protected void readAttributes(XmlPullParser parser, TextStyleSpanable textStyleSpanable) {
        textStyleSpanable.color = Integer.parseInt(parser.getAttributeValue(ns, "c"));
    }
}
