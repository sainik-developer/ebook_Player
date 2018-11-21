package com.sixfingers.bp.configparser.xml.processor.text;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class TextTagProcessorTest {

    private TextTagProcessor textTagProcessor;

    private final String VALID_TEXT_TAG = "<Text font-name=\"arial\" fontNumber=\"20\" textAlign=\"left\" border=\"none\" bg-color=\"\" position=\"5%,5%,40%,30%\" text-color=\"#000000\" angle=\"0\" highlightTextOnAudio=\"true\" -action-enable=\"true\" audio-res=\"cao_chong/cao_chong_p1.mp3\" pg-sp=\"20\"><p ls=\"5\" hi=\"6\" li=\"1\"> <sh c=\"00556677\"> <ac audio-time=\"0.598\">Nearly</ac> </sh>.</p></Text>";

    @Before
    public void prepare() {
        textTagProcessor = new TextTagProcessor();
    }
}
