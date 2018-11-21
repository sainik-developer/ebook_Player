package com.sixfingers.bp.configparser.xml.processor;

import com.sixfingers.bp.configparser.xml.processor.audio.AudioTagProcessor;
import com.sixfingers.bp.configparser.xml.processor.text.ActionTagProcessor;
import com.sixfingers.bp.configparser.xml.processor.text.BoldTagProcessor;
import com.sixfingers.bp.configparser.xml.processor.text.ItalicTagProcessor;
import com.sixfingers.bp.configparser.xml.processor.text.ParagraphTagProcessor;
import com.sixfingers.bp.configparser.xml.processor.text.ShadowTagProcessor;
import com.sixfingers.bp.configparser.xml.processor.text.TextBackGroundTagProcessor;
import com.sixfingers.bp.configparser.xml.processor.text.TextTagProcessor;

import org.junit.Assert;
import org.junit.Test;

public class TagEnumTest {

    @Test
    public void test() {
        Assert.assertEquals(TagEnum.AUDIO.getProcessor().getClass(), AudioTagProcessor.class);
        Assert.assertEquals(TagEnum.TEXT.getProcessor().getClass(), TextTagProcessor.class);
        Assert.assertEquals(TagEnum.PARAGRAPH.getProcessor().getClass(), ParagraphTagProcessor.class);
        Assert.assertEquals(TagEnum.ACTION.getProcessor().getClass(), ActionTagProcessor.class);
        Assert.assertEquals(TagEnum.BOLD.getProcessor().getClass(), BoldTagProcessor.class);
        Assert.assertEquals(TagEnum.ITALIC.getProcessor().getClass(), ItalicTagProcessor.class);
        Assert.assertEquals(TagEnum.SHADOW.getProcessor().getClass(), ShadowTagProcessor.class);
        Assert.assertEquals(TagEnum.TEXT_BACK_GROUND.getProcessor().getClass(), TextBackGroundTagProcessor.class);
    }
}
