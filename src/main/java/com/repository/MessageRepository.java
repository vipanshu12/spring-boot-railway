package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.models.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    public List<Message> findByChatId(Integer chatId); // ✅ Valid custom method

    // ❌ DO NOT override save() and findAll() — already provided by JpaRepository

}
