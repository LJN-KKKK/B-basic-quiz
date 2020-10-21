package com.thoughtworks.basicquiz.repository;

import com.thoughtworks.basicquiz.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserRepository {
    private final Map<Long, User> userMap = new HashMap<Long, User>(){{
        put((long) 1, new User(1, "111", 12, "111", "no desc"));
    }};
    private static final AtomicLong USER_ID = new AtomicLong(2);


    public Long addUser(User user){
        user.setId(USER_ID.get());
        userMap.put(USER_ID.get(), user);
        USER_ID.set(USER_ID.get() + 1);
        return user.getId();
    }


    public User getUserById(long id) {
        return userMap.get(id);
    }

    public boolean userExist(Long id){
        return userMap.containsKey(id);
    }

}
