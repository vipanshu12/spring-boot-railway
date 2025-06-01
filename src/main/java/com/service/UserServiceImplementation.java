package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.config.JwtProvider;
import com.models.User;
import com.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        User newUser = new User();
        newUser.setId(user.getId());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setGender(user.getGender());;

        User savedUser = userRepository.save(newUser);
        // Here you would typically save the user to a database
        // For this example, we'll just return the user object
        return savedUser;
    }

    @Override
    public User findUserById(Integer userId) throws Exception {
        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()) {
            return user.get();
        }
        throw new Exception("User not existed with this id: " + userId);

    }

    @Override
    public User findUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user;
    }

    @Override
    public User followUser(Integer reqUserId, Integer userId2) throws Exception {
        User reqUser = findUserById(reqUserId);
        User user2 = findUserById(userId2);

        user2.getFollowers().add(reqUser.getId());
        reqUser.getFollowings().add(user2.getId());

        userRepository.save(reqUser);
        userRepository.save(user2);
        return reqUser;

    }

    @Override
    public User updateUser(User user, Integer userId) throws Exception {
         Optional<User> user1 = userRepository.findById(userId);

            if (user1.isEmpty()) {
                throw new Exception("User not existed with this id: " + userId);
            }    
            User oldUser = user1.get();

            if (user.getFirstName() != null) {
                oldUser.setFirstName(user.getFirstName());
            }
            if (user.getLastName() != null) {
                oldUser.setLastName(user.getLastName());
            }
            if (user.getEmail() != null) {
                oldUser.setEmail(user.getEmail());
            }
            if(user.getGender() != null) {
                oldUser.setGender(user.getGender());
            }

            User updatedUser = userRepository.save(oldUser);
            return updatedUser;
    }

    @Override
    public List<User> searchUser(String query) {
       return userRepository.searchUser(query);
    }

    @Override
    public User findUserByJwt(String jwt) throws Exception {

        String email = JwtProvider.getEmailFromToken(jwt);
        User user = userRepository.findByEmail(email);

        return user;
    }

}
