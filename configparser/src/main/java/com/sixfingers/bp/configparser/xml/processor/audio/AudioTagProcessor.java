package com.sixfingers.bp.configparser.xml.processor.audio;

import com.sixfingers.bp.configparser.xml.processor.TagProcessor;
import com.sixfingers.bp.model.Audio;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by sainik on 18.11.18.
 */

public class AudioTagProcessor extends TagProcessor<Void, Audio>{
    @Override
    public String[] childTags() {
        return new String[0];
    }

    @Override
    public String name() {
        return null;
    }

    @Override
    public void read(XmlPullParser xmlPullParser, Void aVoid) throws XmlPullParserException, IOException {

    }

    @Override
    protected void readAttributes(XmlPullParser parser, Audio t) {

    }
}
