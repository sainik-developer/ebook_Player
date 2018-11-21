package com.sixfingers.bp.configparser.xml.processor.text;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class TextBackGroundTagProcessorTest {

    private TextBackGroundTagProcessor textBackGroundTagProcessor;

    private final String VALID_TEXT_BACK_GROUND_TAG = "<bg c=\"00556677\"> <ac audio-time=\"0.598\">Nearly</ac> </bg>";
    private final String VALID_TEXT_BG_WITH_SPACE = "<bg c=\"00556677\"> <ac audio-time=\"0.598\">Nearly </ac> </bg>";
    private final String VALID_TEXT_BG_WITH_MULTIPLE_ACTION_TAG = "<bg c=\"00556677\"> <ac audio-time=\"0.598\">Nearly </ac> <ac audio-time=\"0.998\">Nearly </ac> </bg>";
    private final String VALID_TEXT_BG_WITH_BOLD_TAG = "<bg c=\"00556677\"> <b> <ac audio-time=\"0.598\">Nearly </ac> <ac audio-time=\"0.998\">Nearly </ac> </b> </bg>";
    private final String VALID_TEXT_BG_WITH_ITALIC_TAG = "<bg c=\"00556677\"> <i> <ac audio-time=\"0.598\">Nearly </ac> <ac audio-time=\"0.998\">Nearly </ac> </i> </bg>";
    private final String INVALID_TEXT_BG_NOT_COLSED = "<bg c=\"00556677\"> <i> <ac audio-time=\"0.598\">Nearly </ac> <ac audio-time=\"0.998\">Nearly </ac> </i> <bg>";
    private final String INVALID_TEXT_BG_NO_COLOR = "<bg c=\"00556677\"> <i> <ac audio-time=\"0.598\">Nearly </ac> <ac audio-time=\"0.998\">Nearly </ac> </i> </bg>";


    @Before
    public void prepare() {
        textBackGroundTagProcessor = new TextBackGroundTagProcessor();
    }
}
