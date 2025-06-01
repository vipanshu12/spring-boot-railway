package com.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.models.Chat;
import com.models.User;
import com.repository.ChatRepository;


@Service
public class ChatServiceImplementation implements ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Override
    public Chat createChat(User reqUser, User user2) {
        // TODO Auto-generated method stub
        Chat isExists = chatRepository.findChatByUsersId(reqUser, user2);

        if(isExists != null) {
            return isExists;
        }
        Chat chat = new Chat();

        chat.getUsers().add(user2);
        chat.getUsers().add(reqUser);
        chat.setTimestamp(LocalDateTime.now());
         return chatRepository.save(chat);
    }

    @Override
    public Chat findChatById(Integer chatId) throws Exception {

        Optional<Chat> opt = chatRepository.findById(chatId);

        if(opt.isEmpty()) {
            throw new Exception("Chat not found");
        }
        return opt.get();
    }

    @Override
    public List<Chat> findUsersChat(Integer userId) throws Exception {
       return chatRepository.findByUsersId(userId);
    }



}
