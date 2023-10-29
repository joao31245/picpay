package com.jotace.picpay.domain.user;

import com.jotace.picpay.dto.UserRequest;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "users")
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Column(unique = true)
    private String cpf;

    @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    private BigDecimal amount;

    public User(UserRequest request) {
        this.fullName = request.fullName();
        this.cpf = request.cpf();
        this.email = request.email();
        this.password = request.password();
        this.userType = request.userType();
        this.amount = request.amount();
    }
}
