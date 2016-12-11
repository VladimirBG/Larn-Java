package ru.techcoll.news;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.techcoll.news.rss.RssChannel;
import ru.techcoll.news.service.RssParser;

import java.io.InputStream;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@SpringBootTest
public class RssParserTest {

    @Autowired
    private ApplicationContext ctx;

    @Autowired
    private RssParser parser;

    @Test
    public void testBeanPresent() throws Exception {
        assertNotNull(ctx);
        assertTrue(ctx.containsBean("rssParser"));
    }

    @Test
    public void testParse() throws Exception {
        InputStream data = getClass().getClassLoader().getResourceAsStream("news-sample.xml");
        RssChannel channel = parser.retrieveFromStream(data);

        assertEquals("NYT > Arts", channel.getTitle());
        assertEquals("http://www.nytimes.com/section/arts?partner=rss&emc=rss", channel.getLink());
        assertEquals(2, channel.getItems().size());
    }

}