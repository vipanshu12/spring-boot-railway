package com.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.models.Post;
import com.models.User;
import com.repository.PostRepository;
import com.repository.UserRepository;

@Service
public class PostServiceImplementation implements PostService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserService userService;

    @Override
    public Post createNewPost(Post post, Integer userId) throws Exception {
        User user = userService.findUserById(userId);

        Post newPost = new Post();
        newPost.setCaption(post.getCaption());
        newPost.setImage(post.getImage());

        newPost.setCreatedAt(LocalDateTime.now());
        newPost.setVideo(post.getVideo());
        newPost.setUser(user);

        // ✅ Save the post to the database
        return postRepository.save(newPost);
    }

    @Override
    public String deletePost(Integer postId, Integer userId) throws Exception {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);

        if (post.getUser().getId() != user.getId()) {
            throw new Exception("You are not authorized to delete this post");
        }

        postRepository.delete(post);
        return "Post deleted successfully";

    }

    // @Override
    // public List<Post> findAllPostByUserId(Integer userId) throws Exception {
    // return postRepository.findByUserId(userId);
    // }

    @Override
    public Post findPostById(Integer postId) throws Exception {

        Optional<Post> opt = postRepository.findById(postId);

        if (opt.isEmpty()) {
            throw new Exception("Post not found with this id: " + postId);
        }
        return opt.get();
    }

    @Override
    public List<Post> findAllPost() throws Exception {

        return postRepository.findAll();
    }

    @Override
    public Post savedPost(Integer postId, Integer userId) throws Exception {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);

        if (user.getSavedPost().contains(post)) {
            user.getSavedPost().remove(post);
        } else {
            user.getSavedPost().add(post);
        }

        userRepository.save(user); // Save user with updated saved posts

        return post; // ✅ return just the post
    }

    @Override
    public Post likePost(Integer postId, Integer userId) throws Exception {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);

        if (post.getLiked().contains(user)) {
            post.getLiked().remove(user);

        } else {
            post.getLiked().add(user);

        }
        return postRepository.save(post);
    }

    public List<Post> findPostByUserId(Integer userId) throws Exception {
        return postRepository.findByUserId(userId);
    }

    // public List<Post> findAllPostByUserId(Integer userId) throws Exception {
    // return postRepository.findByUserId(userId);
    // }

}
