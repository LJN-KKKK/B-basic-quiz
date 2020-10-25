package com.thoughtworks.basicquiz.service;

import com.thoughtworks.basicquiz.model.Education;
import com.thoughtworks.basicquiz.repository.EducationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class EducationService {
    final EducationRepository educationRepository;

    public EducationService(EducationRepository educationRepository) {
        this.educationRepository = educationRepository;
    }

    public void addEducation(Long id, Education education) {
        education.setId(id);
        educationRepository.save(education);
    }

//    public List<Education> getEducationById(Long id) {
//        return educationRepository.getEducationById(id);
//    }

}
