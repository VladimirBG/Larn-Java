package ru.techcoll.news;

import org.junit.Test;

import java.io.InputStream;

import static org.junit.Assert.assertEquals;

public class RssParserTest {

    @Test
    public void testParse() throws Exception {
        InputStream data = getClass().getClassLoader().getResourceAsStream("news-sample.xml");
        RssChannel channel = new RssParser().retrieveFromStream(data);

        assertEquals("NYT > Arts", channel.getTitle());
        assertEquals("http://www.nytimes.com/section/arts?partner=rss&emc=rss", channel.getLink());
        assertEquals(2, channel.getItems().size());
    }

}