package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.models.Message;

@Controller
public class RealTimeChatController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/chat/{groupId}")
    public void sendToGroup(@Payload Message message, @DestinationVariable String groupId) {
        // Broadcast to all subscribers of /chat/{groupId}
        simpMessagingTemplate.convertAndSend("/chat/" + groupId, message);
    }
}