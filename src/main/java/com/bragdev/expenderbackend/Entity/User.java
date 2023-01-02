package com.bragdev.expenderbackend.Entity;

import io.swagger.annotations.ApiResponse;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Entity(name = "User")
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(name = "email_idx", columnList = "email")
})
public class User implements UserInterface, Serializable, Exportable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "email", length = 255)
    private String email;

    @OneToMany(mappedBy = "user")
    private Collection<com.example.demo.Account> accounts;

    @OneToMany(mappedBy = "user")
    private Collection<ApiToken> apiTokens;

    public User() {
        this.accounts = new ArrayList<>();
        this.apiTokens = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Collection<com.example.demo.Account> getAccounts() {
        return accounts;
    }

    public Collection<ApiToken> getApiTokens() {
        return apiTokens;
    }

    @Override
    public Map<String, Object> toApi() {
        return new ApiResponse(id);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return email;
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
    public String getPassword() {
        return null;
    }

    @Override
    public String getSalt() {
        return null;
    }
}

