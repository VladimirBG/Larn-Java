package Method_Replacement;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.util.StopWatch;

public class MethodReplacement {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:replacer-config.xml");
        ctx.refresh();

        ReplacementTarget replacementTarget = (ReplacementTarget) ctx.getBean("repTarget");
        ReplacementTarget standartTarget = (ReplacementTarget) ctx.getBean("standartTarget");

        displayInfo(replacementTarget);
        displayInfo(standartTarget);
    }

    public static void displayInfo(ReplacementTarget bean) {
        System.out.println("Watch: \n");

        StopWatch stopWatch = new StopWatch();
        stopWatch.start("watchReplacer");
        String out = "";

        for (int i = 0; i < 100000; i++) {
            out = bean.outputMessage("MESSAGE");
        }

        System.out.println(out);
        stopWatch.stop();

        System.out.println("100000 gets took " + stopWatch.getTotalTimeMillis() + " ms");
    }
}
