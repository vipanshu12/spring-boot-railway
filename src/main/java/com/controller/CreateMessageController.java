package com.controller;

import com.models.User;
import com.models.Message;
import com.service.MessageService;
import com.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CreateMessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @PostMapping("/api/messages/chat/{chatId}")
    public Message createMessage(@RequestBody Message req, @RequestHeader("Authorization") String jwt, @PathVariable Integer chatId) throws Exception {
        User user = userService.findUserByJwt(jwt);
        Message message = messageService.createMessage(user, chatId, req);
        return message;
    }

    @GetMapping("/api/messages/chat/{chatId}")
    public List<Message> findChatsMessage(
        @RequestHeader("Authorization") String jwt,
        @PathVariable Integer chatId) throws Exception {
        User user = userService.findUserByJwt(jwt);
        return messageService.findChatsMessages(chatId);
    }
}