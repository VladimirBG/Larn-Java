package ru.techcoll.news;

import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;

    public class HtmlFormatter {
        private static Logger log = Logger.getLogger(ru.techcoll.news.HtmlFormatter.class);

        public void format(RssChannel channel) throws Exception {
            log.info("Formatting to HTML " + channel);

            Reader reader = new InputStreamReader(getClass().getClassLoader().getResourceAsStream("html-formatter.vm"));

            VelocityContext context = new VelocityContext();
            context.put("channel", channel);

            StringWriter writer = new StringWriter();
            Velocity.evaluate(context, writer, "", reader);

            FileWriter fileWriter = new FileWriter("src/main/java/ru/techcoll/news/site/t.html");
            fileWriter.write(writer.toString());

        }
    }
