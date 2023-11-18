package com.jotace.picpay.domain.user;
import com.jotace.picpay.dto.user.UserRequest;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

@Entity(name = "users")
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {
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

    private boolean active;

    public User(UserRequest request, String encryptedPassword) {
        this.fullName = request.fullName();
        this.cpf = request.cpf();
        this.email = request.email();
        this.password = encryptedPassword;
        this.userType = request.userType();
        this.amount = request.amount();
        this.active = true;
    }

    public void delete() {
        this.active = false;
    }

    public void update(String email, String password) {
            this.email = email;
            this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.userType.equals(UserType.MERCHANT))
            return List.of(new SimpleGrantedAuthority("ROLE_MERCHANT"));

       else if(this.userType.equals(UserType.COMMON))
           return List.of(new SimpleGrantedAuthority("ROLE_COMMON"));

       else return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"),
                   new SimpleGrantedAuthority("ROLE_COMMON"),
                   new SimpleGrantedAuthority("ROLE_MERCHANT"));
    }

    @Override
    public String getUsername() {
        return this.email;
    }
    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
