package com.sixfingers.bp.configparser.xml.processor.text;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class ParagraphTagProcessorTest {

    private ParagraphTagProcessor paragraphTagProcessor;

    private final String VALID_PG_TAG = "<p ls=\"5\" hi=\"6\" li=\"1\"> <sh c=\"00556677\"> <ac audio-time=\"0.598\">Nearly</ac> </sh>.</p>";
    private final String VALID_PG_WITH_SPACE = "<p><sh c=\"00556677\"> <ac audio-time=\"0.598\">Nearly </ac> </sh></p>";
    private final String VALID_PG_MULTIPLE_ACTION_TAG = "<p> <ac audio-time=\"0.598\">Nearly </ac> <ac audio-time=\"0.998\">Nearly </ac> </p>";
    private final String VALID_PG_WITH_BOLD_TAG = "<p ls=\"5\" hi=\"6\"> <b> <ac audio-time=\"0.598\">Nearly </ac> <ac audio-time=\"0.998\">Nearly </ac> </b> </p>";
    private final String VALID_PG_WITH_ITALIC_TAG = "<p hi=\"6\" li=\"1\"> <i> <ac audio-time=\"0.598\">Nearly </ac> <ac audio-time=\"0.998\">Nearly </ac> </i> </p>";
    private final String INVALID_PG_NOT_COLSED = "<p> <i> <ac audio-time=\"0.598\">Nearly </ac> <ac audio-time=\"0.998\">Nearly </ac> </i> <p>";


    @Before
    public void prepare() {
        paragraphTagProcessor = new ParagraphTagProcessor();
    }


}
