package com.springboot.reddis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("User")
public class User implements Serializable {

    private String id;
    private String firstName;
    private String lastName;
    private String profileId;
}
