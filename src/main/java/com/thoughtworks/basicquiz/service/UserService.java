package com.thoughtworks.basicquiz.service;

import com.thoughtworks.basicquiz.exception.UserNotExistException;
import com.thoughtworks.basicquiz.model.User;
import com.thoughtworks.basicquiz.repository.EducationRepository;
import com.thoughtworks.basicquiz.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {
    final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User getUserById(Long id) {
        return userRepository.findOneById(id)
                .orElseThrow(() -> new UserNotExistException("User Not Found"));
    }

    public Long addUser(User user) {
        userRepository.save(user);
        return user.getId();
    }

}
