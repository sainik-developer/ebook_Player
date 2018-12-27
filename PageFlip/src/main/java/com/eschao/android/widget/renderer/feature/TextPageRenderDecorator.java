package com.eschao.android.widget.renderer.feature;

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

public class TextPageRenderDecorator extends PageRenderDecorator {

    public TextPageRenderDecorator(final PageRender pageRender) {
        super(pageRender);
    }


    private void drawText(final Text text) {

        TextPaint textPaint = new TextPaint();
        // To have smooth edge of image
        textPaint.setAntiAlias(true);
        textPaint.setSubpixelText(true);
        // get the specified font name
        textPaint.setTypeface(FontManager.getByName(pageRender.mContext, text.fontName));
        // set text as filled
        textPaint.setStyle(Paint.Style.FILL);
        // set text color
        textPaint.setColor(text.textColor);

        // setting the alignment of the text
        textPaint.setTextAlign(setTextAlign(text.textAlign.name()));

        if (text.actualPosition == null) {
            text.actualPosition = CalculationUtils.convertPercentageToAbsoluteForParagraph(text.position,
                    pageRender.mBackgroundBitmap.getWidth(), mBackgroundBitmap.getHeight());
        }

        float top = text.actualPosition.top;
        for (Paragraph paragraph : text.paragraphs) {
            pageRender.mCanvas.save();
            pageRender.mCanvas.translate(text.actualPosition.left, top);
            pageRender.mCanvas.rotate(text.angle, text.actualPosition.left, top);

            StaticLayout staticLayout = new StaticLayout(drawParagraph(paragraph), textPaint,
                    (int) Math.ceil(text.actualPosition.width),
                    Layout.Alignment.ALIGN_NORMAL, 1,
                    paragraph.lineSpacing,
                    true);
            top += staticLayout.getHeight() + text.paragraphSpacing;
            staticLayout.draw(pageRender.mCanvas);
            pageRender.mCanvas.restore();
        }
    }


    private SpannableStringBuilder drawParagraph(final Paragraph paragraph) {
        SpannableStringBuilder ssBuilder = new SpannableStringBuilder(paragraph.text);

        applyBold(ssBuilder, paragraph.getTextStyles(TextStyleSpanable.Type.BOLD));
        applyItalic(ssBuilder, paragraph.getTextStyles(TextStyleSpanable.Type.ITALIC));
        applyLevelBackgroundColor(ssBuilder, paragraph.getTextStyles(TextStyleSpanable.Type.LABEL_BACKGROUND));
        applyShadowColor(ssBuilder, paragraph.getTextStyles(TextStyleSpanable.Type.SHADOW));
        return ssBuilder;
    }


    @Override
    @SuppressWarnings("unchecked")
    public void onDrawFrame() {
        super.onDrawFrame();
        // this page's text to be drawn
        Page page = pageRender.pageContentProvider.provide(mPageNo);
        List<Text> texts = (List<Text>) page.getBySubType(Text.class);
        for (Text text : texts) {
            drawText(text);
        }
    }

