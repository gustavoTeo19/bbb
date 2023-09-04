package com.example.barberboost.models;

import com.example.barberboost.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
@Table(name = "TB_USER")
@Entity(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserModel implements UserDetails  {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false)
    private String login;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private UserRole role;
    @Column(nullable = false, unique = true)
    private String cellphone;

    public UserModel(String login, String password, UserRole role, String cellphone){
        this.login = login;
        this.password = password;
        this.role = role;
        this.cellphone = cellphone;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"),
                new SimpleGrantedAuthority("ROLE_CLIENT"),
                new SimpleGrantedAuthority("ROLE_BARBER"));
        else if (this.role == UserRole.CLIENT) {
            new SimpleGrantedAuthority("ROLE_CLIENT");
        } else if(this.role == UserRole.BARBER) {
            new SimpleGrantedAuthority("ROLE_BARBER");
        }
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.login;
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

    @Override
    public String toString() {
        return "{" +
                ", login='" + login + '\'' +
                ", role=" + role +
                ", cellphone='" + cellphone + '\'' +
                '}';
    }
}
