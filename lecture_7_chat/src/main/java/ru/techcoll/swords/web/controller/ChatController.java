package ru.techcoll.swords.web.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import ru.techcoll.swords.domain.ChatMessage;

/**
 * Котроллер чата
 */
@Controller
public class ChatController {

    @MessageMapping("/chat")
    @SendTo("/topic/chat/general")
    public ChatMessage message(ChatMessage message) {
        return message;
    }

}
