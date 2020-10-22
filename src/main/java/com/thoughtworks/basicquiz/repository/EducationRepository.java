package com.thoughtworks.basicquiz.repository;

import com.thoughtworks.basicquiz.model.Education;
import com.thoughtworks.basicquiz.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class EducationRepository {
    private final Map<Long, ArrayList<Education>> educationMap = new HashMap<Long, ArrayList<Education>>(){{
        // TODO GTB-4: - 正式提交前，删掉调试或者测试数据
        // TODO GTB-4: - long类型，可以使用1L这种缩写
        put((long) 1, new ArrayList<Education>(){{
            add(new Education(1, "111", 2121, "111"));
        }});
    }};

    public void addEducation(Long id, Education education){
        // TODO GTB-4: - 即使只有一行，最好使用"{}"包起来
        if(!educationMap.containsKey(id)) educationMap.put(id, new ArrayList<>());
        education.setUserId(id);
        educationMap.get(id).add(education);
    }


    public List<Education> getEducationById(Long id){
        return educationMap.get(id);
    }

    public void addUser(User user){
        educationMap.put(user.getId(), new ArrayList<>());
    }

    public boolean userExist(Long id){
        return educationMap.containsKey(id);
    }
}
