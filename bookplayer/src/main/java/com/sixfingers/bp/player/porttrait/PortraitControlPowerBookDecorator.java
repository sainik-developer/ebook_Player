package com.sixfingers.bp.player.porttrait;

import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.sixfingers.bp.player.features.PowerEbookDecorator;
import com.sixfingers.bp.player.R;

import java.util.Arrays;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

/***
 *
 */
public class PortraitControlPowerBookDecorator extends PowerEbookDecorator {

    public PortraitControlPowerBookDecorator(final ViewGroup viewGroup) {
        super(viewGroup);
        inflate(viewGroup.getContext(), R.layout.portrait_control_panel, this);
        setPageNo();
        setLanguageAdapater();
        frameLayout.addView(this, new FrameLayout.LayoutParams(MATCH_PARENT,
                getScreenHeightByPercentage(7),
                Gravity.BOTTOM | Gravity.START));
    }

    private void setPageNo() {
        TextView textView = findViewById(R.id.id_pageno);
        textView.setText("1");
    }

    private void setLanguageAdapater() {
        Spinner spinner = findViewById(R.id.id_language);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                R.layout.language_spinner_text,
                R.id.language_spinner_text_id, Arrays.asList("English", "Español"));
        adapter.setDropDownViewResource(R.layout.spinner_text);
        spinner.setAdapter(adapter);
    }
}
