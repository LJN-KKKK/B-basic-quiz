package com.thoughtworks.basicquiz.service;

import com.thoughtworks.basicquiz.model.Education;
import com.thoughtworks.basicquiz.repository.EducationRepository;
import org.springframework.stereotype.Service;

@Service
public class EducationService {
    private final EducationRepository educationRepository;

    public EducationService(EducationRepository educationRepository) {
        this.educationRepository = educationRepository;
    }

    public void addEducation(Long id, Education education) {
        educationRepository.addEducation(id, education);
    }
}
