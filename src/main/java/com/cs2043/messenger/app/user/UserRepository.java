package com.cs2043.messenger.app.user;

import com.cs2043.messenger.app.user.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    @Query("SELECT u " +
            "FROM User u " +
            "WHERE u.email = ?1")
    Optional<User> findByEmail(String email);
}
