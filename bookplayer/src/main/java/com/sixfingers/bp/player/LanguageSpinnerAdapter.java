package com.sixfingers.bp.player;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LanguageSpinnerAdapter extends ArrayAdapter<String> {


    final Map<String, String> languageMap = new HashMap<>();

    public LanguageSpinnerAdapter(Context context, List<String> supportedLanguage) {
        super(context, 0);
    }

}


///**
// * Created by sainik on 2/27/18.
// */
//public class LanguageSpinnerAdapter extends ArrayAdapter<String> {
//
//    private List<String> supportedLanguage;
//    private LayoutInflater layoutInflater;
//
//    private Map<String, String> languageMap = new HashMap<>();
//
//    {
//        languageMap.put("en", "English");
//        languageMap.put("es", "Spanish");
//        languageMap.put("cn", "Chinese");
//    }
//
//    public LanguageSpinnerAdapter(Context context, List<String> supportedLanguage) {
//        super(context, 0);
//        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        this.supportedLanguage = supportedLanguage;
//    }
//
//    @Override
//    public int getCount() {
//        return supportedLanguage.size();
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public String getItem(int position) {
//        return supportedLanguage.get(position);
//    }
//
//    @Override
//    public View getDropDownView(int position, View convertView, ViewGroup parent) {
//        CheckedTextView row = (CheckedTextView) layoutInflater
//                .inflate(R.layout.speed_spinner_item, parent, false);
//        row.setText(languageMap.get(this.getItem(position)));
//        return row;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        TextView row = (TextView) layoutInflater
//                .inflate(R.layout.test_spinner_item, parent, false);
//        row.setText(languageMap.get(this.getItem(position)));
//        return row;
//    }
//
//}
