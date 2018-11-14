package com.sixfingers.bp.configparser.xml;

import com.sixfingers.bp.model.Paragraph;
import com.sixfingers.bp.model.Text;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by sainik on 11.11.18.
 */
public class ParagraphTagProcessor extends TagProcessor<Paragraph> {

    @Override
    public String[] childTags() {
        return new String[]{"ac", "b", "i", "sh", "bg"};
    }

    @Override
    public String name() {
        return "p";
    }

    @Override
    public Paragraph read(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {

        return null;
    }

    @Override
    protected void readAttributes(XmlPullParser parser, Paragraph paragraph) {

        return;
    }
}
