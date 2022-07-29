package com.zj.dp.utils;

import com.zj.dp.config.RedisConfig;
import java.util.Objects;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.JedisCluster;

public class RedisUtil {
    private static JedisCluster jedisCluster;
    private static JedisCluster getRedisCluster(RedisConfig redisConfig) {
        if(Objects.isNull(jedisCluster)) {
            GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();

            poolConfig.setMaxTotal(redisConfig.getMaxActive());
            poolConfig.setMaxIdle(redisConfig.getMaxWait());
            poolConfig.setMinIdle(redisConfig.getMinIdle());

            //排除maxWait为-1
            if(redisConfig.getMaxWait() > 0) {
                poolConfig.setMaxWaitMillis(redisConfig.getMaxWait());
            }

            jedisCluster = new JedisCluster(
                    redisConfig.getHostAndPortHashSet(),
                    redisConfig.getConnectionTimeout(),
                    redisConfig.getSoTimeout(),
                    redisConfig.getMaxAttempts(),
                    redisConfig.getPassword(),
                    poolConfig);
        }
        return jedisCluster;
    }

    public static void pushToRedis(RedisConfig redisConfig, String key, String value) {
        getRedisCluster(redisConfig).lpush(key, value);
    }
}
