package com.thoughtworks.basicquiz.repository;

import com.thoughtworks.basicquiz.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepository {
    private final Map<Long, User> userMap = new HashMap<Long, User>(){{
        put((long) 1, new User(1, "111", 12, "111", "no desc"));
    }};
    private static Long user_id = 2L;


    public Long addUser(User user){
        user.setId(user_id);
        userMap.put(user_id, user);
        user_id++;
        return user.getId();
    }


    public User getUserById(long id) {
        return userMap.get(id);
    }

}
