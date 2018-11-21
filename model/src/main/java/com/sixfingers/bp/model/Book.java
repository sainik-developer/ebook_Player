package com.sixfingers.bp.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Book {

    public Property globalProp;
    public List<Page> pages = new LinkedList<>();
    private Map<Locale, List<Page>> localeWisePageMap = new HashMap<>();

    /***
     *
     * @param language
     * @return
     */
    public List<Page> getPagesByLanguage(final Locale language) {
        List<Page> pagesAsked = localeWisePageMap.get(language);
        if (pagesAsked != null)
            return pagesAsked;
        pagesAsked = new LinkedList<>();
        for (Page page : pages)
            if (page.lang != null && page.lang.equals(language))
                pagesAsked.add(page);
        localeWisePageMap.put(language, pagesAsked);
        return pagesAsked;
    }


}
