package com.sixfingers.bp.configparser.xml;

import com.sixfingers.bp.model.TextStyleSpanable;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by sainik on 12.11.18.
 */
public class TextBackGroundTagProcessor extends TagProcessor<TextStyleSpanable> {
    @Override
    public String[] childTags() {
        return new String[]{"ac"};
    }

    @Override
    public String name() {
        return "bg";
    }

    @Override
    public TextStyleSpanable read(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        return null;
    }

    @Override
    protected void readAttributes(XmlPullParser parser, TextStyleSpanable textStyleSpanable) {

    }
}
