package com.sixfingers.bp.player.landscape;

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
import com.sixfingers.bp.player.R;
import com.sixfingers.bp.player.features.PowerEbookDecorator;

import java.util.Arrays;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

/***
 * it'a for landscape mode
 */
public class LandScapeControlPowerBookDecorator extends PowerEbookDecorator {

    private TextView pageNoView;
    private Spinner languageSpinnerView;
    private Spinner speedSpinnerView;

    private static final String LANGUAGE_OPTIONS[] = {"English", "Español"};

    public LandScapeControlPowerBookDecorator(ViewGroup viewGroup) {
        super(viewGroup);
        inflate(viewGroup.getContext(), R.layout.landscape_control_panel, this);
        setPageNo();
        setLanguageAdapter();
        setSpeedAdapter();
        frameLayout.addView(this, new FrameLayout.LayoutParams(MATCH_PARENT, getScreenHeightByPercentage(12.0f), Gravity.BOTTOM | Gravity.START));
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
        languageSpinnerView = findViewById(R.id.id_language);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.language_spinner_text, R.id.language_spinner_text_id, LANGUAGE_OPTIONS);
        adapter.setDropDownViewResource(R.layout.spinner_text);
        languageSpinnerView.setAdapter(adapter);
//        languageSpinnerView.setOnItemClickListener((parent, view, position, id) -> {
//            CentralHandler.Companion.getInstance().changeLanguage(LANGUAGE_OPTIONS[(int) id]);
//        });

    }

    private void setSpeedAdapter() {
        Spinner spinner = findViewById(R.id.id_speed_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.speed_spinner_text, R.id.language_spinner_text_id, Arrays.asList("0.8", "0.9", "1.0", "1.1", "1.2"));
        adapter.setDropDownViewResource(R.layout.spinner_text);
        spinner.setSelection(2);
        spinner.setAdapter(adapter);
    }

    @Override
    public void revealView() {
        this.setAlpha(0f);
        this.setVisibility(View.VISIBLE);
        this.animate().alpha(1f).setDuration(1000).setListener(null);
    }

    @Override
    public void hideView() {
        this.animate().alpha(0f).setDuration(1000).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                LandScapeControlPowerBookDecorator.this.setVisibility(View.GONE);
            }
        });
    }
}
