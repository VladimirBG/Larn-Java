package Setter_Ijection_Test;

/**
 * Created by Vladimir on 02.02.2017.
 */
public class SpelInject {
    private String name = "SpEL is working";

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
