package com.gdg.rssreader.manager.xml;

import com.gdg.rssreader.feed.FeedActivity;
import com.gdg.rssreader.model.RssItem;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.lang.reflect.Field;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)
public class RssHandlerTest{

    private FeedActivity feedActivity;
    private RssHandler handler;

    @Before
    public void setUp() throws Exception {
        handler = new RssHandler(null);
        handler.item = new RssItem();
        feedActivity = Robolectric.buildActivity(FeedActivity.class).create().get();
    }

    @After
    public void tearDown() throws Exception {
        handler = null;
        feedActivity = null;
    }

    @Test
    public void testCreate(){
        assertNotNull(feedActivity);
    }

    @Test
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