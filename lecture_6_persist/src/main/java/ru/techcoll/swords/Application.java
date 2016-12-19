package ru.techcoll.swords;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class Application  extends WebMvcConfigurerAdapter {

    public static void main(String[] args) throws Exception {
        new SpringApplicationBuilder(Application.class)
                .headless(true).web(false)
                .run(args);
    }

}
