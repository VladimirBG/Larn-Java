package Method_Replacement;

import org.springframework.beans.factory.support.MethodReplacer;

import java.lang.reflect.Method;

/**
 * Created by Vladimir on 06.02.2017.
 */
public class MessageReplacer implements MethodReplacer {
    @Override
    public Object reimplement(Object o, Method method, Object[] objects) throws Throwable {
        if (isOutputMessageMethod(method)) {
            return "Replacer output: " + objects[0];
        } else {
            throw new IllegalArgumentException("Unable to repliement method" + method.getName());
        }
    }

    private boolean isOutputMessageMethod(Method method) {
        if (method.getParameterTypes().length != 1) {
            return false;
        }

        if (!("outputMessage").equals(method.getName())) {
            return false;
        }

        if (method.getParameterTypes()[0] != String.class) {
            return false;
        }

        return true;
    }
}
