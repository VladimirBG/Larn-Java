package ru.techcoll.news;

import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;

public class RssFormatter {

    private static Logger log = Logger.getLogger(RssFormatter.class);

    public String format(RssChannel channel) throws Exception {
        log.info("Formatting " + channel);

        Reader reader = new InputStreamReader(getClass().getClassLoader().getResourceAsStream("rss-format.vm"));

        VelocityContext context = new VelocityContext();
        context.put("channel", channel);

        StringWriter writer = new StringWriter();
        Velocity.evaluate(context, writer, "", reader);

        return writer.toString();
    }

}
