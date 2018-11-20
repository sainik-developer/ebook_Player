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
        ActionSpanable actionSpanable = new ActionSpanable(0, 0);
        readAttributes(parser, actionSpanable);
        int count = 0;
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                actionSpanable.text = parser.getText();
                if (actionSpanable.text == null)
                    throw new IllegalStateException("Action tag should have a value");
            }
            count++;
            if (count > 1)
                throw new IllegalStateException("End tag is not found, malformed xml");
        }
        if (!parser.getName().equals("ac")) {
            throw new IllegalStateException("Action tag is not closed!");
        }
        if (actionSpanable.text.isEmpty()) {
            throw new IllegalStateException("Action tag has no value");
        }
        actionSpanable.start = paragraph.text.length();
        paragraph.text = paragraph.text + actionSpanable.text;
        actionSpanable.end = paragraph.text.length();
        paragraph.spanables.add(actionSpanable);
    }

    @Override
    protected void readAttributes(XmlPullParser parser, ActionSpanable t) {
        t.audioTime = Float.parseFloat(parser.getAttributeValue(ns, "audio-time"));
    }
}
