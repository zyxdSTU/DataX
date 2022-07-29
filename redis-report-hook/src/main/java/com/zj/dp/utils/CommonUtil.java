package com.zj.dp.utils;

import java.util.Objects;

public class CommonUtil {
    public static Long getLongValue(Number number) {
        return Objects.isNull(number) ? 0 : number.longValue();
    }


    public static Integer getIntValue(Number number) {
        return Objects.isNull(number) ? 0 : number.intValue();
    }

    public static Double getDoubleValue(Number number) {
        return Objects.isNull(number) ? 0 : number.doubleValue();
    }

}
