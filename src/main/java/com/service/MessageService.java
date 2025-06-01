package com.service;

import java.util.List;

import com.models.Chat;
import com.models.Message;
import com.models.User;

public interface MessageService {

    public Message createMessage(User user, Integer chatId, Message req) throws Exception;

    public List<Message> findChatsMessages(Integer chatId) throws Exception; 

    


}
