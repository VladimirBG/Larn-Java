package ru.techcoll.news;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ru.techcoll.news.rss.RssChannel;
import ru.techcoll.news.service.RssFormatter;
import ru.techcoll.news.service.RssParser;

@Component
@Profile("!test")
public class Main implements CommandLineRunner {

    @Autowired
    private RssParser parser;

    @Autowired
    private RssFormatter formatter;

    public void run(String[] args) throws Exception {
        RssChannel channel = parser.retrieveFromUrl("https://news.yandex.ru/sport.rss");
        System.out.print(formatter.format(channel));
    }

}
