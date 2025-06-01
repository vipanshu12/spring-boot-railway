
package com.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import com.models.Chat;
import com.models.Message;
import com.models.User;
import com.repository.ChatRepository;
import com.repository.MessageRepository;

@Service
public class MessageServiceImplementation implements MessageService {

    
        @Autowired 
        private MessageRepository messageRepository;

        @Autowired
        private ChatService chatService;

        @Autowired
        private ChatRepository chatRepository;


        



    @Override
    public List<Message> findChatsMessages(Integer chatId) throws Exception {
        Chat chat = chatService.findChatById(chatId); 
       
        return messageRepository.findByChatId(chatId);
    }

    @Override
    public Message createMessage(User user, Integer chatId, Message req) throws Exception {


          Chat chat = chatService.findChatById(chatId);
        Message message = new Message();

      

        message.setChat(chat);
        message.setContent(req.getContent());
        message.setImage(req.getImage());
        message.setUser(user);
        message.setTimestamp(LocalDateTime.now());

        Message savedMessage=messageRepository.save(message);   
        chat.getMessages().add(savedMessage);
        chatRepository.save(chat);
        return savedMessage;    
    }

}
