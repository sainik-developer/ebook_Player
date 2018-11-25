package com.sixfingers.bp.configparser.xml.processor.audio;

import android.util.Xml;

import com.sixfingers.bp.configparser.xml.processor.text.TextTagProcessor;
import com.sixfingers.bp.model.Audio;
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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
public class AudioTagProcessorTest {

    private AudioTagProcessor audioTagProcessor;

    private final String VALID_TEXT_TAG = "<Audio position=\"30%,74%,8%,8%\" res=\"cao_chong/bunting-yellow.mp3\"></Audio>";

    private void common(final String str, final Audio audio) throws XmlPullParserException, IOException {
        XmlPullParser parser = Xml.newPullParser();
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        InputStream ip = new ByteArrayInputStream(str.getBytes(Charset.forName("UTF-8")));
        parser.setInput(ip, "UTF-8");
        parser.nextTag();
        audioTagProcessor.read(parser, audio);
    }


    @Before
    public void prepare() {
        audioTagProcessor = new AudioTagProcessor();
    }

    @Test
    public void testValid() throws XmlPullParserException, IOException {
        Audio audio = new Audio();
        common(VALID_TEXT_TAG, audio);
        assertNotNull(audio.res);
    }

}
