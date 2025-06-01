package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.models.Reels;
import com.models.User;
import com.repository.ReelsRepository;

@Service

public class ReelsServiceImplementation implements ReelsService {
   


    @Autowired
    private ReelsRepository reelsRepository;

    @Autowired
    private UserService userService;




    @Override
    public List<Reels> findAllReels() {
        // TODO Auto-generated method stub
        return reelsRepository.findAll();
    }
 @Override
    public List<Reels> findUsersReel(Integer userId) throws Exception {
        userService.findUserById(userId);
        return reelsRepository.findByUserId(userId);
    }

    @Override
    public Reels createReel(Reels reels, User user) {

                Reels createReel= new Reels();
        createReel.setTitle(reels.getTitle());
        createReel.setUser(user);

        createReel.setVideo(reels.getVideo());

        return reelsRepository.save(createReel);
        // TODO Auto-generated method stub
    
    }

}
