package com.springboot.reddis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Repository
public class ProductDao {

    public static final String HASH_KEY = "Product";

    public HashOperations hashOperations;

    @Autowired
    private RedisTemplate template;

    @PostConstruct
    public void init() {
        hashOperations = template.opsForHash();
    }

    public Product save(Product product) {
        hashOperations.put(HASH_KEY, product.getId(), product);
        return product;
    }

    public Long size() {
        return hashOperations.size(HASH_KEY);

    }

    public List<Product> values() {
        return hashOperations.values(HASH_KEY);
    }

    public Map entries() {
        return hashOperations.entries(HASH_KEY);
    }

    public List<Product> findAll() {
        return hashOperations.values(HASH_KEY);
    }

    public Product findProductById(int id) {
        return (Product) hashOperations.get(HASH_KEY, id);
    }

    public String deleteProduct(int id) {
        hashOperations.delete(HASH_KEY, id);
        return "product removed !!";
    }

}