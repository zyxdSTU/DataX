package com.zj.dp;

import static com.zj.dp.constants.Constant.DATAX_CORE_CONTAINER_JOB_ID;
import static com.zj.dp.constants.Constant.HOST;
import static com.zj.dp.constants.Constant.PORT;
import static com.zj.dp.constants.Constant.REDIS_CLUSTER_NODES;
import static com.zj.dp.constants.Constant.REDIS_PASSWORD;
import static com.zj.dp.constants.Constant.REDIS_POOL_MAX_ACTIVE;
import static com.zj.dp.constants.Constant.REDIS_POOL_MAX_IDLE;
import static com.zj.dp.constants.Constant.REDIS_POOL_MAX_WAIT;
import static com.zj.dp.constants.Constant.REDIS_POOL_MIN_IDLE;
import static com.zj.dp.constants.Constant.REDIS_REPORT_HOOK_TAG;
import static com.zj.dp.constants.Constant.REDIS_TIMEOUT;
import static com.zj.dp.constants.Constant.SEPARATOR;

import com.alibaba.datax.common.spi.Hook;
import com.alibaba.datax.common.util.Configuration;
import com.alibaba.fastjson.JSON;
import com.zj.dp.config.RedisConfig;
import com.zj.dp.constants.Constant;
import com.zj.dp.dto.DataxProgressDTO;
import com.zj.dp.utils.CommonUtil;
import com.zj.dp.utils.RedisUtil;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import redis.clients.jedis.HostAndPort;

public class RedisReportHook implements Hook {

    /**
     * 返回名字
     *
     * @return
     */
    @Override
    public String getName() {
        return "redisReportHook";
    }

    /**
     *  @param jobConf
     *
     * @param msg
     */
    @Override
    public void invoke(Configuration jobConf, Map<String, Number> msg) {
        System.out.println(REDIS_REPORT_HOOK_TAG);
        String jobId = jobConf.getString(DATAX_CORE_CONTAINER_JOB_ID);

        DataxProgressDTO dataxProgressDTO = DataxProgressDTO.builder()
                .jobId(jobId)
                .percentage(CommonUtil.getDoubleValue(msg.get(Constant.PERCENTAGE)))
                .state(CommonUtil.getIntValue(msg.get(Constant.STATE)))
                .readSucceedRecords(CommonUtil.getLongValue(msg.get(Constant.READ_SUCCEED_RECORDS)))
                .writeSucceedRecords(CommonUtil.getLongValue(msg.get(Constant.WRITE_SUCCEED_RECORDS)))
                .timestamp(System.currentTimeMillis())
                .build();

        RedisConfig redisConfig = getRedisConfig(jobConf);

        RedisUtil.pushToRedis(redisConfig, constructRedisKey(jobId), JSON.toJSONString(dataxProgressDTO));
    }

    private String constructRedisKey(String jobId) {
        return REDIS_REPORT_HOOK_TAG + SEPARATOR + jobId;
    }


    private RedisConfig getRedisConfig(Configuration configuration) {
        RedisConfig redisConfig = new RedisConfig();
        if(Objects.nonNull(configuration.getString(REDIS_PASSWORD))) {
            redisConfig.setPassword(configuration.getString(REDIS_PASSWORD));
        }

        if(Objects.nonNull(configuration.get(REDIS_POOL_MAX_ACTIVE))) {
            redisConfig.setMaxActive(configuration.getInt(REDIS_POOL_MAX_ACTIVE));
        }

        if(Objects.nonNull(configuration.get(REDIS_POOL_MAX_WAIT))) {
            redisConfig.setMaxWait(configuration.getInt(REDIS_POOL_MAX_WAIT));
        }

        if(Objects.nonNull(configuration.get(REDIS_POOL_MAX_IDLE))) {
            redisConfig.setMaxIdle(configuration.getInt(REDIS_POOL_MAX_IDLE));
        }

        if(Objects.nonNull(configuration.get(REDIS_POOL_MIN_IDLE))) {
            redisConfig.setMinIdle(configuration.getInt(REDIS_POOL_MIN_IDLE));
        }

        if(Objects.nonNull(configuration.get(REDIS_TIMEOUT))) {
            redisConfig.setConnectionTimeout(configuration.getInt(REDIS_TIMEOUT));
        }

        List<Configuration> redisClusterNodes = configuration.getListConfiguration(REDIS_CLUSTER_NODES);

        HashSet<HostAndPort> hostAndPortHashSet = new HashSet<>();

        redisClusterNodes.stream().forEach(hostAndPort->{
            hostAndPortHashSet.add(new HostAndPort(hostAndPort.getString(HOST), hostAndPort.getInt(PORT)));
        });

        redisConfig.setHostAndPortHashSet(hostAndPortHashSet);

        return redisConfig;
    }
}
