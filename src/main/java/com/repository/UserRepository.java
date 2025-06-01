package com.repository;

import java.util.List;

// import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.models.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    com.models.User save(com.models.User newUser);
    // This interface will automatically provide CRUD operations for the User entity
    // You can add custom query methods here if needed

    public User findByEmail(String email);

    @Query("""
    SELECT u FROM User u
    WHERE LOWER(u.firstName) LIKE LOWER(CONCAT('%', :query, '%'))
       OR LOWER(u.lastName) LIKE LOWER(CONCAT('%', :query, '%'))
       OR LOWER(u.email) LIKE LOWER(CONCAT('%', :query, '%'))
""")
List<User> searchUser(@Param("query") String query);


}
