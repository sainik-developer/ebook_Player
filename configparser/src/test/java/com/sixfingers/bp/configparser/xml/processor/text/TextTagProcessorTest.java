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

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class TextTagProcessorTest {

    private TextTagProcessor textTagProcessor;

    private final String VALID_TEXT_TAG = "<Text font-name=\"arial\" fontNumber=\"20\" textAlign=\"LEFT\" border=\"NONE\" bg-color=\"\" position=\"5%,5%,40%,30%\" text-color=\"000000\" angle=\"0\" action-enable=\"true\" audio-res=\"cao_chong/cao_chong_p1.mp3\" pg-sp=\"20\"><p ls=\"5\" hi=\"6\" li=\"1\"> <sh c=\"00556677\"> <ac audio-time=\"0.598\">Nearly</ac> </sh>.</p></Text>";

    private void common(final String str, final Text text) throws XmlPullParserException, IOException {
        XmlPullParser parser = Xml.newPullParser();
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        InputStream ip = new ByteArrayInputStream(str.getBytes(Charset.forName("UTF-8")));
        parser.setInput(ip, "UTF-8");
        parser.nextTag();
        textTagProcessor.read(parser, text);
    }


    @Before
    public void prepare() {
        textTagProcessor = new TextTagProcessor();
    }

    @Test
    public void testValid() throws XmlPullParserException, IOException {
        Text text = new Text();
        common(VALID_TEXT_TAG, text);
        assertTrue(text.paragraphs.size() == 1);
        assertEquals(10, text.paragraphs.get(0).text.length());
        assertEquals(1, text.paragraphs.get(0).getTextStyles(TextStyleSpanable.Type.SHADOW).get(0).start);
        assertEquals(9, text.paragraphs.get(0).getTextStyles(TextStyleSpanable.Type.SHADOW).get(0).end);

    }
}
