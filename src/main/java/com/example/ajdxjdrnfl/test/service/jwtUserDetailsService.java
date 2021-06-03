package com.example.ajdxjdrnfl.test.service;

import java.util.ArrayList;

import com.example.ajdxjdrnfl.test.entitiy.Member;
import com.example.ajdxjdrnfl.test.repository.MemberRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class jwtUserDetailsService implements UserDetailsService {
    @Autowired
    MemberRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        Member member = repository.findByName(username);
        if (member != null) {

            return new User(member.getName(), member.getPassword(), new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username : " + username);
        }
    }
}
