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
        text.fontName = getAttributeValue(parser, ns, "font-name", Text.DEFAULT_FONT_NAME);
        text.fontNumber = Integer.valueOf(getAttributeValue(parser, ns, "fontNumber", Text.DEFAULT_FONT_NUMBER));
        text.textAlign = TextAlign.valueOf(getAttributeValue(parser, ns, "textAlign", Text.DEFAULT_FONT_ALIGN));
        text.border = Border.valueOf(getAttributeValue(parser, ns, "border", Text.DEFAULT_BORDER));
        text.backGroudColor = Integer.parseInt(getAttributeValue(parser, ns, "bg-color", Text.DEFAULT_BG_COLOR));
        text.position = new Position(parser.getAttributeValue(ns, "position"));
        text.textColor = Integer.parseInt(getAttributeValue(parser, ns, "text-color", Text.DEFAULT_TEXT_COLOR));
        text.angle = Integer.parseInt(getAttributeValue(parser, ns, "angle", Text.DEFAULT_ANGLE));
        text.actionEnable = Boolean.parseBoolean(getAttributeValue(parser, ns, "action-enable", Text.DEFAULT_ACTION_ENABLE));
        text.audioRes = parser.getAttributeValue(ns, "audio-res");
    }
}
