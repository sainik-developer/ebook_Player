package com.sixfingers.bp.configparser.xml.processor.text;

import com.sixfingers.bp.configparser.xml.processor.TagEnum;
import com.sixfingers.bp.configparser.xml.processor.TagProcessor;
import com.sixfingers.bp.model.ActionSpanable;
import com.sixfingers.bp.model.Paragraph;
import com.sixfingers.bp.model.Text;
import com.sixfingers.bp.model.TextStyleSpanable;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by sainik on 11.11.18.
 */
public class ParagraphTagProcessor extends TagProcessor<Text, Paragraph> {

    @Override
    public String[] childTags() {
        return new String[]{"ac", "b", "i", "sh", "bg"};
    }

    @Override
    public String name() {
        return "p";
    }

    @Override
    public void read(XmlPullParser parser, Text text) throws XmlPullParserException, IOException {
        Paragraph paragraph = new Paragraph();
        parser.require(XmlPullParser.START_TAG, ns, "p");
        readAttributes(parser, paragraph);
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() == XmlPullParser.START_TAG) {
                String name = parser.getName();
                if (name.equals("ac")) {
                    TagProcessor<Paragraph, ActionSpanable> actionSpanableTagProcessor = TagEnum.ACTION.getProcessor();
                    actionSpanableTagProcessor.read(parser, paragraph);
                } else if (name.equals("b")) {
                    TagProcessor<Paragraph, TextStyleSpanable> boldTagProcessor = TagEnum.BOLD.getProcessor();
                    boldTagProcessor.read(parser, paragraph);
                } else if (name.equals("i")) {
                    TagProcessor<Paragraph, TextStyleSpanable> italicTagProcessor = TagEnum.ITALIC.getProcessor();
                    italicTagProcessor.read(parser, paragraph);
                } else if (name.equals("sh")) {
                    TagProcessor<Paragraph, TextStyleSpanable> shadowTagProcessor = TagEnum.SHADOW.getProcessor();
                    shadowTagProcessor.read(parser, paragraph);
                } else if (name.equals("bg")) {
                    TagProcessor<Paragraph, TextStyleSpanable> backgroundTagProcessor = TagEnum.TEXT_BACK_GROUND.getProcessor();
                    backgroundTagProcessor.read(parser, paragraph);
                } else {
                    throw new IllegalStateException("paragraph tag only can have 'ac','b','i,'sh' or 'bg''");
                }
            } else {
                paragraph.text = paragraph.text + parser.getText();
            }
        }
        if (!parser.getName().equals("p")) {
            throw new IllegalStateException("Paragraph tag is not closed!");
        }
        text.paragraphs.add(paragraph);
    }

    @Override
    protected void readAttributes(XmlPullParser parser, Paragraph paragraph) {
        paragraph.lineSpacing = Integer.parseInt(getAttributeValue(parser, ns, "ls", "5"));
        paragraph.headIndent = Integer.parseInt(getAttributeValue(parser, ns, "hi", "7"));
        paragraph.lineIndent = Integer.parseInt(getAttributeValue(parser, ns, "li", "6"));

    }
}
