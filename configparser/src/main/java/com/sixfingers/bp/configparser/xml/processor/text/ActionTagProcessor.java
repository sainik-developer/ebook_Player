package com.sixfingers.bp.configparser.xml.processor.text;

import com.sixfingers.bp.configparser.xml.processor.TagProcessor;
import com.sixfingers.bp.model.ActionSpanable;
import com.sixfingers.bp.model.Paragraph;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by sainik on 12.11.18.
 */
public class ActionTagProcessor extends TagProcessor<Paragraph, ActionSpanable> {
    @Override
    public String[] childTags() {
        return new String[0];
    }

    @Override
    public String name() {
        return "ac";
    }

    @Override
    public void read(XmlPullParser parser, Paragraph paragraph) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "ac");
        String text = parser.getText();
        ActionSpanable actionSpanable = new ActionSpanable(paragraph.text.length(), paragraph.text.length() + text.length());
        readAttributes(parser, actionSpanable);
    }

    @Override
    protected void readAttributes(XmlPullParser parser, ActionSpanable t) {
        t.audioTime = Float.parseFloat(parser.getAttributeValue(ns, "audio-time"));
    }
}
