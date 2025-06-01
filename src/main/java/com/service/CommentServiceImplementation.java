package com.service;

import java.lang.StackWalker.Option;
import java.time.LocalDateTime;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.models.Comment;
import com.models.Post;
import com.models.User;
import com.repository.CommentRepository;
import com.repository.PostRepository;

@Service
public class CommentServiceImplementation implements CommentService {


    @Autowired
    PostService postService;

    @Autowired
    UserService userService;    

    @Autowired
    PostRepository postRepository;

     @Autowired
     private CommentRepository commentRepository;   
    @Override
    public Comment findCommentById(Integer commentId) throws Exception {
        Optional<Comment> opt    = commentRepository.findById(commentId);
        if(opt.isEmpty()){
            throw new Exception("Comment not found");
        }
        // TODO Auto-generated method stub
        return opt.get();
    }

    @Override
    public Comment likeComment(Integer commentId, Integer userId) throws Exception {

        Comment comment = findCommentById(commentId);
        User user = userService.findUserById(userId);
        if (!comment.getLiked().contains(user)) {
            comment.getLiked().add(user);
            
        } else {
           comment.getLiked().remove(user);
        }
        // TODO Auto-generated method stub
        return commentRepository.save(comment);

    }

    @Override
    public Comment createComment(Comment comment, Integer postId, Integer userId) throws Exception {
        User user = userService.findUserById(userId);

        Post post = postService.findPostById(postId);

        comment.setUser(user);
        comment.setContent(comment.getContent());
        comment.setCreatedAt(LocalDateTime.now());

        Comment savedComment = commentRepository.save(comment);

        post.getComments().add(savedComment);

        postRepository.save(post);

        return savedComment;
        // TODO Auto-generated method stub
        
    }

}
