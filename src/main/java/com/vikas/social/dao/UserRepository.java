package com.vikas.social.dao;

import com.vikas.social.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUserName(String userName);

    // 2. Find a user by their email (Useful for "Forgot Password" or Login)
    Optional<User> findByEmail(String email);

    // 3. Check if a username already exists (Useful for Registration validation)
    boolean existsByUserName(String userName);

    // 4. Check if an email already exists (Useful for Registration validation)
    boolean existsByEmail(String email);
}
