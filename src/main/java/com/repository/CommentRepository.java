package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    // This interface will automatically provide CRUD operations for the Comment entity
    // You can add custom query methods here if needed

}
