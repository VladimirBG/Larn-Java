package Constructor_Injection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("customizeMessageProvider")
public class CustomizeMessageProvider implements MessageProvider{
    private int message;
    private String message2;

    @Autowired
    public CustomizeMessageProvider(int message, String message2) {
        this.message = message;
        this.message2 = message2;
    }

    @Override
    public String getMessage() {
        return message + " " + message2;
    }
}
