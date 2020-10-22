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
        // TODO GTB-4: - ++i和i++操作不是线程安全的，可以了解下AtomicInteger
        user_id++;
        return user.getId();
    }


    public User getUserById(long id) {
        return userMap.get(id);
    }

}
