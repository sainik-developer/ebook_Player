package com.sixfingers.bp.configparser.xml.processor.text;

import android.util.Xml;

import com.sixfingers.bp.model.Paragraph;
import com.sixfingers.bp.model.Text;
import com.sixfingers.bp.model.TextStyleSpanable;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
public class ParagraphTagProcessorTest {

    private ParagraphTagProcessor paragraphTagProcessor;

    private final String VALID_PG_TAG = "<p ls=\"5\" hi=\"6\" li=\"1\"><sh c=\"00556677\"><ac audio-time=\"0.598\">Nearly</ac></sh>.</p>";
    private final String VALID_PG_WITH_SPACE = "<p><sh c=\"00556677\"> <ac audio-time=\"0.598\">Nearly </ac> </sh></p>";
    private final String VALID_PG_MULTIPLE_ACTION_TAG = "<p> <ac audio-time=\"0.598\">Nearly </ac> <ac audio-time=\"0.998\">Nearly </ac> </p>";
    private final String VALID_PG_WITH_BOLD_TAG = "<p ls=\"5\" hi=\"6\"> <b> <ac audio-time=\"0.598\">Nearly </ac> <ac audio-time=\"0.998\">Nearly </ac> </b> </p>";
    private final String VALID_PG_WITH_ITALIC_TAG = "<p hi=\"6\" li=\"1\"> <i> <ac audio-time=\"0.598\">Nearly </ac> <ac audio-time=\"0.998\">Nearly </ac> </i> </p>";
    private final String INVALID_PG_NOT_COLSED = "<p> <i> <ac audio-time=\"0.598\">Nearly </ac> <ac audio-time=\"0.998\">Nearly </ac> </i>   <p>";


    @Before
    public void prepare() {
        paragraphTagProcessor = new ParagraphTagProcessor();
    }

    private void common(final String str, final Text text) throws XmlPullParserException, IOException {
        XmlPullParser parser = Xml.newPullParser();
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        InputStream ip = new ByteArrayInputStream(str.getBytes(Charset.forName("UTF-8")));
        parser.setInput(ip, "UTF-8");
        parser.nextTag();
        paragraphTagProcessor.read(parser, text);
    }

    @Test
    public void validPTest() throws XmlPullParserException, IOException {
        final Text text = new Text();
        common(VALID_PG_TAG, text);
        Paragraph paragraph = text.paragraphs.get(0);
        assertEquals(5, paragraph.lineSpacing);
        assertEquals(6, paragraph.headIndent);
        assertEquals(1, paragraph.lineIndent);
        assertEquals("Nearly.", paragraph.text);
        assertEquals(0.598f, paragraph.getActionSpanables().get(0).audioTime, 0f);
        assertEquals(0, paragraph.getActionSpanables().get(0).start);
        assertEquals(6, paragraph.getActionSpanables().get(0).end);
    }

    @Test
    public void validPWithSpace() throws XmlPullParserException, IOException {
        final Text text = new Text();
        common(VALID_PG_WITH_SPACE, text);
        Paragraph paragraph = text.paragraphs.get(0);
        assertEquals(" Nearly  ", paragraph.text);
        assertEquals(0.598f, paragraph.getActionSpanables().get(0).audioTime, 0f);
        assertEquals(1, paragraph.getActionSpanables().get(0).start);
        assertEquals(8, paragraph.getActionSpanables().get(0).end);
    }

    @Test
    public void validPWithMultipleActionTags() throws XmlPullParserException, IOException {
        final Text text = new Text();
        common(VALID_PG_MULTIPLE_ACTION_TAG, text);
        Paragraph paragraph = text.paragraphs.get(0);
        assertEquals(0.598f, paragraph.getActionSpanables().get(0).audioTime, 0f);
        assertEquals(1, paragraph.getActionSpanables().get(0).start);
        assertEquals(8, paragraph.getActionSpanables().get(0).end);
        assertEquals(9, paragraph.getActionSpanables().get(1).start);
        assertEquals(16, paragraph.getActionSpanables().get(1).end);
    }

    @Test
    public void validPWithBoldTag() throws XmlPullParserException, IOException {
        final Text text = new Text();
        common(VALID_PG_WITH_BOLD_TAG, text);
        Paragraph paragraph = text.paragraphs.get(0);
        assertEquals(0.598f, paragraph.getActionSpanables().get(0).audioTime, 0f);
        assertEquals(2, paragraph.getActionSpanables().get(0).start);
        assertEquals(9, paragraph.getActionSpanables().get(0).end);
        assertEquals(10, paragraph.getActionSpanables().get(1).start);
        assertEquals(17, paragraph.getActionSpanables().get(1).end);
        assertEquals(1, paragraph.getTextStyles(TextStyleSpanable.Type.BOLD).get(0).start);
        assertEquals(18, paragraph.getTextStyles(TextStyleSpanable.Type.BOLD).get(0).end);
    }

    @Test
    public void validPWithItalicTag() throws XmlPullParserException, IOException {
        final Text text = new Text();
        common(VALID_PG_WITH_ITALIC_TAG, text);
        Paragraph paragraph = text.paragraphs.get(0);
        assertEquals(0.598f, paragraph.getActionSpanables().get(0).audioTime, 0f);
        assertEquals(2, paragraph.getActionSpanables().get(0).start);
        assertEquals(9, paragraph.getActionSpanables().get(0).end);
        assertEquals(10, paragraph.getActionSpanables().get(1).start);
        assertEquals(17, paragraph.getActionSpanables().get(1).end);
        assertEquals(1, paragraph.getTextStyles(TextStyleSpanable.Type.ITALIC).get(0).start);
        assertEquals(18, paragraph.getTextStyles(TextStyleSpanable.Type.ITALIC).get(0).end);

    }

    @Test(expected = XmlPullParserException.class)
    public void invalidPWIthoutClose() throws XmlPullParserException, IOException {
        final Text text = new Text();
        common(INVALID_PG_NOT_COLSED, text);
    }

}
