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
public class BoldTagProcessorTest {

    private BoldTagProcessor boldTagProcessor;

    private final String VALID_BOLD_TAG = "<b><ac audio-time=\"0.598\">Nearly</ac></b>";
    private final String VALID_BOLD_TAG_WITH_SPACE = "<b>  <ac audio-time=\"0.598\">Nearly</ac>   </b>";
    private final String VALID_MULTI_ACTION_TAG_WITH_SPACE = "<b>  <ac audio-time=\"0.598\">Nearly</ac>  <ac audio-time=\"0.898\">Nearly</ac> </b>";
    private final String INVALID_MULTI_ACTION_TAG_NOT_CLOSED = "<b>  <ac audio-time=\"0.598\">Nearly  <ac audio-time=\"0.898\">Nearly</ac> </b>";
    private final String INVALID_MULTI_ACTION_TAG_NOT_CLOSED_BOLD = "<b>  <ac audio-time=\"0.598\">Nearly  <ac audio-time=\"0.898\">Nearly</ac> <b>";
    private final String BOLD_WITHOUT_ACTION_TAG = "<b>  Nearly  Nearly <b>";
    // TODO add with chines

    private void common(final String str, final Paragraph paragraph) throws XmlPullParserException, IOException {
        XmlPullParser parser = Xml.newPullParser();

        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        InputStream ip = new ByteArrayInputStream(str.getBytes(Charset.forName("UTF-8")));
        parser.setInput(ip, "UTF-8");
        parser.nextTag();
        boldTagProcessor.read(parser, paragraph);
    }

    @Before
    public void prepare() {
        boldTagProcessor = new BoldTagProcessor();
    }

    @Test
    public void testChildTagsAndName() {
        assertTrue(boldTagProcessor.childTags().length == 2);
        assertTrue(boldTagProcessor.name().equals("b"));
        assertTrue(boldTagProcessor.childTags()[0].equals("ac"));
        assertTrue(boldTagProcessor.childTags()[1].equals("i"));
    }

    @Test
    public void testReadValid() throws XmlPullParserException, IOException {
        Paragraph paragraph = new Paragraph();
        common(VALID_BOLD_TAG, paragraph);
        assertEquals(0, paragraph.getTextStyles(TextStyleSpanable.Type.BOLD).get(0).start);
        assertEquals(6, paragraph.getTextStyles(TextStyleSpanable.Type.BOLD).get(0).end);
    }

    @Test
    public void testBoldWithSpace() throws XmlPullParserException, IOException {
        Paragraph paragraph = new Paragraph();
        common(VALID_BOLD_TAG_WITH_SPACE, paragraph);
        assertEquals(0, paragraph.getTextStyles(TextStyleSpanable.Type.BOLD).get(0).start);
        assertEquals(11, paragraph.getTextStyles(TextStyleSpanable.Type.BOLD).get(0).end);
    }

    @Test
    public void testMultiActionTagValid() throws XmlPullParserException, IOException {
        Paragraph paragraph = new Paragraph();
        common(VALID_MULTI_ACTION_TAG_WITH_SPACE, paragraph);
        assertEquals(0, paragraph.getTextStyles(TextStyleSpanable.Type.BOLD).get(0).start);
        assertEquals(17, paragraph.getTextStyles(TextStyleSpanable.Type.BOLD).get(0).end);
    }

    @Test(expected = IllegalStateException.class)
    public void testInvalidActionAtgNotClosed() throws XmlPullParserException, IOException {
        Paragraph paragraph = new Paragraph();
        common(INVALID_MULTI_ACTION_TAG_NOT_CLOSED, paragraph);
    }

    @Test(expected = IllegalStateException.class)
    public void testInvalidBoldNotClosed() throws XmlPullParserException, IOException {
        Paragraph paragraph = new Paragraph();
        common(INVALID_MULTI_ACTION_TAG_NOT_CLOSED_BOLD, paragraph);

    }

    @Test(expected = XmlPullParserException.class)
    public void testValidBoldWithoutAction() throws XmlPullParserException, IOException {
        Paragraph paragraph = new Paragraph();
        common(BOLD_WITHOUT_ACTION_TAG, paragraph);

    }

}
