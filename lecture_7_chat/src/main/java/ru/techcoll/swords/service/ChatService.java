package ru.techcoll.swords.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.techcoll.swords.domain.ChatMessage;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Сервис чат сообщений
 */
@Service
public class ChatService {

    @Autowired
    private SimpMessagingTemplate template;

    private AtomicLong uptime = new AtomicLong(0L);

    @Scheduled(fixedRate = 60000)
    public void sendUptimeMessages() {

        template.convertAndSend("/topic/chat/general", new ChatMessage(
            "Sysbot", String.format("System uptime is %dmin", uptime.incrementAndGet()), true
        ));

    }

}
