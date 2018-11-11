package com.sixfingers.bp.configparser.xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by sainik on 11.11.18.
 */
public abstract class TagProcessor<T> {

    /***
     *
     * @return
     */
    abstract String[] childTags();

    /***
     *
     * @return
     */
    abstract String name();

    /***
     *
     * @param xmlPullParser
     * @return
     * @throws XmlPullParserException
     * @throws IOException
     */
    abstract T read(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException;
}
