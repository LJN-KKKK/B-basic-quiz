package com.thoughtworks.basicquiz.service;

import com.thoughtworks.basicquiz.exception.UserNotExistException;
import com.thoughtworks.basicquiz.model.User;
import com.thoughtworks.basicquiz.repository.EducationRepository;
import com.thoughtworks.basicquiz.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final EducationRepository educationRepository;

    public UserService(UserRepository userRepository, EducationRepository educationRepository) {
        this.userRepository = userRepository;
        this.educationRepository = educationRepository;
    }

    public Long addUser(User user) {
        Long user_id = userRepository.addUser(user);
        educationRepository.addUser(user);
        return user_id;
    }

    public User getUserById(long id) {
        User user = userRepository.getUserById(id);
        if(user == null) throw new UserNotExistException("user does not exist");
        return user;
    }
}
