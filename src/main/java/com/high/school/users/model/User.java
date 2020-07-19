package com.high.school.users.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users_table")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="user_id")
    private Long id;

    @Column(name="user_username")
    private String username;

    @Column(name="user_email")
    private String email;

    @Column(name="user_firstname")
    private String firstName;

    @Column(name="user_othernames")
    private String otherNames;

    @Column(name="user_password")
    private String password;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String email, String firstName, String otherNames, String password) {
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.otherNames = otherNames;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getOtherNames() {
        return otherNames;
    }

    public void setOtherNames(String otherNames) {
        this.otherNames = otherNames;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
