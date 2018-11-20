package com.sixfingers.bp.configparser.xml.processor.text;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ActionTagProcessorTest {

    private ActionTagProcessor actionTagProcessor;


    @Before
    public void prepare() {
        actionTagProcessor = new ActionTagProcessor();
    }


    @Test
    public void testChildTags() {
        Assert.assertTrue(actionTagProcessor.childTags().length == 0);
        Assert.assertTrue(actionTagProcessor.name().equals("ac"));
    }


}
