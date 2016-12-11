package ru.techcoll.news.service;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.log.CommonsLogLogChute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.techcoll.news.rss.RssChannel;

import javax.annotation.PostConstruct;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;

@Component
public class RssFormatter {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private VelocityEngine engine = new VelocityEngine();

    @PostConstruct
    public void init() throws Exception{
        engine.setProperty(RuntimeConstants.RUNTIME_LOG_LOGSYSTEM, new CommonsLogLogChute());
    }

    public String format(RssChannel channel) throws Exception {
        log.info("Formatting " + channel);

        Reader reader = new InputStreamReader(getClass().getClassLoader().getResourceAsStream("rss-format.vm"));

        VelocityContext context = new VelocityContext();
        context.put("channel", channel);

        StringWriter writer = new StringWriter();
        engine.evaluate(context, writer, "", reader);

        return writer.toString();
    }

}
