package Setter_Ijection_Test;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Set;

/**
 * Created by Vladimir on 02.02.2017.
 */
public class SetterInjection {
    private String name;
    private int age;
    private float aFloat;
    private Set<String> arrayA;
    private String spELTesting= "SpEL is work";

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setaFloat(float aFloat) {
        this.aFloat = aFloat;
    }

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:setter-context.xml");
        ctx.refresh();
        SetterInjection sett = (SetterInjection) ctx.getBean("setterInjection");
        System.out.println(sett);
    }

    @Override
    public String toString() {
        String v = "";
        for (String s : arrayA) {
            v += " " + s;
        }
        String result = "Name: " +  name + "\nList: " + v + "\nAge:  " + age;
        return result;
    }

    public void setArrayA(Set<String> arrayA) {
        this.arrayA = arrayA;
    }

    public void setSpELTesting(String SpELTesting) {
        spELTesting = SpELTesting;
    }

    public String getSpELTesting() {
        return spELTesting;
    }
}
