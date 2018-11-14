package com.sixfingers.bp.configparser.xml;

import com.sixfingers.bp.model.Text;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by sainik on 11.11.18.
 */
public abstract class TagProcessor<T> {

    // We don't use namespaces
    public static final String ns = null;

    /***
     *
     * @return
     */
    public abstract String[] childTags();

    /***
     *
     * @return
     */
    public abstract String name();

    /***
     *
     * @param xmlPullParser
     * @return
     * @throws XmlPullParserException
     * @throws IOException
     */
    public abstract T read(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException;

    protected abstract void readAttributes(final XmlPullParser parser, final T t);


    protected void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }


}
