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
    // TODO add with chines


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
        assertTrue(italicTagProcessor.childTags().length == 1);
        assertTrue(italicTagProcessor.name().equals("i"));
        assertTrue(italicTagProcessor.childTags()[0].equals("ac"));
    }
}
