package com.sixfingers.bp.player.landscape;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.sixfingers.bp.player.features.BackHomeControlPowerBookDecorator;
import com.sixfingers.bp.player.features.PowerEbookDecorator;
import com.sixfingers.bp.player.R;

import java.util.Arrays;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class LandScapeControlPowerBookDecorator extends PowerEbookDecorator {

    public LandScapeControlPowerBookDecorator(ViewGroup viewGroup) {
        super(viewGroup);
        inflate(viewGroup.getContext(), R.layout.landscape_control_panel, this);
        setPageNo();
        setLanguageAdpater();
        setSpeedAdapater();
        frameLayout.addView(this, new FrameLayout.LayoutParams(MATCH_PARENT,
                getScreenHeightByPercentage(12.0f), Gravity.BOTTOM | Gravity.START));

    }

    private void setPageNo() {
        TextView pageNoText = findViewById(R.id.id_pageno);
        pageNoText.setText("1-2");
    }

    private void setLanguageAdpater() {
        Spinner spinner = findViewById(R.id.id_language);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                R.layout.language_spinner_text,
                R.id.language_spinner_text_id,
                Arrays.asList("English", "Español"));
        adapter.setDropDownViewResource(R.layout.spinner_text);
        spinner.setAdapter(adapter);
    }

    private void setSpeedAdapater() {
        Spinner spinner = findViewById(R.id.id_speed_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                R.layout.speed_spinner_text,
                R.id.language_spinner_text_id,
                Arrays.asList("0.8", "0.9", "1.0", "1.1", "1.2"));
        adapter.setDropDownViewResource(R.layout.spinner_text);
        spinner.setSelection(2);
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
    }

    @Override
    public void hideView() {
        this.animate()
                .alpha(0f)
                .setDuration(1000)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        LandScapeControlPowerBookDecorator.this.setVisibility(View.GONE);
                    }
                });
    }
}
