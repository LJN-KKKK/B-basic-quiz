package com.thoughtworks.basicquiz.repository;

import com.thoughtworks.basicquiz.model.Education;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class EducationRepository {
    private final Map<Long, ArrayList<Education>> educationMap = new HashMap<Long, ArrayList<Education>>(){{
        put((long) 1, new ArrayList<Education>(){{
            add(new Education(1, "111", 2121, "111"));
        }});
    }};

    public void addEducation(Long id, Education education){
        if(!educationMap.containsKey(id)){
            educationMap.put(id, new ArrayList<>());
        }
        educationMap.get(id).add(education);
    }


    public List<Education> getEducationById(Long id){
        return educationMap.get(id);
    }
}
