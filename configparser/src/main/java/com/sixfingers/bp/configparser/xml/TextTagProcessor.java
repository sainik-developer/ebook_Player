package com.sixfingers.bp.configparser.xml;

import com.sixfingers.bp.model.Paragraph;
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

        Text text = new Text();
        parser.require(XmlPullParser.START_TAG, ns, "Text");
        readAttributes(parser, text);
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("p")) {
                TagProcessor<Paragraph> paragraphTagProcessor = TagEnum.PARAGRAPH.getProcessorByText();
                Paragraph paragraph = paragraphTagProcessor.read(parser);
                text.paragraphs.add(paragraph);
            } else {
                skip(parser);
            }
        }

        return text;
    }


    private void readAttributes(final XmlPullParser parser, final Text text) {
        parser.getAttributeValue(ns,"font-name");
        parser.getAttributeValue(ns,"fontNumber");

    }
}
