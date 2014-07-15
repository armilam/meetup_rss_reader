package com.gdg.rssreader.manager.xml;


import com.gdg.rssreader.model.RssItem;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.TreeMap;

import rx.subjects.BehaviorSubject;

public class RssHandler extends DefaultHandler {

    Map<String, Field> fieldsForItem = new TreeMap<String, Field>(String.CASE_INSENSITIVE_ORDER);
    BehaviorSubject<RssItem> feedItemsSignal;
    StringBuilder stringBuilder;
    RssItem item;

    private boolean isParsingItem = false;

    public RssHandler(BehaviorSubject<RssItem> feedItemsSignal) {
        this.feedItemsSignal = feedItemsSignal;
    }

    @Override
    public void startDocument() throws SAXException {
        Field[] fields = RssItem.class.getDeclaredFields();
        for (Field f : fields) {
            fieldsForItem.put(f.getName(), f);
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        stringBuilder = new StringBuilder();

        if (qName.equals("item")) {
            if (item != null) {
                feedItemsSignal.onNext(item);
            }

            item = new RssItem();
            isParsingItem = true;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        stringBuilder.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) {

        if (isParsingItem && fieldsForItem.containsKey(qName)) {
            parseRssItem(qName, stringBuilder.toString());
        }
    }

    void parseRssItem(String methodName, String value) {
        if (methodName.equals("content:encoded"))
            methodName = "content";

        try {
            Field f = fieldsForItem.get(methodName);
            f.setAccessible(true);
            f.set(item, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
