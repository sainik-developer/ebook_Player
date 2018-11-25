package com.sixfingers.bp.configparser.xml.processor.text;

import android.util.Xml;

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
public class ItalicTagProcessorTest {

    private ItalicTagProcessor italicTagProcessor;

    private final String VALID_ITALIC_TAG = "<i><ac audio-time=\"0.598\">Nearly</ac></i>";
    private final String VALID_ITALIC_TAG_WITH_SPACE = "<i>  <ac audio-time=\"0.598\">Nearly</ac>   </i>";
    private final String VALID_MULTI_ACTION_TAG_WITH_SPACE = "<i>  <ac audio-time=\"0.598\">Nearly</ac>  <ac audio-time=\"0.898\">Nearly</ac> </i>";
    private final String INVALID_MULTI_ACTION_TAG_NOT_CLOSED = "<i>  <ac audio-time=\"0.598\">Nearly  <ac audio-time=\"0.898\">Nearly</ac> </i>";
    private final String INVALID_MULTI_ACTION_TAG_NOT_CLOSED_ITALIC = "<i>  <ac audio-time=\"0.598\">Nearly  <ac audio-time=\"0.898\">Nearly</ac> <i>";
    private final String ITALIC_WITHOUT_ACTION_TAG = "<i>  Nearly  Nearly <i>";
    // TODO add with chines language chars


    private void common(final String str, final Paragraph paragraph) throws XmlPullParserException, IOException {
        XmlPullParser parser = Xml.newPullParser();

        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        InputStream ip = new ByteArrayInputStream(str.getBytes(Charset.forName("UTF-8")));
        parser.setInput(ip, "UTF-8");
        parser.nextTag();
        italicTagProcessor.read(parser, paragraph);
    }


    @Before
    public void prepare() {
        italicTagProcessor = new ItalicTagProcessor();
    }


    @Test
    public void testChildTagsAndName() {
        assertTrue(italicTagProcessor.childTags().length == 2);
        assertTrue(italicTagProcessor.name().equals("i"));
        assertTrue(italicTagProcessor.childTags()[0].equals("ac"));
        assertTrue(italicTagProcessor.childTags()[1].equals("b"));
    }

    @Test
    public void testReadValid() throws XmlPullParserException, IOException {
        Paragraph paragraph = new Paragraph();
        common(VALID_ITALIC_TAG, paragraph);
        assertEquals(0, paragraph.getTextStyles(TextStyleSpanable.Type.ITALIC).get(0).start);
        assertEquals(6, paragraph.getTextStyles(TextStyleSpanable.Type.ITALIC).get(0).end);
    }

    @Test
    public void testBoldWithSpace() throws XmlPullParserException, IOException {
        Paragraph paragraph = new Paragraph();
        common(VALID_ITALIC_TAG_WITH_SPACE, paragraph);
        assertEquals(0, paragraph.getTextStyles(TextStyleSpanable.Type.ITALIC).get(0).start);
        assertEquals(11, paragraph.getTextStyles(TextStyleSpanable.Type.ITALIC).get(0).end);
    }

    @Test
    public void testMultiActionTagValid() throws XmlPullParserException, IOException {
        Paragraph paragraph = new Paragraph();
        common(VALID_MULTI_ACTION_TAG_WITH_SPACE, paragraph);
        assertEquals(0, paragraph.getTextStyles(TextStyleSpanable.Type.ITALIC).get(0).start);
        assertEquals(17, paragraph.getTextStyles(TextStyleSpanable.Type.ITALIC).get(0).end);
    }

    @Test(expected = IllegalStateException.class)
    public void testInvalidActionAtgNotClosed() throws XmlPullParserException, IOException {
        Paragraph paragraph = new Paragraph();
        common(INVALID_MULTI_ACTION_TAG_NOT_CLOSED, paragraph);
    }

    @Test(expected = IllegalStateException.class)
    public void testInvalidBoldNotClosed() throws XmlPullParserException, IOException {
        Paragraph paragraph = new Paragraph();
        common(INVALID_MULTI_ACTION_TAG_NOT_CLOSED_ITALIC, paragraph);

    }

    @Test(expected = XmlPullParserException.class)
    public void testValidBoldWithoutAction() throws XmlPullParserException, IOException {
        Paragraph paragraph = new Paragraph();
        common(ITALIC_WITHOUT_ACTION_TAG, paragraph);

    }
}
