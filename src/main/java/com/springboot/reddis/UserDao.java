package com.springboot.reddis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class UserDao {

    public static final String HASH_KEY = "User";

    public HashOperations hashOperations;

    @Autowired
    private RedisTemplate template;

    @PostConstruct
    public void init() {
        hashOperations = template.opsForHash();
    }

    public User save(User user) {
        hashOperations.put(HASH_KEY, user.getId(), user);
        return user;
    }

    public Long size() {
        return hashOperations.size(HASH_KEY);

    }

    public List<User> values() {
        return hashOperations.values(HASH_KEY);
    }

    public Map entries() {
        return hashOperations.entries(HASH_KEY);
    }

    public List<User> findAll() {
        return hashOperations.values(HASH_KEY);
    }

    public User findByUserId(int id) {
        return (User) hashOperations.get(HASH_KEY, id);
    }

    public String deleteUser(int id) {
        hashOperations.delete(HASH_KEY, id);
        return "user removed !!";
    }

}