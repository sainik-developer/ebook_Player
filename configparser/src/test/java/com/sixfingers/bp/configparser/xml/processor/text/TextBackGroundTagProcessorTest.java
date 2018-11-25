package com.sixfingers.bp.configparser.xml.processor.text;

import android.util.Xml;

import com.sixfingers.bp.model.ActionSpanable;
import com.sixfingers.bp.model.Paragraph;
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
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
public class TextBackGroundTagProcessorTest {

    private TextBackGroundTagProcessor textBackGroundTagProcessor;

    private final String VALID_BG_TAG = "<bg c=\"00556677\"><ac audio-time=\"0.598\">Nearly</ac></bg>";
    private final String VALID_BG_WITH_SPACE = "<bg c=\"00556677\"> <ac audio-time=\"0.598\">Nearly </ac> </bg>";
    private final String VALID_BG_WITH_MULTIPLE_ACTION_TAG = "<bg c=\"00556677\"> <ac audio-time=\"0.598\">Nearly </ac> <ac audio-time=\"0.998\">Nearly </ac> </bg>";
    private final String VALID_BG_WITH_BOLD_TAG = "<bg c=\"00556677\"> <b> <ac audio-time=\"0.598\">Nearly </ac> <ac audio-time=\"0.998\">Nearly </ac> </b> </bg>";
    private final String VALID_BG_WITH_ITALIC_TAG = "<bg c=\"00556677\"> <i> <ac audio-time=\"0.598\">Nearly </ac> <ac audio-time=\"0.998\">Nearly </ac> </i> </bg>";
    private final String VALID_BG_WITH_ITALIC_BOLD_TAG = "<bg c=\"00556677\"> <i> <ac audio-time=\"0.598\">Nearly </ac></i> <b><ac audio-time=\"0.998\">Nearly </ac> </b> </bg>";
    private final String INVALID_BG_NOT_COLSED = "<bg c=\"00556677\"> <i> <ac audio-time=\"0.598\">Nearly </ac> <ac audio-time=\"0.998\">Nearly </ac> </i> <bg>";
    private final String INVALID_BG_NO_COLOR = "<bg> <i> <ac audio-time=\"0.598\">Nearly </ac> <ac audio-time=\"0.998\">Nearly </ac> </i> </bg>";


    private void common(final String str, final Paragraph paragraph) throws XmlPullParserException, IOException {
        XmlPullParser parser = Xml.newPullParser();
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        InputStream ip = new ByteArrayInputStream(str.getBytes(Charset.forName("UTF-8")));
        parser.setInput(ip, "UTF-8");
        parser.nextTag();
        textBackGroundTagProcessor.read(parser, paragraph);
    }


    @Before
    public void prepare() {
        textBackGroundTagProcessor = new TextBackGroundTagProcessor();
    }


    @Test
    public void testSimpleValidShadownTag() throws XmlPullParserException, IOException {
        final Paragraph paragraph = new Paragraph();
        common(VALID_BG_TAG, paragraph);
        TextStyleSpanable shadowSpanable = paragraph.getTextStyles(TextStyleSpanable.Type.SHADOW).get(0);
        assertTrue(shadowSpanable.color == Integer.parseInt("00556677"));
        assertEquals(0, shadowSpanable.start);
        assertEquals(6, shadowSpanable.end);
    }

    @Test
    public void testSimpleValidShadowTagWithSpace() throws XmlPullParserException, IOException {
        final Paragraph paragraph = new Paragraph();
        common(VALID_BG_WITH_SPACE, paragraph);
        TextStyleSpanable shadowSpanable = paragraph.getTextStyles(TextStyleSpanable.Type.SHADOW).get(0);
        assertTrue(shadowSpanable.color == Integer.parseInt("00556677"));
        assertEquals(0, shadowSpanable.start);
        assertEquals(9, shadowSpanable.end);
    }

    @Test
    public void testShadowValidWithMultiActionTag() throws XmlPullParserException, IOException {
        final Paragraph paragraph = new Paragraph();
        common(VALID_BG_WITH_MULTIPLE_ACTION_TAG, paragraph);
        TextStyleSpanable shadowSpanable = paragraph.getTextStyles(TextStyleSpanable.Type.SHADOW).get(0);
        assertTrue(shadowSpanable.color == Integer.parseInt("00556677"));
        assertEquals(0, shadowSpanable.start);
        assertEquals(17, shadowSpanable.end);
        ActionSpanable action1 = paragraph.getActionSpanables().get(0);
        assertEquals(1, action1.start);
        assertEquals(8, action1.end);
        ActionSpanable action2 = paragraph.getActionSpanables().get(1);
        assertEquals(9, action2.start);
        assertEquals(16, action2.end);

    }

    @Test
    public void testShadownValidWithBoldTag() throws XmlPullParserException, IOException {
        final Paragraph paragraph = new Paragraph();
        common(VALID_BG_WITH_BOLD_TAG, paragraph);
        TextStyleSpanable shadowSpanable = paragraph.getTextStyles(TextStyleSpanable.Type.SHADOW).get(0);
        assertTrue(shadowSpanable.color == Integer.parseInt("00556677"));
        assertEquals(0, shadowSpanable.start);
        assertEquals(19, shadowSpanable.end);
    }

    @Test
    public void testShadowValidWithItalicTag() throws XmlPullParserException, IOException {
        final Paragraph paragraph = new Paragraph();
        common(VALID_BG_WITH_ITALIC_TAG, paragraph);
        TextStyleSpanable shadowSpanable = paragraph.getTextStyles(TextStyleSpanable.Type.SHADOW).get(0);
        assertTrue(shadowSpanable.color == Integer.parseInt("00556677"));
        assertEquals(0, shadowSpanable.start);
        assertEquals(19, shadowSpanable.end);
        TextStyleSpanable italic1Spanable = paragraph.getTextStyles(TextStyleSpanable.Type.ITALIC).get(0);
        assertEquals(1, italic1Spanable.start);
        assertEquals(18, italic1Spanable.end);
    }

    @Test
    public void testShadowValidWithBoldAndItalicTag() throws XmlPullParserException, IOException {
        final Paragraph paragraph = new Paragraph();
        common(VALID_BG_WITH_ITALIC_BOLD_TAG, paragraph);
        TextStyleSpanable shadowSpanable = paragraph.getTextStyles(TextStyleSpanable.Type.SHADOW).get(0);
        assertTrue(shadowSpanable.color == Integer.parseInt("00556677"));
        assertEquals(0, shadowSpanable.start);
        assertEquals(19, shadowSpanable.end);
        TextStyleSpanable boldSpanable = paragraph.getTextStyles(TextStyleSpanable.Type.BOLD).get(0);
        assertEquals(10, boldSpanable.start);
        assertEquals(18, boldSpanable.end);
        TextStyleSpanable italicSpanable = paragraph.getTextStyles(TextStyleSpanable.Type.ITALIC).get(0);
        assertEquals(1, italicSpanable.start);
        assertEquals(9, italicSpanable.end);
    }

    @Test(expected = IllegalStateException.class)
    public void testShadowNotClosed() throws XmlPullParserException, IOException {
        final Paragraph paragraph = new Paragraph();
        common(INVALID_BG_NOT_COLSED, paragraph);
    }

    @Test(expected = NumberFormatException.class)
    public void testShowdDoesNotHaveColor() throws XmlPullParserException, IOException {
        final Paragraph paragraph = new Paragraph();
        common(INVALID_BG_NO_COLOR, paragraph);
    }
}
