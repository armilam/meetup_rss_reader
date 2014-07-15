package com.gdg.rssreader.manager.xml;

import com.gdg.rssreader.model.RssItem;

import junit.framework.TestCase;

import java.lang.reflect.Field;

public class TestRssHandler extends TestCase{

    private RssHandler handler;

    public void setUp() throws Exception {
        handler = new RssHandler(null);
        handler.item = new RssItem();
    }

    public void tearDown() throws Exception {
        handler = null;
    }

    public void testParseRssItem() throws Exception {
        RssItem itemToTest = new RssItem();
        itemToTest = setRssItemField("title", "test");
        assertEquals("test", itemToTest.getTitle());

        itemToTest = setRssItemField("link", "test");
        assertEquals("test", itemToTest.getLink());

        itemToTest = setRssItemField("content", "test");
        assertEquals("test", itemToTest.getContent());
    }

    private RssItem setRssItemField(String fieldName, String fieldValue) throws NoSuchFieldException {
        Field f = RssItem.class.getDeclaredField(fieldName);
        handler.fieldsForItem.put(fieldName, f);
        handler.parseRssItem(fieldName, fieldValue);
        return handler.item;
    }
}