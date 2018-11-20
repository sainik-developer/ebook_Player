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
public class BoldTagProcessor extends TagProcessor<Paragraph, TextStyleSpanable> {
    @Override
    public String[] childTags() {
        return new String[]{"ac"};
    }

    @Override
    public String name() {
        return "b";
    }

    @Override
    public void read(XmlPullParser parser, Paragraph paragraph) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "b");
        int start = paragraph.text.length();
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() == XmlPullParser.START_TAG) {
                String name = parser.getName();
                if (name.equals("ac")) {
                    TagProcessor<Paragraph, ActionSpanable> actionSpanableTagProcessor = TagEnum.ACTION.getProcessor();
                    actionSpanableTagProcessor.read(parser, paragraph);
                } else {
                    skip(parser);
                }
            } else {
                paragraph.text = paragraph.text + parser.getText();
            }
        }
        if (!parser.getName().equals("b")) {
            throw new IllegalStateException("bold tag is not closed!");
        }
        int end = paragraph.text.length();
        TextStyleSpanable boldSpanable = TextStyleSpanable.Type.BOLD.newInstance(start, end);
        paragraph.spanables.add(boldSpanable);
    }

    @Override
    protected void readAttributes(XmlPullParser parser, TextStyleSpanable textStyleSpanable) {
    }
}
