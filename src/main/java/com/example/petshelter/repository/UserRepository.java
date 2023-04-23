package com.example.petshelter.repository;

import com.example.petshelter.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByPhoneNumber(String string);

    Optional<User> findByTelegramId(Long id);

    boolean existsByPhoneNumber(String phoneNumber);
}
