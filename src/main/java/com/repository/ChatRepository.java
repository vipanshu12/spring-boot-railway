package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.models.Chat;
import com.models.User;

public interface ChatRepository extends JpaRepository<Chat, Integer> {

    public List<Chat> findByUsersId(Integer userId); // Example of a custom query method

    @Query("SELECT c FROM Chat c WHERE :user MEMBER OF c.users AND :reqUser MEMBER OF c.users")
    public Chat findChatByUsersId(@Param("user")User user, @Param("reqUser")User reqUser); // Example of a custom query method
    
    // Custom query methods can be defined here if needed
    // For example, to find chats by user ID:
    // List<Chat> findByUsersId(Integer userId);

}
