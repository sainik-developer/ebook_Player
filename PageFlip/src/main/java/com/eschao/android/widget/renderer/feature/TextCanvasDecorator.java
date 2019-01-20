package com.eschao.android.widget.renderer.feature;

import android.content.Context;
import android.graphics.Canvas;
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

import com.eschao.android.widget.renderer.CanvasDecorator;
import com.eschao.android.widget.renderer.FontManager;
import com.eschao.android.widget.renderer.feature.span.ShadowColorSpan;
import com.eschao.android.widget.utils.CalculationUtils;
import com.sixfingers.bp.model.Page;
import com.sixfingers.bp.model.Paragraph;
import com.sixfingers.bp.model.Text;
import com.sixfingers.bp.model.TextStyleSpanable;

import java.util.List;

/***
 *
 */
public final class TextCanvasDecorator extends CanvasDecorator {

    public TextCanvasDecorator(final CanvasDecorator canvasDecorator) {
        super(canvasDecorator);
    }

    public TextCanvasDecorator() {
        super(null);
    }

    @Override
    public void decorateCanvas(final Canvas canvas, final Page page, final Context context) {
        if (canvasDecorator != null) {
            canvasDecorator.decorateCanvas(canvas, page, context);
        }
        List<Text> texts = (List<Text>) page.getBySubType(Text.class);
        for (Text text : texts) {
            if (text.actualPosition == null) {
                text.actualPosition = CalculationUtils.convertPercentageToAbsoluteForParagraph(text.position,
                        canvas.getWidth(), canvas.getHeight());
            }
            drawText(canvas, text, createTextPaint(text, context));
        }

    }

    private void drawText(final Canvas canvas, final Text text, final TextPaint textPaint) {
        float top = text.actualPosition.top;
        for (Paragraph paragraph : text.paragraphs) {
            canvas.save();
            canvas.translate(text.actualPosition.left, top);
            canvas.rotate(text.angle, text.actualPosition.left, top);

            StaticLayout staticLayout = new StaticLayout(drawParagraph(paragraph), textPaint,
                    (int) Math.ceil(text.actualPosition.width),
                    Layout.Alignment.ALIGN_NORMAL, 1,
                    paragraph.lineSpacing,
                    true);
            top += staticLayout.getHeight() + text.paragraphSpacing;
            staticLayout.draw(canvas);
            canvas.restore();
        }
    }

    private TextPaint createTextPaint(final Text text, final Context context) {
        final TextPaint textPaint = new TextPaint();
        // To have smooth edge of image
        textPaint.setAntiAlias(true);
        textPaint.setSubpixelText(true);
        // get the specified font name
        textPaint.setTypeface(FontManager.getByName(context, text.fontName));
        // set text as filled
        textPaint.setStyle(Paint.Style.FILL);
        // set text color
        textPaint.setColor(text.textColor);
        textPaint.setTextSize(text.fontNumber * context.getResources().getDisplayMetrics()
                .scaledDensity);
        // setting the alignment of the text
        textPaint.setTextAlign(setTextAlign(text.textAlign.name()));
        return textPaint;
    }

    private SpannableStringBuilder drawParagraph(final Paragraph paragraph) {
        SpannableStringBuilder ssBuilder = new SpannableStringBuilder(paragraph.text);
        applyBold(ssBuilder, paragraph.getTextStyles(TextStyleSpanable.Type.BOLD));
        applyItalic(ssBuilder, paragraph.getTextStyles(TextStyleSpanable.Type.ITALIC));
        applyLevelBackgroundColor(ssBuilder, paragraph.getTextStyles(TextStyleSpanable.Type.LABEL_BACKGROUND));
        applyShadowColor(ssBuilder, paragraph.getTextStyles(TextStyleSpanable.Type.SHADOW));
        return ssBuilder;
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
        switch (textAlign) {
            case "left":
                return Paint.Align.LEFT;
            case "right":
                return Paint.Align.RIGHT;
            case "center":
                return Paint.Align.CENTER;
            default:
                return Paint.Align.LEFT;
        }
    }
}
