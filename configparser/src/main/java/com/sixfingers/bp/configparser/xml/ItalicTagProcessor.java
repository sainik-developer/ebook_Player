package com.sixfingers.bp.configparser.xml;

import com.sixfingers.bp.model.TextStyleSpanable;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by sainik on 12.11.18.
 */
public class ItalicTagProcessor extends TagProcessor<TextStyleSpanable> {
    @Override
    String[] childTags() {
        return new String[]{"ac"};
    }

    @Override
    String name() {
        return null;
    }

    @Override
    TextStyleSpanable read(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        return null;
    }
}
