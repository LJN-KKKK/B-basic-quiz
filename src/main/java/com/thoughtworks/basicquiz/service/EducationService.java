package com.thoughtworks.basicquiz.service;

import com.thoughtworks.basicquiz.exception.UserHasNoEducationException;
import com.thoughtworks.basicquiz.exception.UserNotExistException;
import com.thoughtworks.basicquiz.model.Education;
import com.thoughtworks.basicquiz.repository.EducationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationService {
    private final EducationRepository educationRepository;
    public EducationService(EducationRepository educationRepository) {
        this.educationRepository = educationRepository;
    }

    public void addEducation(Long id, Education education) {
        if(!educationRepository.userExist(id)) throw new UserNotExistException("user does not exist");
        educationRepository.addEducation(id, education);
    }

    public List<Education> getEducationById(Long id){
        if(!educationRepository.userExist(id)) throw new UserNotExistException("user does not exist");
        List<Education> educationsList = educationRepository.getEducationById(id);
        if(educationsList.isEmpty()) throw new UserHasNoEducationException("user has no education");
        return educationsList;
    }
}
