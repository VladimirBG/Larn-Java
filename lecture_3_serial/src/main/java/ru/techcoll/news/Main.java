package ru.techcoll.news;

import org.apache.log4j.PropertyConfigurator;
import org.apache.velocity.app.Velocity;
import ru.techcoll.news.rss.RssChannel;
import ru.techcoll.news.rss.RssItem;
import ru.techcoll.news.serial.XmlSerializer;

import java.io.InputStream;
import java.util.Properties;

public class Main {

    private static void init() throws Exception {
        // logging
        PropertyConfigurator.configure(Main.class.getClassLoader().getResource("log4j.properties"));

        // velocity
        try (final InputStream stream = Main.class.getClassLoader().getResourceAsStream("velocity.properties")) {
            Properties properties = new Properties();
            properties.load(stream);
            Velocity.init(properties);
        }
    }

    public static void main(String[] args) throws Exception {
        init();

        XmlSerializer parser = new XmlSerializer();
        RssChannel channel = parser.retrieveFromUrl("https://news.yandex.ru/sport.rss", RssChannel.class, new RssItem());

        System.out.print(new RssFormatter().format(channel));
    }

}
