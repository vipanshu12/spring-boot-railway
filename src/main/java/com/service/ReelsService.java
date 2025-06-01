package com.service;

import java.util.List;

import com.models.Reels;
import com.models.User;

public interface ReelsService {

    


    public Reels createReel(Reels reels, User user);

    public List<Reels> findAllReels();

    public List<Reels> findUsersReel(Integer userId) throws Exception;



}
