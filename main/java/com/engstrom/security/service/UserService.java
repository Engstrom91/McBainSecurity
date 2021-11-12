package com.engstrom.security.service;

import com.engstrom.security.model.User;
import com.engstrom.security.web.dto.UserRegistration;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
User save(UserRegistration registration);


}
