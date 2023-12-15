package com.jotace.picpay.repositories;


import com.jotace.picpay.domain.user.User;
import com.jotace.picpay.dto.user.UserRequest;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {
    @Autowired
    EntityManager entityManager;
    @Autowired
    UserRepository repository;
    @Test
    @DisplayName("Should find an user bt id")
    void findUserByIdCaseTrue() {

    }


}