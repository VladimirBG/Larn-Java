package annotation_context;

import org.springframework.stereotype.Service;

/**
 * Created by Vladimir on 02.02.2017.
 */
@Service("MP")
public class SecondMessageProvider implements MessageProvider{
    public String getMessage() {
        return "Second MP";
    }
}
