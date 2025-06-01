package com.controller;

import com.models.User;
import com.repository.UserRepository;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/user")
    public User createUser(@RequestBody User user) {
        User savedUser = userService.registerUser(user);

        return savedUser;
    }
    


    @GetMapping("/user")
    public List<User>getUser(){
        List<User>users= userRepository.findAll();

        return users;
    }
    @GetMapping("/user/{userId}")
    public  User getUserById(@PathVariable("userId") Integer id)throws Exception{
        User user=userService.findUserById(id);
        // user.setPassword(null);
        
        return user;

    }
    
    @PutMapping("/user")
public User updateUser(@RequestHeader("Authorization") String jwt,
                       @RequestBody User user) throws Exception { 

            User reqUser=userService.findUserByJwt(jwt);
      User updatedUser=userService.updateUser(user, reqUser.getId() );
      
        return  updatedUser ;

    }

    @PutMapping("api/users/follow/{userId2}")
    public User followUserHandler(@RequestHeader("Authorization")String jwt , @PathVariable Integer userId2)throws Exception
    {
        User reqUser=userService.findUserByJwt(jwt);
        User user =userService.followUser(reqUser.getId() , userId2);
        return user ;
    }

    @GetMapping("/users/search")
    public List<User> seacUser(@RequestParam("query") String query)throws Exception
    {

        List<User> users=userService.searchUser(query);

        return users;
    }

    @GetMapping("/profile")
    public User getUserFromToken(@RequestHeader("Authorization")String jwt) throws Exception
    {
        User user =userService.findUserByJwt(jwt);
        // user .setPassword(null);
        return user ;
    }
}