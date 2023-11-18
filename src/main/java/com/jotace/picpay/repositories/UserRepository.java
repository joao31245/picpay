package com.jotace.picpay.repositories;

import com.jotace.picpay.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserById(Long id);

    User findUserByEmail(String email);
    List<User> findAllByActiveTrue();
}
