package com.eschao.android.widget.renderer.feature;

import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;

import com.eschao.android.widget.renderer.PageRender;
import com.eschao.android.widget.renderer.PageRenderDecorator;
import com.sixfingers.bp.model.TextStyleSpanable;

import java.util.List;

public class TextPageRenderDecorator extends PageRenderDecorator {

    TextPageRenderDecorator(final PageRender pageRender) {
        super(pageRender);
    }

    private void drawParagraph() {
        //pageRender.pageProvider.getBackgroundBitmap(mPageNo);
//        pageRender.mBackgroundBitmap;

    }

    @Override
    public void onDrawFrame() {
        super.onDrawFrame();
    }

    private void markShadowColor(final SpannableStringBuilder builder,
                                 final List<TextStyleSpanable> shadows) {

    }

    private void setLevelBackgroundColor(final SpannableStringBuilder ssBuilder, final int lableBackgroundColor) {
        ssBuilder.setSpan(new BackgroundColorSpan(lableBackgroundColor),
                0,
                ssBuilder.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }
}


//
//    private Paint.Align setTextAlign(final String textAlign) {
//        if (textAlign.equals("left")) {
//            return Paint.Align.LEFT;
//        } else if (textAlign.equals("right")) {
//            return Paint.Align.RIGHT;
//        } else if (textAlign.equals("center")) {
//            return Paint.Align.CENTER;
//        }
//        return Paint.Align.LEFT;
//    }
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
