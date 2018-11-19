package com.sixfingers.bp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Page {
    public Locale lang;
    public int pageNo;
    public String backgroundImageName;
    public List<Content> contents = new ArrayList<>();
}
