package com.zj.dp.config;

import java.util.HashSet;
import lombok.Data;
import redis.clients.jedis.HostAndPort;


@Data
public class RedisConfig {

    HashSet<HostAndPort> hostAndPortHashSet;
    String password;
    Integer maxActive;
    Integer maxIdle;
    Integer minIdle;
    Integer maxWait;
    Integer connectionTimeout;
    Integer soTimeout;
    Integer maxAttempts;

    public RedisConfig() {
        this.maxActive = 100;
        this.maxIdle = 10;
        this.minIdle = 5;
        this.maxWait = -1;
        this.connectionTimeout = 10000;
        this.soTimeout = 10000;
        this.maxAttempts = 5;
    }
}
