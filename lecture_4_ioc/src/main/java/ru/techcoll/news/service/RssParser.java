package ru.techcoll.news.service;

import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.techcoll.news.rss.RssChannel;
import ru.techcoll.news.rss.RssItem;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

@Component
public class RssParser {

    private final Logger log = LoggerFactory.getLogger(getClass());

    public RssChannel retrieveFromUrl(String url) throws Exception {
        return retrieveFromStream(getDocument(url));
    }

    public RssChannel retrieveFromStream(InputStream stream) throws Exception {
        DocumentFactory factory = new DocumentFactory();
        SAXReader reader = new SAXReader();
        reader.setDocumentFactory(factory);

        Document document = reader.read(stream);
        Node node = document.selectSingleNode("/rss/channel");
        return parseChannel(node);
    }

    public InputStream getDocument(String url) throws Exception {
        log.info("Reading RSS from " + url);

        URLConnection connection = new URL(url).openConnection();
        return connection.getInputStream();
    }

    private RssChannel parseChannel(Node node) {
        RssChannel channel = new RssChannel();

        channel.setTitle(node.valueOf("title").trim());
        channel.setLink(node.valueOf("link").trim());
        channel.setDescription(node.valueOf("description").trim());
        channel.setImageUrl(node.valueOf("image/url").trim());
        channel.setLastBuildDate(node.valueOf("lastBuildDate").trim());

        List<Node> nodes = node.selectNodes("item");
        for (Node n : nodes)
            channel.getItems().add(parseItem(n));

        return channel;
    }

    private RssItem parseItem(Node node) {
        RssItem item = new RssItem();

        item.setTitle(node.valueOf("title").trim());
        item.setLink(node.valueOf("link").trim());
        item.setDescription(node.valueOf("description").trim());
        item.setPubDate(node.valueOf("pubDate").trim());
        item.setGuid(node.valueOf("guid").trim());

        return item;
    }

}
