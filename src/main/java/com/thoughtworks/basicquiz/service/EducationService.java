package com.thoughtworks.basicquiz.service;

import com.thoughtworks.basicquiz.exception.UserNotExistException;
import com.thoughtworks.basicquiz.model.Education;
import com.thoughtworks.basicquiz.model.User;
import com.thoughtworks.basicquiz.repository.EducationRepository;
import com.thoughtworks.basicquiz.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class EducationService {
    final EducationRepository educationRepository;
    final UserRepository userRepository;

    public EducationService(EducationRepository educationRepository, UserRepository userRepository) {
        this.educationRepository = educationRepository;
        this.userRepository = userRepository;
    }

    public void addEducation(Long id, Education education) {
        User user = userRepository.findOneById(id)
                .orElseThrow(() -> new UserNotExistException("User does not exist"));
        education.setUser(user);
        educationRepository.save(education);
    }

    public List<Education> getEducationById(Long id) {
        return educationRepository.findAllByUserId(id);
    }

}
