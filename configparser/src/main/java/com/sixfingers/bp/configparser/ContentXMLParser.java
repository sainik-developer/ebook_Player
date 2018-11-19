package com.sixfingers.bp.configparser;

import android.text.Html;
import android.text.Spanned;
import android.util.Xml;

import com.sixfingers.bp.configparser.xml.processor.TagEnum;
import com.sixfingers.bp.configparser.xml.processor.TagProcessor;
import com.sixfingers.bp.model.Audio;
import com.sixfingers.bp.model.Content;
import com.sixfingers.bp.model.Text;

import org.xmlpull.v1.XmlPullParser;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * Created by sainik on 09.11.18.
 */

public class ContentXMLParser implements Parser<String, Content> {


    @Override
    public Content parser(final String s) {
        // public static Spanned fromHtml (String source) to unescape
        // TODO test this code
        final Spanned unescapedString = Html.fromHtml(s);
        String s1 = unescapedString.toString();
        XmlPullParser parser = Xml.newPullParser();
        try {
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            InputStream ip = new ByteArrayInputStream(s1.getBytes(Charset.forName("UTF-8")));
            parser.setInput(ip, "UTF-8");
            parser.nextTag();
            if (parser.getName().equals("Text")) {
                Text text = new Text();
                TagProcessor<Text, Text> tt = TagEnum.TEXT.getProcessor();
                tt.read(parser, text);
                return text;
            } else if (parser.getName().equals("Audio")) {
                Audio audio = new Audio();
                TagProcessor<Audio, Audio> audioProcessor = TagEnum.AUDIO.getProcessor();
                audioProcessor.read(parser, audio);
                return audio;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("");
        }
    }
}
