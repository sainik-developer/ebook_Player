package com.sixfingers.bp.configparser;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

@RunWith(RobolectricTestRunner.class)
public class ContentXMLParserTest {

    @Test
    public void test() throws FileNotFoundException {
        ConfigFileJSONParser configFileJSONParser = new ConfigFileJSONParser();
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream("./configparser/src/test/res/sample.json"));

    }
}
