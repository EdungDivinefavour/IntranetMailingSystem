package com.cs2043.messenger.config;

import com.cs2043.messenger.app.message.models.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {
    @MessageMapping("/send/message")
    @SendTo("/topic/messages")
    public Message handleMessage(Message message) {
        // Handle the received message and return a response
        return message;
    }
}
