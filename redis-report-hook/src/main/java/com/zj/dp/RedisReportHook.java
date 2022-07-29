package com.zj.dp;

import static com.zj.dp.constants.Constant.DATAX_CORE_CONTAINER_JOB_ID;

import com.alibaba.datax.common.spi.Hook;
import com.alibaba.datax.common.util.Configuration;
import com.zj.dp.enums.State;
import com.zj.dp.constants.Constant;
import java.util.Map;

public class RedisReportHook implements Hook {

    /**
     * 返回名字
     *
     * @return
     */
    @Override
    public String getName() {
        return "redisHook";
    }

    /**
     *  @param jobConf
     *
     * @param msg
     */
    @Override
    public void invoke(Configuration jobConf, Map<String, Number> msg) {
        System.out.println("redisReportHook");
        StringBuilder stringBuilder = new StringBuilder();
        String jobId = jobConf.getString(DATAX_CORE_CONTAINER_JOB_ID);
        Double percentage = msg.get(Constant.PERCENTAGE).doubleValue();
        Long totalReadRecords = msg.get(Constant.TOTAL_READ_RECORDS).longValue();
        String state = State.map.get(msg.get(Constant.STATE).intValue()).name();

        stringBuilder.append("jobId: ")
                .append(jobId)
                .append(" ")
                .append("percentage: ")
                .append(100 * percentage)
                .append("% ")
                .append("totalReadRecords: ")
                .append(totalReadRecords)
                .append(" ")
                .append("state: ")
                .append(state);

        System.out.println(stringBuilder);
    }
}
