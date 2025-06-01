package com.service;

import java.util.List;

import com.models.Chat;
import com.models.User;

public interface ChatService {

    public Chat createChat(User reqUser, User user2);

    public Chat findChatById(Integer chatId) throws Exception;
    public List<Chat> findUsersChat(Integer userId) throws Exception;


    

}
