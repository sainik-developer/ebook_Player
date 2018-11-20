package com.sixfingers.bp.configparser.xml.processor.text;

import android.util.Xml;

import com.sixfingers.bp.model.Paragraph;

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
public class ActionTagProcessorTest {

    private ActionTagProcessor actionTagProcessor;

    private final String VALID_ACTION_TAG = "<ac audio-time=\"0.598\">Nearly</ac>";
    private final String VALID_ACTION_WITH_SPACE = "<ac audio-time=\"0.598\"> Nearly  </ac>";
    private final String INVALID_NO_AUDIO_TIME_VALUE_ACTION_TAG = "<ac audio-time=\"\">Nearly</ac>";
    private final String INVALID_NO_AUDIO_TIME_ACTION_TAG = "<ac >Nearly</ac>";
    private final String INVALID_TAG_CLOSE_ACTION_TAG = "<ac audio-time=\"0.598\">Nearly/ac>";
    private final String INVALID_TAG_NOT_CLOSED = "<ac audio-time=\"0.598\"> Nearly";
    private final String INVALID_NO_VALUE_TAG = "<ac audio-time=\"0.598\"></ac>";

    private void common(final String str, final Paragraph paragraph) throws XmlPullParserException, IOException {
        XmlPullParser parser = Xml.newPullParser();

        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        InputStream ip = new ByteArrayInputStream(str.getBytes(Charset.forName("UTF-8")));
        parser.setInput(ip, "UTF-8");
        parser.nextTag();
        actionTagProcessor.read(parser, paragraph);
    }

    @Before
    public void prepare() {
        actionTagProcessor = new ActionTagProcessor();
    }


    @Test
    public void testChildTagsAndName() {
        assertTrue(actionTagProcessor.childTags().length == 0);
        assertTrue(actionTagProcessor.name().equals("ac"));
    }

    @Test
    public void testReadValid() throws XmlPullParserException, IOException {
        Paragraph paragraph = new Paragraph();
        common(VALID_ACTION_TAG, paragraph);
        assertEquals("Nearly", paragraph.text);
        assertTrue(paragraph.getActionSpanables().size() == 1);
        assertEquals("Nearly", paragraph.getActionSpanables().get(0).text);
        assertEquals(0.598f, paragraph.getActionSpanables().get(0).audioTime, 0);

    }

    @Test
    public void testReadWithSpaceValid() throws XmlPullParserException, IOException {
        Paragraph paragraph = new Paragraph();
        common(VALID_ACTION_WITH_SPACE, paragraph);
        assertEquals(" Nearly  ", paragraph.text);
        assertTrue(paragraph.getActionSpanables().size() == 1);
        assertEquals(" Nearly  ", paragraph.getActionSpanables().get(0).text);
        assertEquals(0.598f, paragraph.getActionSpanables().get(0).audioTime, 0);
    }

    @Test(expected = NumberFormatException.class)
    public void testReadInvalidWithNoAudioTimeValue() throws XmlPullParserException, IOException {
        Paragraph paragraph = new Paragraph();
        common(INVALID_NO_AUDIO_TIME_VALUE_ACTION_TAG, paragraph);
    }

    @Test(expected = NullPointerException.class)
    public void testReadInvalidWithNoAudioTimeTag() throws XmlPullParserException, IOException {
        Paragraph paragraph = new Paragraph();
        common(INVALID_NO_AUDIO_TIME_ACTION_TAG, paragraph);
    }

    @Test(expected = IllegalStateException.class)
    public void testReadInvalidTagClose1() throws XmlPullParserException, IOException {
        Paragraph paragraph = new Paragraph();
        common(INVALID_TAG_CLOSE_ACTION_TAG, paragraph);
    }

    @Test(expected = IllegalStateException.class)
    public void testReadInvalidTagClose2() throws XmlPullParserException, IOException {
        Paragraph paragraph = new Paragraph();
        common(INVALID_TAG_NOT_CLOSED, paragraph);
    }

    @Test(expected = IllegalStateException.class)
    public void testReadInvalidValeu() throws XmlPullParserException, IOException {
        Paragraph paragraph = new Paragraph();
        common(INVALID_NO_VALUE_TAG, paragraph);
    }

}
