package Constructor_Injection;

import org.springframework.context.support.GenericXmlApplicationContext;


public class DeclareSpringComponents {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:app-context-annotation-constructor.xml");
        ctx.refresh();
        MessageRenderer messageRenderer = ctx.getBean("messageRenderer", MessageRenderer.class);
        messageRenderer.render();
    }
}
