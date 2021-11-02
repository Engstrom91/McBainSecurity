package com.engstrom.security.service;

import com.engstrom.security.model.Role;
import com.engstrom.security.model.User;
import com.engstrom.security.repository.UserRepository;
import com.engstrom.security.web.dto.UserRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User save(UserRegistration registration) {
        User user = new User(registration.getFirstName(),
                registration.getLastName(), registration.getEmail(),
                passwordEncoder.encode(registration.getPassword()), Arrays.asList(new Role("ROLE_USER")));

        return userRepo.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username); //Our username is the email address
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");

        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    //Spring security expects authorities that must be provided. We will provide our roles.
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());

    }

    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }
}
