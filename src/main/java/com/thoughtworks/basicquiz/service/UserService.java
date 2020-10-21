package com.thoughtworks.basicquiz.service;

import com.thoughtworks.basicquiz.model.User;
import com.thoughtworks.basicquiz.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user) {
        return userRepository.addUser(user);
    }

    public User getUserById(long id) {
        return userRepository.getUserById(id);
    }
}
