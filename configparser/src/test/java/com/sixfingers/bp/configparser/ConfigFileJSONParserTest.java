package com.sixfingers.bp.configparser;

import com.sixfingers.bp.model.Book;

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
        FileInputStream fileInputStream = new FileInputStream("./src/test/res/sample.json");
        Book book = configFileJSONParser.parser(fileInputStream);
    }
}
