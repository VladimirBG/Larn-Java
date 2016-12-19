package ru.techcoll.swords.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Сообщение в чат от пользователя
 */
@JsonIgnoreProperties(value = { "system" }, allowGetters = true)
public class ChatMessage {

    /**
     * Имя пользователя.
     */
    private String name;

    /**
     * Текст сообщения
     */
    private String text;

    /**
     * Является ли сообщение системным.
     */
    private boolean system;

    public ChatMessage() {
        this.system = false;
    }

    public ChatMessage(String name, String text, Boolean system) {
        this.name = name;
        this.text = text;
        this.system = system;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isSystem() {
        return system;
    }

    public void setSystem(boolean system) {
        this.system = system;
    }
}
