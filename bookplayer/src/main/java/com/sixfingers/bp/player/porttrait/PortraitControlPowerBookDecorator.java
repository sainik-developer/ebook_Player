package com.sixfingers.bp.player.porttrait;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.eventhandler.Callback;
import com.example.eventhandler.CentralHandler;
import com.sixfingers.bp.player.features.PowerEbookDecorator;
import com.sixfingers.bp.player.R;

import java.util.Arrays;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

/***
 *
 */
public class PortraitControlPowerBookDecorator extends PowerEbookDecorator {

    private final int height;
    private TextView pageNoView;
    private Spinner languageSpinnerView;
    private static final String LANGUAGE_OPTIONS[] = {"English", "Español"};


    public PortraitControlPowerBookDecorator(final ViewGroup viewGroup) {
        super(viewGroup);
        inflate(viewGroup.getContext(), R.layout.portrait_control_panel, this);
        setPageNo();
        setLanguageAdapter();
        frameLayout.addView(this, new FrameLayout.LayoutParams(MATCH_PARENT,
                height = getScreenHeightByPercentage(7),
                Gravity.BOTTOM | Gravity.START));
    }

    private void setPageNo() {
        pageNoView = findViewById(R.id.id_pageno);
        CentralHandler.Companion.getInstance()
                .addCallback(CentralHandler.MessageCode.PAGE_NUMBER.getCode(), new Callback() {
                    @Override
                    public void callback(Bundle bundle) {
                        pageNoView.setText(bundle.getString("current_page") != null ? bundle.getString("current_page") : "-");
                    }
                });

    }

    private void setLanguageAdapter() {
        Spinner spinner = findViewById(R.id.id_language);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                R.layout.language_spinner_text,
                R.id.language_spinner_text_id, Arrays.asList("English", "Español"));
        adapter.setDropDownViewResource(R.layout.spinner_text);
        spinner.setAdapter(adapter);
    }

    @Override
    public void revealView() {
        this.setAlpha(0f);
        this.setVisibility(View.VISIBLE);
        this.animate()
                .alpha(1f)
                .setDuration(1000)
                .setListener(null);

//        ObjectAnimator animation = ObjectAnimator.ofFloat(this, "translationY", -height);
//        animation.setDuration(1000);
//        animation.start();
//        setVisibility(VISIBLE);
    }

    @Override
    public void hideView() {
        this.animate()
                .alpha(0f)
                .setDuration(1000)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        PortraitControlPowerBookDecorator.this.setVisibility(View.GONE);
                    }
                });
//        ObjectAnimator animation = ObjectAnimator.ofFloat(this, "translationY", height);
//        animation.setDuration(1000);
//        animation.start();
//        setVisibility(INVISIBLE);
    }
}
