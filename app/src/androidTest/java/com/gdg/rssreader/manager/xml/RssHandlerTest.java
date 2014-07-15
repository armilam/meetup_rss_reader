package com.gdg.rssreader.manager.xml;

import com.gdg.rssreader.model.RssItem;

import junit.framework.TestCase;

import java.lang.reflect.Field;

public class RssHandlerTest extends TestCase {

    private RssHandler handler;
    public void setUp() throws Exception {
        super.setUp();
        handler = new RssHandler(null);
        handler.item = new RssItem();
    }

    public void tearDown() throws Exception {
        handler = null;
    }

    public void testParseRssItem() throws Exception {
        Field f = RssItem.class.getDeclaredField("title");
        handler.fieldsForItem.put("title", f);
        handler.parseRssItem("title", "test");
        assertEquals(handler.item.getTitle(), "test");

        f = RssItem.class.getDeclaredField("link");
        handler.fieldsForItem.put("link", f);
        handler.parseRssItem("link", "test");
        assertEquals(handler.item.getLink(), "test");
    }
}