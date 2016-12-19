package ru.techcoll.swords;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;
import org.springframework.web.servlet.view.velocity.VelocityLayoutView;
import org.springframework.web.servlet.view.velocity.VelocityLayoutViewResolver;

@SpringBootApplication
@EnableScheduling
public class Application  extends WebMvcConfigurerAdapter {

    @Bean
    @SuppressWarnings("deprecation")
    public VelocityConfigurer velocityConfigurer() {
        VelocityConfigurer configurer = new VelocityConfigurer();
        configurer.setResourceLoaderPath("/WEB-INF/views/");
        return configurer;
    }

    @Bean
    @SuppressWarnings("deprecation")
    public ViewResolver velocityViewResolver() {
        VelocityLayoutViewResolver resolver = new VelocityLayoutViewResolver();
        resolver.setViewClass(VelocityLayoutView.class);
        resolver.setCache(true);
        resolver.setLayoutUrl("layout.vm");
        resolver.setPrefix("");
        resolver.setSuffix(".vm");
        return resolver;
    }

    public static void main(String[] args) throws Exception {
        new SpringApplicationBuilder(Application.class)
                .run(args);
    }

}
