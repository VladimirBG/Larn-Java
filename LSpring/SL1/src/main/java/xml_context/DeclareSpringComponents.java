package xml_context;

import org.springframework.context.support.GenericXmlApplicationContext;


public class DeclareSpringComponents {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:app-context-xml.xml");
        ctx.refresh();
        xml_context.MessageRenderer messageRenderer = ctx.getBean("messageRenderer", xml_context.MessageRenderer.class);
        messageRenderer.render();
    }
}
