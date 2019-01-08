package com.sixfingers.bp.player.porttrait;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.sixfingers.bp.player.PowerEbookDecorator;
import com.sixfingers.bp.player.R;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

/***
 *
 */
public class PortraitControlPowerBookDecorator extends PowerEbookDecorator {

    public PortraitControlPowerBookDecorator(final ViewGroup viewGroup) {
        super(viewGroup);
        inflate(viewGroup.getContext(), R.layout.portrait_control_panel, this);
        setPageNo();
        setLanguageAdapter();
        frameLayout.addView(this, new FrameLayout.LayoutParams(MATCH_PARENT,
                getScreenHeightByPercentage(7),
                Gravity.BOTTOM | Gravity.START));
    }

    private void setPageNo() {
        TextView textView = findViewById(R.id.pageno);
        textView.setText("1-2");
    }

    private void setLanguageAdapter() {
        String[] language = {"EN", "CN", "ES"};

        //Getting the instance of Spinner and applying OnItemSelectedListener on it

//        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                spin.setSelection(position);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });


        //Creating the ArrayAdapter instance having the country list
        Spinner spinner = findViewById(R.id.language);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.planets_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }
}
