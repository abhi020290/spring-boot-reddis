package com.springboot.reddis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
@CacheConfig(cacheNames = {"product"})
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

    @Cacheable(cacheNames = "ProductById" , key = "#id")
    public Product findProductById(int id) {
        log.info("Getting value from redis database for id "+id);
        return (Product) hashOperations.get(HASH_KEY, id);
    }

    @CacheEvict(cacheNames = "ProductById" , key = "#id")
    public String deleteProduct(int id) {
        hashOperations.delete(HASH_KEY, id);
        return "product removed !!";
    }

}