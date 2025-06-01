package com.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.models.Reels;

@Repository
public interface ReelsRepository extends JpaRepository<Reels, Integer> {

    List<Reels> findByUserId(Integer userId); // ✅ Valid custom method

    // ❌ DO NOT override save() and findAll() — already provided by JpaRepository
}
