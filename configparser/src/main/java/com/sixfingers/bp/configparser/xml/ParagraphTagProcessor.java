package com.sixfingers.bp.configparser.xml;

import com.sixfingers.bp.model.Paragraph;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by sainik on 11.11.18.
 */
public class ParagraphTagProcessor extends TagProcessor<Paragraph> {

    @Override
    String[] childTags() {
        return new String[]{"ac", "b", "i", "sh", "bg"};
    }

    @Override
    String name() {
        return "p";
    }

    @Override
    Paragraph read(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        return null;
    }
}
