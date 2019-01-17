package com.eschao.android.widget.renderer.feature;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.style.BackgroundColorSpan;
import android.text.style.StyleSpan;

import com.eschao.android.widget.renderer.BasePageRender;
import com.eschao.android.widget.renderer.FontManager;
import com.eschao.android.widget.renderer.PageRender;
import com.eschao.android.widget.renderer.PageRenderDecorator;
import com.eschao.android.widget.renderer.feature.span.ShadowColorSpan;
import com.eschao.android.widget.utils.CalculationUtils;
import com.sixfingers.bp.model.Page;
import com.sixfingers.bp.model.Paragraph;
import com.sixfingers.bp.model.Text;
import com.sixfingers.bp.model.TextStyleSpanable;

import java.util.List;

public class TextPageRenderDecorator {

//    public TextPageRenderDecorator(final PageRender pageRender) {
//        super(pageRender);
//    }


    private static void drawText(final Text text, final BasePageRender basePageRender) {

        TextPaint textPaint = new TextPaint();
        // To have smooth edge of image
        textPaint.setAntiAlias(true);
        textPaint.setSubpixelText(true);
        // get the specified font name
//        textPaint.setTypeface(FontManager.getByName(basePageRender.mContext, text.fontName));
        // set text as filled
        textPaint.setStyle(Paint.Style.FILL);
        // set text color
        textPaint.setColor(Color.GREEN);
        textPaint.setTextSize(10 * basePageRender.mContext.getResources().getDisplayMetrics()
                .scaledDensity);

        // setting the alignment of the text
        textPaint.setTextAlign(setTextAlign(text.textAlign.name()));

        if (text.actualPosition == null) {
            text.actualPosition = CalculationUtils.convertPercentageToAbsoluteForParagraph(text.position,
                    basePageRender.mCanvas.getWidth(), basePageRender.mCanvas.getHeight());
        }

        float top = text.actualPosition.top;
        for (Paragraph paragraph : text.paragraphs) {
            basePageRender.mCanvas.save();
            basePageRender.mCanvas.translate(text.actualPosition.left, top);
            basePageRender.mCanvas.rotate(text.angle, text.actualPosition.left, top);

            StaticLayout staticLayout = new StaticLayout(drawParagraph(paragraph), textPaint,
                    (int) Math.ceil(text.actualPosition.width) * 2,
                    Layout.Alignment.ALIGN_NORMAL, 1,
                    paragraph.lineSpacing,
                    true);
            top += staticLayout.getHeight() + text.paragraphSpacing;
            staticLayout.draw(basePageRender.mCanvas);
            basePageRender.mCanvas.restore();
        }
    }


    private static SpannableStringBuilder drawParagraph(final Paragraph paragraph) {
        SpannableStringBuilder ssBuilder = new SpannableStringBuilder(paragraph.text);
        applyBold(ssBuilder, paragraph.getTextStyles(TextStyleSpanable.Type.BOLD));
        applyItalic(ssBuilder, paragraph.getTextStyles(TextStyleSpanable.Type.ITALIC));
        applyLevelBackgroundColor(ssBuilder, paragraph.getTextStyles(TextStyleSpanable.Type.LABEL_BACKGROUND));
        applyShadowColor(ssBuilder, paragraph.getTextStyles(TextStyleSpanable.Type.SHADOW));
        return ssBuilder;
    }


    @SuppressWarnings("unchecked")
    public static void onDrawFrame(BasePageRender basePageRender) {
//        super.onDrawFrame();
        // this page's text to be drawn
        Page page = basePageRender.pageContentProvider.provide(basePageRender.mPageNo);
        List<Text> texts = (List<Text>) page.getBySubType(Text.class);
        for (Text text : texts) {
            drawText(text, basePageRender);
        }
    }

    private static void applyBold(final SpannableStringBuilder ssBuilder, final List<TextStyleSpanable> bolds) {
        for (TextStyleSpanable bold : bolds) {
            ssBuilder.setSpan(new StyleSpan(Typeface.BOLD),
                    bold.start, bold.end,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }

    private static void applyItalic(final SpannableStringBuilder ssBuilder,
                                    final List<TextStyleSpanable> italics) {
        for (TextStyleSpanable italic : italics) {
            ssBuilder.setSpan(new StyleSpan(Typeface.ITALIC),
                    italic.start, italic.end,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }

    private static void applyShadowColor(final SpannableStringBuilder ssBuilder,
                                         final List<TextStyleSpanable> shadows) {
        for (TextStyleSpanable textStyleSpanable :
                shadows) {
            //TODO make the redius, dx ,dy dynamic
            ssBuilder.setSpan(new ShadowColorSpan(10, 2, 2, textStyleSpanable.color),
                    textStyleSpanable.start,
                    textStyleSpanable.end,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        }
    }

    private static void applyLevelBackgroundColor(final SpannableStringBuilder ssBuilder,
                                                  final List<TextStyleSpanable> levelBgColors) {
        for (TextStyleSpanable textStyleSpanable : levelBgColors) {
            ssBuilder.setSpan(new BackgroundColorSpan(textStyleSpanable.color),
                    textStyleSpanable.start,
                    textStyleSpanable.end,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        }
    }


    private static Paint.Align setTextAlign(final String textAlign) {
        if (textAlign.equals("left")) {
            return Paint.Align.LEFT;
        } else if (textAlign.equals("right")) {
            return Paint.Align.RIGHT;
        } else if (textAlign.equals("center")) {
            return Paint.Align.CENTER;
        }
        return Paint.Align.LEFT;
    }
}