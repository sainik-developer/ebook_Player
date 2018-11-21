package com.sixfingers.bp.configparser;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

@RunWith(RobolectricTestRunner.class)
public class ConfigFileJSONParserTest {

    @Test
    public void test() throws FileNotFoundException {
        ConfigFileJSONParser configFileJSONParser = new ConfigFileJSONParser();
        FileInputStream fileInputStream = new FileInputStream(ConfigFileJSONParserTest.class.getResource("resources/sample.json").getFile());
    }
}
