package com.sixfingers.bp.configparser;

import android.util.Log;

import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }


    @Test
    public void test() throws IOException{
        InputStream inputStream = ClassLoader.getSystemResourceAsStream("sample.json");
        BufferedReader inputStreamReader = new BufferedReader(new InputStreamReader(inputStream));
        assertNotNull("ersferf",inputStreamReader.readLine());
    }


}