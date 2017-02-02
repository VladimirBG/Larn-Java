package annotation_context;

import org.springframework.stereotype.Service;

/**
 * Created by Vladimir on 02.02.2017.
 */
@Service("messageProvide")
public class HelloWorldMessageProvider implements MessageProvider {
    public String getMessage() {
        return "Hello World!";
    }
}
