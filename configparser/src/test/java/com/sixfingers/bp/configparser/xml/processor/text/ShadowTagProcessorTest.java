package com.sixfingers.bp.configparser.xml.processor.text;

import android.util.Xml;

import com.sixfingers.bp.model.Paragraph;
import com.sixfingers.bp.model.TextStyleSpanable;

import org.junit.Assert;
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

@RunWith(RobolectricTestRunner.class)
public class ShadowTagProcessorTest {

    private ShadowTagProcessor shadowTagProcessor;

    private final String VALID_SH_TAG = "<sh c=\"00556677\"><ac audio-time=\"0.598\">Nearly</ac></sh>";
    private final String VALID_SH_WITH_SPACE = "<sh c=\"00556677\"> <ac audio-time=\"0.598\">Nearly </ac> </sh>";
    private final String VALID_SH_MULTIPLE_ACTION_TAG = "<sh c=\"00556677\"> <ac audio-time=\"0.598\">Nearly </ac> <ac audio-time=\"0.998\">Nearly </ac> </sh>";
    private final String VALID_Sh_WITH_BOLD_TAG = "<sh c=\"00556677\"> <b> <ac audio-time=\"0.598\">Nearly </ac> <ac audio-time=\"0.998\">Nearly </ac> </b> </sh>";
    private final String VALID_SH_WITH_ITALIC_TAG = "<sh c=\"00556677\"> <i> <ac audio-time=\"0.598\">Nearly </ac> <ac audio-time=\"0.998\">Nearly </ac> </i> </sh>";
    private final String VALID_SH_WITH_ITALIC_BOLD_TAG = "<sh c=\"00556677\"> <b> <ac audio-time=\"0.598\">Nearly </ac></b> <i><ac audio-time=\"0.998\">Nearly </ac> </i> </sh>";
    private final String INVALID_SH_NOT_CLOSED = "<sh c=\"00556677\"> <i> <ac audio-time=\"0.598\">Nearly </ac> <ac audio-time=\"0.998\">Nearly </ac> </i> <sh>";
    private final String INVALID_SH_NO_COLOR = "<sh> <i> <ac audio-time=\"0.598\">Nearly </ac> <ac audio-time=\"0.998\">Nearly </ac> </i> </sh>";


    private void common(final String str, final Paragraph paragraph) throws XmlPullParserException, IOException {
        XmlPullParser parser = Xml.newPullParser();
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        InputStream ip = new ByteArrayInputStream(str.getBytes(Charset.forName("UTF-8")));
        parser.setInput(ip, "UTF-8");
        parser.nextTag();
        shadowTagProcessor.read(parser, paragraph);
    }


    @Before
    public void prepare() {
        shadowTagProcessor = new ShadowTagProcessor();
    }


    @Test
    public void testSimpleValidShadownTag() throws XmlPullParserException, IOException {
        final Paragraph paragraph = new Paragraph();
        common(VALID_SH_TAG, paragraph);
        TextStyleSpanable shadowSpanable = paragraph.getTextStyles(TextStyleSpanable.Type.SHADOW).get(0);
        Assert.assertTrue(shadowSpanable.color == Integer.parseInt("00556677"));
        Assert.assertEquals(0, shadowSpanable.start);
        Assert.assertEquals(6, shadowSpanable.end);
    }

    @Test
    public void testSimpleValidShadowTagWithSpace() throws XmlPullParserException, IOException {
        final Paragraph paragraph = new Paragraph();
        common(VALID_SH_WITH_SPACE, paragraph);
        TextStyleSpanable shadowSpanable = paragraph.getTextStyles(TextStyleSpanable.Type.SHADOW).get(0);
        Assert.assertTrue(shadowSpanable.color == Integer.parseInt("00556677"));
        Assert.assertEquals(0, shadowSpanable.start);
        Assert.assertEquals(8, shadowSpanable.end);
    }

    @Test
    public void testShadowValidWithMultiActionTag() throws XmlPullParserException, IOException {
        final Paragraph paragraph = new Paragraph();
        common(VALID_SH_MULTIPLE_ACTION_TAG, paragraph);
        TextStyleSpanable shadowSpanable = paragraph.getTextStyles(TextStyleSpanable.Type.SHADOW).get(0);
        Assert.assertTrue(shadowSpanable.color == Integer.parseInt("00556677"));
        Assert.assertEquals(0, shadowSpanable.start);
        Assert.assertEquals(17, shadowSpanable.end);
    }

    @Test
    public void testShadownValidWithBoldTag() throws XmlPullParserException, IOException {
        final Paragraph paragraph = new Paragraph();
        common(VALID_Sh_WITH_BOLD_TAG, paragraph);
        TextStyleSpanable shadowSpanable = paragraph.getTextStyles(TextStyleSpanable.Type.SHADOW).get(0);
        Assert.assertTrue(shadowSpanable.color == Integer.parseInt("00556677"));
        Assert.assertEquals(0, shadowSpanable.start);
        Assert.assertEquals(19, shadowSpanable.end);
    }

    @Test
    public void testShadowValidWithItalicTag() throws XmlPullParserException, IOException {
        final Paragraph paragraph = new Paragraph();
        common(VALID_SH_WITH_ITALIC_TAG, paragraph);
        TextStyleSpanable shadowSpanable = paragraph.getTextStyles(TextStyleSpanable.Type.SHADOW).get(0);
        Assert.assertTrue(shadowSpanable.color == Integer.parseInt("00556677"));
        Assert.assertEquals(0, shadowSpanable.start);
        Assert.assertEquals(19, shadowSpanable.end);
        TextStyleSpanable italic1Spanable = paragraph.getTextStyles(TextStyleSpanable.Type.ITALIC).get(0);
        Assert.assertEquals(0, italic1Spanable.start);
        Assert.assertEquals(19, italic1Spanable.end);
    }

    @Test
    public void testShadowValidWithBoldAndItalicTag() throws XmlPullParserException, IOException {
        final Paragraph paragraph = new Paragraph();
        common(VALID_SH_WITH_ITALIC_BOLD_TAG, paragraph);
        TextStyleSpanable shadowSpanable = paragraph.getTextStyles(TextStyleSpanable.Type.SHADOW).get(0);
        Assert.assertTrue(shadowSpanable.color == Integer.parseInt("00556677"));
        Assert.assertEquals(0, shadowSpanable.start);
        Assert.assertEquals(19, shadowSpanable.end);
        TextStyleSpanable boldSpanable = paragraph.getTextStyles(TextStyleSpanable.Type.BOLD).get(0);
        Assert.assertEquals(0, boldSpanable.start);
        Assert.assertEquals(19, boldSpanable.end);
        TextStyleSpanable italicSpanable = paragraph.getTextStyles(TextStyleSpanable.Type.ITALIC).get(0);
        Assert.assertEquals(0, italicSpanable.start);
        Assert.assertEquals(19, italicSpanable.end);
    }

    @Test(expected = IllegalStateException.class)
    public void testShadowNotClosed() throws XmlPullParserException, IOException {
        final Paragraph paragraph = new Paragraph();
        common(INVALID_SH_NOT_CLOSED, paragraph);
    }

    @Test(expected = NumberFormatException.class)
    public void testShowdDoesNotHaveColor() throws XmlPullParserException, IOException {
        final Paragraph paragraph = new Paragraph();
        common(INVALID_SH_NO_COLOR, paragraph);
    }

}
