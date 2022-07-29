package com.zj.dp.constants;

/**
 * 这里主要是业务层面的处理
 */
public final class Constant {
    public static final String STAGE = "stage";
    public static final String BYTE_SPEED = "byteSpeed";
    public static final String RECORD_SPEED = "recordSpeed";
    public static final String PERCENTAGE = "percentage";

    public static final String STATE = "state";

    public static final String READ_SUCCEED_RECORDS = "readSucceedRecords";
    public static final String READ_SUCCEED_BYTES = "readSucceedBytes";

    public static final String READ_FAILED_RECORDS = "readFailedRecords";
    public static final String READ_FAILED_BYTES = "readFailedBytes";

    public static final String WRITE_RECEIVED_RECORDS = "writeReceivedRecords";
    public static final String WRITE_RECEIVED_BYTES = "writeReceivedBytes";

    public static final String WRITE_FAILED_RECORDS = "writeFailedRecords";
    public static final String WRITE_FAILED_BYTES = "writeFailedBytes";

    public static final String TOTAL_READ_RECORDS = "totalReadRecords";
    private static final String TOTAL_READ_BYTES = "totalReadBytes";

    private static final String TOTAL_ERROR_RECORDS = "totalErrorRecords";
    private static final String TOTAL_ERROR_BYTES = "totalErrorBytes";

    public static final String WRITE_SUCCEED_RECORDS = "writeSucceedRecords";
    private static final String WRITE_SUCCEED_BYTES = "writeSucceedBytes";

    public static final String WAIT_WRITER_TIME = "waitWriterTime";

    public static final String WAIT_READER_TIME = "waitReaderTime";

    public static final String TRANSFORMER_USED_TIME = "totalTransformerUsedTime";
    public static final String TRANSFORMER_SUCCEED_RECORDS = "totalTransformerSuccessRecords";
    public static final String TRANSFORMER_FAILED_RECORDS = "totalTransformerFailedRecords";
    public static final String TRANSFORMER_FILTER_RECORDS = "totalTransformerFilterRecords";
    public static final String TRANSFORMER_NAME_PREFIX = "usedTimeByTransformer_";

    public static final String DATAX_CORE_CONTAINER_JOB_ID = "core.container.job.id";

    public static final String REDIS_REPORT_HOOK_TAG = "redisReportHookTag";
    public static final String SEPARATOR = "_";

    /**
     * redis配置信息
     */
    public static final String REDIS_CLUSTER_NODES = "job.redis.clusterNodes";
    public static final String REDIS_PASSWORD = "job.redis.password";
    public static final String REDIS_TIMEOUT = "job.redis.timeout";
    public static final String REDIS_POOL_MAX_ACTIVE = "job.redis.pool.maxActive";
    public static final String REDIS_POOL_MAX_IDLE = "job.redis.pool.maxIdle";
    public static final String REDIS_POOL_MIN_IDLE = "job.redis.pool.minIdle";
    public static final String REDIS_POOL_MAX_WAIT = "job.redis.pool.maxWait";
    public static final String HOST = "host";
    public static final String PORT = "port";


}
