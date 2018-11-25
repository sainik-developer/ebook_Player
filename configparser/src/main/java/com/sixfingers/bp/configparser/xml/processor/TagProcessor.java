package com.sixfingers.bp.configparser.xml.processor;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by sainik on 11.11.18.
 */
public abstract class TagProcessor<T, K> {

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
    public abstract void read(XmlPullParser xmlPullParser, T t) throws XmlPullParserException, IOException;

    /***
     *
     * @param parser
     * @param t
     */
    protected abstract void readAttributes(final XmlPullParser parser, final K t);


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

    protected String getAttributeValue(XmlPullParser parser, String s, String s2, String defaultValue) {
        String s1 = parser.getAttributeValue(s, s2);
        return s1 == null || s1.isEmpty() ? defaultValue : s1;
    }


}
