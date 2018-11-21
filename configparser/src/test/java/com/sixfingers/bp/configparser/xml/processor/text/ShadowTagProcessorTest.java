package com.sixfingers.bp.configparser.xml.processor.text;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class ShadowTagProcessorTest {

    private ShadowTagProcessor shadowTagProcessor;

    private final String VALID_SH_TAG = "<sh c=\"00556677\"> <ac audio-time=\"0.598\">Nearly</ac> </sh>";
    private final String VALID_SH_WITH_SPACE = "<sh c=\"00556677\"> <ac audio-time=\"0.598\">Nearly </ac> </sh>";
    private final String VALID_SH_MULTIPLE_ACTION_TAG = "<sh c=\"00556677\"> <ac audio-time=\"0.598\">Nearly </ac> <ac audio-time=\"0.998\">Nearly </ac> </sh>";
    private final String VALID_Sh_WITH_BOLD_TAG = "<sh c=\"00556677\"> <b> <ac audio-time=\"0.598\">Nearly </ac> <ac audio-time=\"0.998\">Nearly </ac> </b> </sh>";
    private final String VALID_SH_WITH_ITALIC_TAG = "<sh c=\"00556677\"> <i> <ac audio-time=\"0.598\">Nearly </ac> <ac audio-time=\"0.998\">Nearly </ac> </i> </sh>";
    private final String INVALID_SH_NOT_COLSED = "<sh c=\"00556677\"> <i> <ac audio-time=\"0.598\">Nearly </ac> <ac audio-time=\"0.998\">Nearly </ac> </i> <sh>";
    private final String INVALID_SH_NO_COLOR = "<sh> <i> <ac audio-time=\"0.598\">Nearly </ac> <ac audio-time=\"0.998\">Nearly </ac> </i> </sh>";


    @Before
    public void prepare() {
        shadowTagProcessor = new ShadowTagProcessor();
    }



}
