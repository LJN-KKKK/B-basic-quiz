package com.thoughtworks.basicquiz.repository;

import com.thoughtworks.basicquiz.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepository {
    private Map<Integer, User> userList = new HashMap<>();;
    private static Integer USER_ID = 1;

    public User addUser(User user){
        userList.put(USER_ID, user);
        USER_ID++;
        return user;
    }
}
