package com.example.projectOne.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_login")
public class UserLogin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "username", nullable = false, length = 20)
    private String username;

    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;

    @Column(name = "passwords", nullable = false)
    private String passwords;

    @Column(name = "roles", length = 8)
    @Enumerated(EnumType.STRING)
    private Roles roles;

    @Column(name = "email", nullable = false, length = 40)
    private String email;

    public UserLogin(String username, String firstName, String lastName, String passwords, Roles roles, String email) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.passwords = passwords;
        this.roles = roles;
        this.email = email;
    }
}