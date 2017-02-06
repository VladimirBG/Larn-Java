package LookupMethodInjection;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.util.StopWatch;

/**
 * Created by Vladimir on 03.02.2017.
 */
public class LookupDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:app-config.xml");
        ctx.refresh();

        DemoBeanInterface abstractBean = (DemoBeanInterface) ctx.getBean("abstractLookup");
        DemoBeanInterface standartBean = (DemoBeanInterface) ctx.getBean("standartLookBean");

        displayInfo(abstractBean);
        displayInfo(standartBean);
    }

    public static void displayInfo(DemoBeanInterface bean) {
        MyHelper helper1 = bean.getMyHelper();
        MyHelper helper2 = bean.getMyHelper();

        System.out.println("Helper instances the same?: " + (helper1 == helper2));

        StopWatch stopWatch = new StopWatch();
        stopWatch.start("lookupDem");

        for (int i = 0; i < 100000; i++) {
            MyHelper helper = bean.getMyHelper();
            helper.doSmthUseful();
        }

        stopWatch.stop();

        System.out.println("100000 gets took " + stopWatch.getTotalTimeMillis() + " ms");
    }
}
