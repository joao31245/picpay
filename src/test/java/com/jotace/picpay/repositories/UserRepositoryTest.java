package com.jotace.picpay.repositories;


import com.jotace.picpay.domain.user.User;
import com.jotace.picpay.dto.user.UserRequest;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

// imports...

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {
    @Autowired
    EntityManager entityManager;

    @Autowired
    UserRepository repository;

    @Test
    @DisplayName("Should find an user by id")
    void findUserByIdCaseTrue() {
        // Dado um usuário existente no banco de dados de testes
        User user = new User();
        user.setFullName("John Doe");
        user.setEmail("john@example.com");
        entityManager.persist(user);
        entityManager.flush();

        User foundUser = repository.findById(user.getId()).orElse(null);

        assertNotNull(foundUser);
        assertEquals(user.getId(), foundUser.getId());
        assertEquals(user.getFullName(), foundUser.getFullName());
        assertEquals(user.getEmail(), foundUser.getEmail());
    }
}
