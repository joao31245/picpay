package com.jotace.picpay.repositories;

import com.jotace.picpay.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserById(Long id);

    UserDetails findUserByEmail(String email);
    List<User> findAllByActiveTrue();
}
