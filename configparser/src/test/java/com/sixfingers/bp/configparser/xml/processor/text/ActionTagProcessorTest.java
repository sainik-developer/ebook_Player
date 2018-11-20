package com.sixfingers.bp.configparser.xml.processor.text;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ActionTagProcessorTest {

    private ActionTagProcessor actionTagProcessor;

    private final String VALID_ACTION_TAG = "<ac audio-time=\"0.598\">Nearly</ac>";
    private final String VALID_ACTION_WITH_SPACE = "<ac audio-time=\"0.598\"> Nearly  </ac>";
    private final String INVALID_NO_AUDIO_TIME_VALUE_ACTION_TAG = "<ac audio-time=\"\">Nearly</ac>";
    private final String INVALID_NO_AUDIO_TIME_ACTION_TAG = "<ac >Nearly</ac>";
    private final String INVALID_TAG_CLOSE_ACTION_TAG = "<ac >Nearly/ac>";


    @Before
    public void prepare() {
        actionTagProcessor = new ActionTagProcessor();
    }


    @Test
    public void testChildTagsAndName() {
        Assert.assertTrue(actionTagProcessor.childTags().length == 0);
        Assert.assertTrue(actionTagProcessor.name().equals("ac"));
    }

    @Test
    public void testReadValid() {

    }

    @Test
    public void testReadWithSpaceValid() {

    }

    @Test
    public void testReadInvalidWithNoAudioTimeValue() {

    }

    @Test
    public void testReadInvalidWithNoAudioTimeTag(){

    }
//
//    @Test
//    public void testRead

}
