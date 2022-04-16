package com.example.projectOne.utils;

import com.example.projectOne.models.UserLogin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class UserSecurity implements UserDetails {

    private UserLogin userLogin;
    private String username;
    private String password;
    private Set<? extends GrantedAuthority> authorties;

    private static Logger logger = LoggerFactory.getLogger(UserSecurity.class);

    public UserSecurity(String username, String password, Set<? extends GrantedAuthority> authorties) {
        this.username = username;
        this.password = password;
        this.authorties = authorties;
    }

    public UserSecurity(UserLogin userLogin) {
        this.userLogin = userLogin;
    }

    public static UserSecurity build(UserLogin user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.addAll(Arrays.asList(new SimpleGrantedAuthority(user.getRoles().name())));
        logger.info("Username is " + user.getUsername());
        logger.info("password is " + user.getPasswords());
        logger.info("authority is " + authorities);
        return new UserSecurity(user.getUsername(), user.getPasswords(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorties;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
