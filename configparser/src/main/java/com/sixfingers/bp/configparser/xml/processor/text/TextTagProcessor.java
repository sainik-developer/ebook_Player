package com.sixfingers.bp.configparser.xml.processor.text;

import com.sixfingers.bp.configparser.xml.processor.TagEnum;
import com.sixfingers.bp.configparser.xml.processor.TagProcessor;
import com.sixfingers.bp.model.Paragraph;
import com.sixfingers.bp.model.Position;
import com.sixfingers.bp.model.Text;
import com.sixfingers.bp.model.enums.Border;
import com.sixfingers.bp.model.enums.TextAlign;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by sainik on 11.11.18.
 */
public class TextTagProcessor extends TagProcessor<Text, Text> {

    @Override
    public String[] childTags() {
        return new String[]{"p"};
    }

    @Override
    public String name() {
        return "Text";
    }

    @Override
    public void read(XmlPullParser parser, Text text) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "Text");
        readAttributes(parser, text);
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("p")) {
                TagProcessor<Text, Paragraph> paragraphTagProcessor = TagEnum.PARAGRAPH.getProcessor();
                paragraphTagProcessor.read(parser, text);
            } else {
                skip(parser);
            }
        }
        if (!parser.getName().equals("Text")) {
            throw new IllegalStateException("Text tag is not closed!");
        }
    }

    @Override
    protected void readAttributes(final XmlPullParser parser, final Text text) {
        text.fontName = parser.getAttributeValue(ns, "font-name");
        text.fontNumber = Integer.valueOf(parser.getAttributeValue(ns, "fontNumber"));
        text.textAlign = TextAlign.valueOf(parser.getAttributeValue(ns, "textAlign"));
        text.border = Border.valueOf(parser.getAttributeValue(ns, "border"));
        text.backGroudColor = parser.getAttributeValue(ns, "bg-color");
        text.position = new Position(parser.getAttributeValue(ns, "position"));
        text.textColor = parser.getAttributeValue(ns, "text-color");
        text.angle = Integer.parseInt(parser.getAttributeValue(ns, "angle"));
        text.highlightTextOnAudio = Boolean.parseBoolean(parser.getAttributeValue(ns, "highlightTextOnAudio"));
        text.actionEnable = Boolean.parseBoolean(parser.getAttributeValue(ns, "action-enable"));
        text.audioRes = parser.getAttributeValue(ns, "audio-res");
    }
}