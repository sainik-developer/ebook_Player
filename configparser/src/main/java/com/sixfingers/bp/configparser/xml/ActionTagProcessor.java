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
    String[] childTags() {
        return new String[0];
    }

    @Override
    String name() {
        return "ac";
    }

    @Override
    ActionSpanable read(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        return null;
    }
}
