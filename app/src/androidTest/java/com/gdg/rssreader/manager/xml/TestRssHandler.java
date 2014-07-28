package com.gdg.rssreader.manager.xml;

import com.gdg.rssreader.model.RssItem;

import junit.framework.TestCase;

import org.mockito.Mockito;

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

        itemToTest = setRssItemField("title", Mockito.anyString());
        assertEquals(Mockito.anyString(), itemToTest.getTitle());

        itemToTest = setRssItemField("link", Mockito.anyString());
        assertEquals(Mockito.anyString(), itemToTest.getLink());

        itemToTest = setRssItemField("content", Mockito.anyString());
        assertEquals(Mockito.anyString(), itemToTest.getContent());
    }

    private RssItem setRssItemField(String fieldName, String fieldValue) throws NoSuchFieldException {
        Field f = RssItem.class.getDeclaredField(fieldName);
        handler.fieldsForItem.put(fieldName, f);
        handler.parseRssItem(fieldName, fieldValue);
        return handler.item;
    }
}