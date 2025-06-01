package com.controller;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.config.JwtProvider;
import com.models.User;
import com.repository.UserRepository;
import com.request.LoginRequest;
import com.response.AuthResponse;
import com.service.CustomerUserDetailsService;
import com.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired  
    private CustomerUserDetailsService customerUserDetailsService; 

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

       @PostMapping("/signup")
public AuthResponse createUser(@RequestBody User user) throws Exception {

    if (user.getPassword() == null || user.getPassword().isEmpty()) {
        throw new Exception("Password cannot be null or empty");
    }

    User isExist = userRepository.findByEmail(user.getEmail());
    if (isExist != null) {
        throw new Exception("User already exists with this email: " + user.getEmail());
    }

    User newUser = new User();
    newUser.setFirstName(user.getFirstName());
    newUser.setLastName(user.getLastName());
    newUser.setEmail(user.getEmail());
    newUser.setPassword(passwordEncoder.encode(user.getPassword())); // Make sure it's not null
    newUser.setGender(user.getGender());

    User savedUser = userRepository.save(newUser);

    UsernamePasswordAuthenticationToken authentication =
            new UsernamePasswordAuthenticationToken(savedUser.getEmail(), user.getPassword());  // Use raw password here

    String token = JwtProvider.generateToken(authentication);

    return new AuthResponse(token, "Register success");
}
    

    @PostMapping("/signin")
    public AuthResponse signin(@RequestBody LoginRequest loginRequest){
        UsernamePasswordAuthenticationToken authentication= 
        authenticate(loginRequest.getEmail(), loginRequest.getPassword());
         String token = JwtProvider.generateToken(authentication);
                AuthResponse res = new AuthResponse(token, "Login success");
                return res;
    }

    private     UsernamePasswordAuthenticationToken authenticate(String email, String password) {

        UserDetails userDetails = 
        customerUserDetailsService.loadUserByUsername(email);

        if(userDetails == null) {
            throw new RuntimeException("User not found with email.");
        }

        if(!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new RuntimeException("Invalid password.");
        }   
        // TODO Auto-generated method stub
        return new UsernamePasswordAuthenticationToken(userDetails,null,  userDetails.getAuthorities());
    }



}
