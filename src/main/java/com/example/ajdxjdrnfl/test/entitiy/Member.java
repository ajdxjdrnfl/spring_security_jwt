package com.example.ajdxjdrnfl.test.entitiy;

import java.util.ArrayList;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.userdetails.User;

@Entity(name = "member")
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String password;

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public Member() {

    }

    public Member(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @Override
    public String toString() {
        return "[" + id + "] name = " + name + ", password = " + password;
    }

    public Optional<User> toUser() {

        Optional<User> opt = Optional.of(new User(name, password, new ArrayList<>()));
        return opt;
    }

}
