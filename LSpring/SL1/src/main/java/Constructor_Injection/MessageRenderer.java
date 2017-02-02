package Constructor_Injection;

/**
 * Created by Vladimir on 02.02.2017.
 */
public interface MessageRenderer {
    void render();
    void setMessageProvider(MessageProvider provider);
    MessageProvider getMessageProvider();
}