    private void applyBold(final SpannableStringBuilder ssBuilder, final List<TextStyleSpanable> bolds) {
        for (TextStyleSpanable bold : bolds) {
            ssBuilder.setSpan(new StyleSpan(Typeface.BOLD),
                    bold.start, bold.end,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }

    private void applyItalic(final SpannableStringBuilder ssBuilder,
                             final List<TextStyleSpanable> italics) {
        for (TextStyleSpanable italic : italics) {
            ssBuilder.setSpan(new StyleSpan(Typeface.ITALIC),
                    italic.start, italic.end,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }

    private void applyShadowColor(final SpannableStringBuilder ssBuilder,
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

    private void applyLevelBackgroundColor(final SpannableStringBuilder ssBuilder,
                                           final List<TextStyleSpanable> levelBgColors) {
        for (TextStyleSpanable textStyleSpanable : levelBgColors) {
            ssBuilder.setSpan(new BackgroundColorSpan(textStyleSpanable.color),
                    textStyleSpanable.start,
                    textStyleSpanable.end,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        }
    }


    private Paint.Align setTextAlign(final String textAlign) {
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


//
//    private void drawTextAndImage(Canvas myCanvas) {
//        int currentParagraphNo = 0;
//
//        // paragraph as per json
//        for (Paragraph ph : leave.paragraphs) {
//
//            TextPaint textPaint = new TextPaint();
//
//            textPaint.setAntiAlias(true);
//            textPaint.setSubpixelText(true);
//
//            textPaint.setTypeface(typeface);
//
//            textPaint.setStyle(Paint.Style.FILL);
//            textPaint.setColor(Color.parseColor(ph.fontColor));
//            textPaint.setTextSize(ph.fontNumber);
//
//            // setting the alignment of the text
//            textPaint.setTextAlign(setTextAlign(ph.textAlign));
//
//            // Text level shadowColorForLable TODO TESTING : NOT DONE Not working fix it
//            // Showing shadow on text
//            if (ph.shadowColorForLable != null && !ph.shadowColorForLable.isEmpty())
//                textPaint.setShadowLayer(10, 2, 2, Color.parseColor(ph.shadowColorForLable));
//
//            // TODO optimize the code efficiency by changing the and eliminate repeated
//            // TODO calculation as it will improve the performance
//            if (!ph.isRectLabelCalculationDone) {
//                ph.labelRect = CalculationUtils.convertPercentageToAbsoluteForParagraph(ph.lableRect,
//                        baseBitmap.getWidth(),
//                        baseBitmap.getHeight());
//                ph.isRectLabelCalculationDone = true;
//            }
//
//            float top = ph.labelRect.top;
//
//            int currentSubParagraphIndex = 0;
//            // paragraph as per spilt text by new line
//            for (String actualParagraphText : ph.actualParagraphList) {
//
//                myCanvas.save();
//                myCanvas.translate(ph.labelRect.left, top);
//
//                myCanvas.rotate(ph.lableAngle, ph.labelRect.left, top);
//
//                SpannableStringBuilder ssBuilder = new SpannableStringBuilder(actualParagraphText);
//                setLevelBackgroundColor(ssBuilder, Color.parseColor(ph.lableBackgroundColor));
//
//                // Paragraph first line indent and
//                LeadingMarginSpan.Standard paragraphSpacingSpan = new LeadingMarginSpan.Standard(ph.styles.firstLineHeadIndent,
//                        ph.styles.headIndent);
//                ssBuilder.setSpan(paragraphSpacingSpan, 0, ssBuilder.length(), 0);
//
//
//                // mark the word by Italic
//                markWordByStyle(ph.ItalicWords,ssBuilder,ph.italicWordsPositionOfAllOccurrence,currentSubParagraphIndex,Typeface.ITALIC);
//                // mark the word by bold
//                markWordByStyle(ph.StrongWords,ssBuilder,ph.strongWordsPositionOfAllOccurrence,currentSubParagraphIndex,Typeface.BOLD);
//
//                // TODO testing
//                if (paraIndex == currentParagraphNo) {
//                    TimeLine timeLine = ph.timeLineArray.get(timeIndex);
//                    if (timeLine.subParagraphIndex == currentSubParagraphIndex) {
//                        ssBuilder.setSpan(new ForegroundColorSpan(Color.parseColor(ph.highlightColor)),
//                                timeLine.positionRespectToSubParagraph,
//                                timeLine.positionRespectToSubParagraph + timeLine.length,
//                                0);
//                    }
//                }
//
//                StaticLayout staticLayout = new StaticLayout(ssBuilder, textPaint,
//                        (int) Math.ceil(ph.labelRect.right),
//                        Layout.Alignment.ALIGN_NORMAL, 1,
//                        ph.styles.lineSpacing,
//                        true);
//
//                top += staticLayout.getHeight() + ph.styles.paragraphspacing;
//
//                ph.layouts.add(staticLayout);
//                ph.strBuilders.add(ssBuilder);
//
//                staticLayout.draw(myCanvas);
//
//                currentSubParagraphIndex++;
//
//                myCanvas.restore();
//            }
//            currentParagraphNo++;
//        }
//    }
//
//    private void markWordByStyle(final List<String> italicWord,
//                                 final SpannableStringBuilder ssBuilder,
//                                 final Map<String, Map<Integer, List<Pair<Integer, Integer>>>> italicWordsPositionOfAllOccurrence,
//                                 final int currentSubParagraphIndex, final int style) {
//        if (italicWord.size() > 0) {
//            for (String str : italicWord) {
//                Map<Integer, List<Pair<Integer, Integer>>> subParagraphListOfOccurrence = italicWordsPositionOfAllOccurrence.get(str);
//                if (subParagraphListOfOccurrence != null) {
//                    List<Pair<Integer, Integer>> pairs = subParagraphListOfOccurrence.get(currentSubParagraphIndex);
//                    if (pairs != null) {
//                        for (Pair<Integer, Integer> pair : pairs) {
//                            ssBuilder.setSpan(new StyleSpan(style), pair.first, pair.second, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//                        }
//                    }
//                }
//            }
//        }
//    }


//    public void draw(Canvas canvas, final Text text) {
//
//        for (Paragraph paragraph : text.paragraphs) {
//            TextPaint textPaint = new TextPaint();
//            //TODO write down what it does to get smooth edges
//            textPaint.setAntiAlias(true);
//            //TODO write down what it does
//            textPaint.setSubpixelText(true);
//
//            //textPaint.setTypeface(typeface);
//
//            textPaint.setStyle(Paint.Style.FILL);
////            markWordByStyle(ph.ItalicWords,ssBuilder,ph.italicWordsPositionOfAllOccurrence,currentSubParagraphIndex,Typeface.ITALIC);
//
//        }
//
//    }
//
//
//    private void markShadowColor(final SpannableStringBuilder builder, final List<TextStyleSpanable> shadows) {
//
//    }
//
//
//    private void setLevelBackgroundColor(final SpannableStringBuilder ssBuilder, final int lableBackgroundColor) {
//        ssBuilder.setSpan(new BackgroundColorSpan(lableBackgroundColor), 0, ssBuilder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//    }
