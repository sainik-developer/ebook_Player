package com.sixfingers.bp.configparser;

import com.sixfingers.bp.model.Audio;
import com.sixfingers.bp.model.Content;
import com.sixfingers.bp.model.Text;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

@RunWith(RobolectricTestRunner.class)
public class ContentXMLParserTest {

    private Parser<String, Content> contentXMLParser;

    @Before
    public void prior() {
        contentXMLParser = new ContentXMLParser();
    }

    @Test
    public void testText() {
        Content content = contentXMLParser.parser("<Text font-name=\"\" fontNumber=\"20\" textAlign=\"LEFT\" border=\"NONE\" bg-color=\"\" position=\"5%,5%,40%,30%\" text-color=\"000000\" angle=\"0\" highlightTextOnAudio=\"true\" action-enable=\"true\" audio-res=\"cao_chong/cao_chong_p1.mp3\" pg-sp=\"20\"><p ls=\"5\" hi=\"6\" li=\"1\"><ac audio-time=\"0.598\">Nearly</ac> <ac audio-time=\"1.054\">two</ac> <ac audio-time=\"1.294\">thousand</ac> <ac audio-time=\"1.794\">years</ac> <ac audio-time=\"2.052\">ago</ac> <ac audio-time=\"2.418\">in</ac> <ac audio-time=\"2.518\">ancient</ac> <ac audio-time=\"2.829\">China</ac>,</p><p><ac audio-time=\"3.309\">there</ac> <ac audio-time=\"3.435\">was</ac> <ac audio-time=\"3.58\">a</ac> <ac audio-time=\"3.651\">boy</ac> <ac audio-time=\"3.974\">named</ac> <b><ac audio-time=\"4.224\">Cao</ac> <ac audio-time=\"4.583\">Chong</ac></b> <ac audio-time=\"5.046\">who</ac> <ac audio-time=\"5.179\">was</ac> <ac audio-time=\"5.41\">curious</ac> <ac audio-time=\"6.029\">and</ac> <ac audio-time=\"6.143\">loved</ac> <ac audio-time=\"6.584\">asking</ac> <ac audio-time=\"7.032\">questions</ac>.<ac audio-time=\"8.275\">He</ac> <ac audio-time=\"8.567\">wondered</ac> <ac audio-time=\"8.903\">if</ac> <ac audio-time=\"9.044\">fish</ac> <ac audio-time=\"9.297\">had</ac> <ac audio-time=\"9.493\">ears</ac>, <ac audio-time=\"10.444\">whether</ac> <ac audio-time=\"10.816\">they</ac> <ac audio-time=\"10.988\">could</ac> <ac audio-time=\"11.127\">hear</ac>, <ac audio-time=\"11.664\">and</ac> <ac audio-time=\"11.786\">how</ac> <ac audio-time=\"12.107\">they</ac> <ac audio-time=\"12.252\">slept</ac> <ac audio-time=\"12.654\">in</ac> <ac audio-time=\"12.774\">the</ac> <ac audio-time=\"12.873\">water</ac>. <ac audio-time=\"14.182\">One</ac> <ac audio-time=\"14.474\">day</ac>,<ac audio-time=\"14.977\">Chong</ac> <ac audio-time=\"15.364\">was</ac> <ac audio-time=\"15.554\">very</ac> <ac audio-time=\"15.934\">excited</ac> <ac audio-time=\"16.483\">because</ac> <ac audio-time=\"16.879\">he</ac> <ac audio-time=\"17.041\">was</ac> <ac audio-time=\"17.187\">going</ac> <ac audio-time=\"17.441\">to</ac> <ac audio-time=\"17.502\">see</ac> <ac audio-time=\"17.799\">a</ac> <ac audio-time=\"17.878\">gigantic</ac> <ac audio-time=\"18.49\">animal</ac> <ac audio-time=\"18.937\">called</ac> <ac audio-time=\"19.248\">an</ac> <ac audio-time=\"19.444\">elephant</ac>.<ac audio-time=\"20.394\">There</ac> <ac audio-time=\"20.658\">had</ac> <ac audio-time=\"20.844\">not</ac> <ac audio-time=\"21.057\">been</ac> <ac audio-time=\"21.236\">an</ac> <ac audio-time=\"21.383\">elephant</ac> <ac audio-time=\"21.798\">in</ac> <ac audio-time=\"21.915\">their</ac> <ac audio-time=\"22.031\">country</ac> <ac audio-time=\"22.462\">before</ac>.<ac audio-time=\"23.381\">It</ac> <ac audio-time=\"23.529\">was</ac> <ac audio-time=\"23.689\">a</ac> <ac audio-time=\"23.825\">birthday</ac> <ac audio-time=\"24.21\">present</ac> <ac audio-time=\"24.614\">to</ac> <ac audio-time=\"24.826\">his</ac> <ac audio-time=\"25.051\">father</ac> <ac audio-time=\"25.409\">Cao</ac> <ac audio-time=\"25.695\">Cao</ac>, <ac audio-time=\"26.012\">the</ac> <ac audio-time=\"26.164\">Prime</ac> <ac audio-time=\"26.419\">Minister</ac> <ac audio-time=\"26.935\">of</ac> <ac audio-time=\"27.076\">Han</ac>, <ac audio-time=\"27.83\">from</ac> <ac audio-time=\"28.092\">the</ac> <ac audio-time=\"28.182\">emperor</ac> <ac audio-time=\"28.596\">of</ac> <ac audio-time=\"28.727\">the</ac> <ac audio-time=\"28.85\">Wu</ac> <ac audio-time=\"29.206\">Kingdom</ac>.</p></Text>");
        Assert.assertEquals(Text.class, content.getClass());
    }

    @Test
    public void testAudio() {
        Content content = contentXMLParser.parser("<Audio position=\"30%,74%,8%,8%\" res=\"cao_chong/bunting-yellow.mp3\"></Audio>");
        Assert.assertEquals(Audio.class, content.getClass());

    }

}
