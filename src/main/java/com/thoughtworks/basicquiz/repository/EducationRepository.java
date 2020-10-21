package com.thoughtworks.basicquiz.repository;

import com.thoughtworks.basicquiz.model.Education;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class EducationRepository {
    private final Map<Long, Education> educationMap = new HashMap<Long, Education>(){{
        put((long) 1, new Education(1, "111", 2121, "111"));
    }};

    public void addEducation(Long id, Education education){
        educationMap.put(id, education);
    }
}
