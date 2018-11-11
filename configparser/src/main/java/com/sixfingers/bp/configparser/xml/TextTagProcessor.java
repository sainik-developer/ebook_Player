package com.sixfingers.bp.configparser.xml;

import com.sixfingers.bp.model.Text;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by sainik on 11.11.18.
 */

public class TextTagProcessor extends TagProcessor<Text> {

    @Override
    String[] childTags() {
        return new String[]{"p", "ac", "b", "i", "sh", "bg"};
    }

    @Override
    String name() {
        return "Text";
    }

    @Override
    Text read(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        return null;
    }
}
