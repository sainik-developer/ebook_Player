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
        return new String[]{"p"};
    }

    @Override
    String name() {
        return "Text";
    }

    @Override
    Text read(XmlPullParser parser) throws XmlPullParserException, IOException {

        parser.require(XmlPullParser.START_TAG, ns, "Text");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();

            TagProcessor<?> tagProcessor = getTagProcessorByName(name);
            if (tagProcessor != null) {
                tagProcessor.read(parser);
            } else {
                skip(parser);
            }
        }

        return null;
    }
}
