package ru.techcoll.news.serial;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Автоматически сериализуемое свойство
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface SerialNode {

    /**
     * @return XPath-выражение для нахождения значения свойства.
     */
    String value();

}
