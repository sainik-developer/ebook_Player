package com.sixfingers.bp.configparser.xml;

import com.sixfingers.bp.model.ActionSpanable;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by sainik on 12.11.18.
 */
public class ActionTagProcessor extends TagProcessor<ActionSpanable> {
    @Override
    public String[] childTags() {
        return new String[0];
    }

    @Override
    public String name() {
        return "ac";
    }

    @Override
    public ActionSpanable read(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        return null;
    }

    @Override
    protected void readAttributes(XmlPullParser parser, ActionSpanable actionSpanable) {
        
    }
}
