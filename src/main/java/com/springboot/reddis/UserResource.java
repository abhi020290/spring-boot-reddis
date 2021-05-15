package com.springboot.reddis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserResource {

    @Autowired
    private UserDao dao;

    @PostMapping
    public User save(@RequestBody User user) {
        return dao.save(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return dao.findAll();
    }

    @GetMapping("/{id}")
    public User findProduct(@PathVariable int id) {
        return dao.findByUserId(id);
    }
    @DeleteMapping("/{id}")
    public String remove(@PathVariable int id)   {
        return dao.deleteUser(id);
    }

    @GetMapping("size")
    public Long getSize() {
        return dao.size();
    }

    @GetMapping("entries")
    public Map entries() {
        return dao.entries();
    }

    @GetMapping("values")
    public List<User> values() {
        return dao.values();
    }

}
