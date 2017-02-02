package annotation_context;

import org.springframework.context.support.GenericXmlApplicationContext;


public class DeclareSpringComponents {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:app-context-annotation.xml");
        ctx.refresh();
        annotation_context.MessageRenderer messageRenderer = ctx.getBean("messageRenderer", annotation_context.MessageRenderer.class);
        messageRenderer.render();
    }
}
