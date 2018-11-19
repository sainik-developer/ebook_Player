package com.sixfingers.bp.configparser.xml.processor.audio;

import com.sixfingers.bp.configparser.xml.processor.TagProcessor;
import com.sixfingers.bp.model.Audio;
import com.sixfingers.bp.model.Page;
import com.sixfingers.bp.model.Position;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by sainik on 18.11.18.
 */

public class AudioTagProcessor extends TagProcessor<Audio, Audio> {
    @Override
    public String[] childTags() {
        return new String[0];
    }

    @Override
    public String name() {
        return "Audio";
    }

    @Override
    public void read(XmlPullParser parser, Audio audio) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "Audio");
        readAttributes(parser, audio);
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
        }
    }

    @Override
    protected void readAttributes(XmlPullParser parser, Audio t) {
        t.position = new Position(parser.getAttributeValue(ns, "position"));
    }
}
