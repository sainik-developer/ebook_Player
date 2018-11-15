package com.sixfingers.bp.configparser.xml;

import com.sixfingers.bp.model.ActionSpanable;
import com.sixfingers.bp.model.Paragraph;
import com.sixfingers.bp.model.TextStyleSpanable;

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
    public Paragraph read(XmlPullParser parser) throws XmlPullParserException, IOException {
        Paragraph paragraph = new Paragraph();
        parser.require(XmlPullParser.START_TAG, ns, "p");
        readAttributes(parser, paragraph);
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("ac")) {
                TagProcessor<ActionSpanable> actionSpanableTagProcessor = TagEnum.ACTION.getProcessor();
                ActionSpanable actionSpanable = actionSpanableTagProcessor.read(parser);
                paragraph.spanables.add(actionSpanable);
            } else if (name.equals("b")) {
                TagProcessor<TextStyleSpanable> boldTagProcessor = TagEnum.BOLD.getProcessor();
                TextStyleSpanable bold = boldTagProcessor.read(parser);
                paragraph.spanables.add(bold);
            } else if (name.equals("i")) {
                TagProcessor<TextStyleSpanable> italicTagProcessor = TagEnum.ITALIC.getProcessor();
                TextStyleSpanable italic = italicTagProcessor.read(parser);
                paragraph.spanables.add(italic);
            } else if (name.equals("sh")) {
                TagProcessor<TextStyleSpanable> shadowTagProcessor = TagEnum.SHADOW.getProcessor();
                TextStyleSpanable shadow = shadowTagProcessor.read(parser);
                paragraph.spanables.add(shadow);
            } else if (name.equals("bg")) {
                TagProcessor<TextStyleSpanable> backgroundTagProcessor = TagEnum.TEXT_BACK_GROUND.getProcessor();
                TextStyleSpanable bgColor = backgroundTagProcessor.read(parser);
                paragraph.spanables.add(bgColor);
            }
        }
        return null;
    }

    @Override
    protected void readAttributes(XmlPullParser parser, Paragraph paragraph) {
        paragraph.lineSpacing = Integer.parseInt(parser.getAttributeValue(ns, "ls"));
        paragraph.headIndent = Integer.parseInt(parser.getAttributeValue(ns, "hi"));
        paragraph.lineIndent = Integer.parseInt(parser.getAttributeValue(ns, "li"));

    }
}
