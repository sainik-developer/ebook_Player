package com.sixfingers.bp.configparser.xml;

import android.util.Pair;

import com.sixfingers.bp.model.TextStyleSpanable;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by sainik on 12.11.18.
 */
public class ShadowTagProcessor extends TagProcessor<Pair<TextStyleSpanable, String>> {

    @Override
    public String[] childTags() {
        return new String[]{"ac"};
    }

    @Override
    public String name() {
        return "sh";
    }

    @Override
    public Pair<TextStyleSpanable, String> read(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        return null;
    }

    @Override
    protected void readAttributes(XmlPullParser parser, Pair<TextStyleSpanable, String> textStyleSpanable) {

    }
}
