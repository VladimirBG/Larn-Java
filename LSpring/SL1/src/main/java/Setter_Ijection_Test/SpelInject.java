package Setter_Ijection_Test;

/**
 * Created by Vladimir on 02.02.2017.
 */
public class SpelInject {
    private String name = "Vasya";
    private int age = 25;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }
}
