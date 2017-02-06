package Method_Replacement;

/**
 * Created by Vladimir on 06.02.2017.
 */
public class ReplacementTarget {
    public String outputMessage(String msg) {
        return "Standart output: " + msg;
    }

    public String outputMessage(int msg) {
        return "Output int message" + msg;
    }
}
